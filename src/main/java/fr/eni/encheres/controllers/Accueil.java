package fr.eni.encheres.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bo.Article;

/**
 * Affichige de la page d'accueil du site ENI_enchères
 * Servlet implementation class Accueil
 */
@WebServlet("/home")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Accueil() {
        // TODO Auto-generated constructor stub
    }
    
    ArticleManager articleMgr = ArticleManager.getInstance();
    CategorieManager categorieMgr = CategorieManager.getInstance();
    List<Article> articlesAvailable;

	/**
	 * Récupère les Articles et les catégories en attribut, et affiche la page accueil
	 * Si il y a un parametre, détruit la session
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//On vérifie que la liste d'articles n'est pas null
		try 
		{
			if(articleMgr.getArticlesAvailable() != null && request.getAttribute("listeArticles") == null) 
			{ 
				//Récupérer la liste des articles disponibles à la vente en base de données et qui doivent être affichés sur la page d'accueil
				articlesAvailable = articleMgr.getArticlesAvailable();
				request.setAttribute("listeArticles", articlesAvailable);
			}
		
			//Si j'ai cliqué sur le lien : "se déconnecter", je détruit ma session
			if( request.getParameter("disconnect") != null)
			{
				HttpSession session = ((HttpServletRequest)request).getSession();
				session.removeAttribute("connect");
			}
			
			//Je rentre dans l'attribut listeCatégories, toutes les catégories de ma base de données
			request.setAttribute("listeCategories", categorieMgr.getCategories());
			
		} 
		catch (BLLException e) 
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		//Remonter cette liste vers l'IHM qui va afficher les articles disponibles aux utilisateurs
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		
		if( rd != null)
		{
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		//Récupère la saisie de l'utilisateur de la box de recherche
		String recherche = request.getParameter("search");
		
		//Récupère le no_catégorie de la liste déroulante, choisi par l'utilisateur
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		
		List<Article> articles = new ArrayList<Article>();
		
		//Récupère le pseudo de l'utilisateur actuellement connecté
		String pseudo = (String) session.getAttribute("connect");
		
		boolean all = true;
		
		try
		{
		
			//Récupère les articles et les insères dans un attribut
			request.setAttribute("listeArticles", articlesAvailable);
			//Récupère les catégories et les insères dans un attribut
			request.setAttribute("listeCategories", categorieMgr.getCategories());
		
			
			//Si l'utilisateur a coché une checkbox
			if( request.getParameter("achat") != null)
			{	
				//Si l'utilisateur a coché la checkbox "achat"
				if( request.getParameter("achat").equals("achat") )
				{
					request.setAttribute("achat", true);
					
					//Si l'utilisateur a coché le check "encheres_ouvertes"
					if( request.getParameter("encheres_ouvertes") != null)
					{
						//Récupère toutes les enchères en cours
						if(articleMgr.getArticles( "on sell") != null )
						{
							for( Article article : articleMgr.getArticles("on sell"))
							{
								articles.add(article);
							}
						}
						
						all = false;
						
					}
					
					if( all )
					{
						if(articleMgr.getArticles("on sell") != null )
						{
							for( Article article : articleMgr.getArticles("on sell"))
							{
								articles.add(article);
							}
						}
					}
					
					
				}
				
				//Si l'utilisateur a coché la checkbox "vente"
				if( request.getParameter("achat").equals("vente"))
				{
					request.setAttribute("vente", true);
					
					//Si l'utilisateur a coché le check "mes_ventes_ouvertes"
					if( request.getParameter("mes_ventes_ouvertes") != null)
					{	
	
						//On récupère les articles en vente actuellement par l'utilisateur
						if(articleMgr.getMyArticles( pseudo, "on sell") != null )
						{
							for( Article article : articleMgr.getMyArticles( pseudo, "on sell"))
							{
								articles.add(article);
							}
						}
						
						all = false;
						
					}	
					
					//Si l'utilisateur a coché le check "mes_ventes_futur"
					if( request.getParameter("mes_ventes_futur") != null)
					{
						//On récupère les futurs articles à vendre par l'utilisateur
						if(articleMgr.getMyArticles( pseudo, "future") != null )
						{
							for( Article article : articleMgr.getMyArticles( pseudo, "future"))
							{
								articles.add(article);
							}
						}
						
						all = false;
						
					}
					
					//Si l'utilisateur a coché le check "ventes_terminees"
					if( request.getParameter("ventes_terminees") != null)
					{
						//On récupère les articles déjà vendu par l'utilisateur
						if(articleMgr.getMyArticles( pseudo, "sold") != null )
						{
							for( Article article : articleMgr.getMyArticles( pseudo, "sold"))
							{
								articles.add(article);
							}
						}
						
						all = false;
						
					}
					
					//Si l'utilisateur n'a rien cocher on récupère tout les artcles
					if( all )
					{
						if(articleMgr.getMyArticles( pseudo, "sold") != null )
						{
							for( Article article : articleMgr.getMyArticles( pseudo, "all"))
							{
								articles.add(article);
							}
						}
					}
					
				}
				
			}
			
			if(  request.getParameter("achat") == null )
			{
				if( categorie == 0 )
				{
					for( Article article : articleMgr.getArticlesByName(recherche))
					{
						articles.add(article);
					}
				}
				else 
				{
					for( Article article : articleMgr.getArticlesByCategorie(recherche, categorie))
					{
						articles.add(article);
					}
				}
			}
			else
			{
				if( categorie == 0 )
				{
					articles = articleMgr.orderArticleByNames(articles, recherche);
				}
				else 
				{
					articles = articleMgr.orderArticleByCatAndNames(articles, recherche, categorie);
				}
			}
			
			
			request.setAttribute("listeArticles", articles);
		}
		catch( BLLException e)
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		
		if( rd != null)
		{
			rd.forward(request, response);
		}
	}
}

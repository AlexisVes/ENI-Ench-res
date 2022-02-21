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
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bo.Article;

/**
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//On vérifie que la liste d'articles n'est pas null
		if(articleMgr.getArticlesAvailable() != null && request.getAttribute("listeArticles") == null) { 
			//Récupérer la liste des articles disponibles à la vente en base de données et qui doivent être affichés sur la page d'accueil
			articlesAvailable = articleMgr.getArticlesAvailable();
			request.setAttribute("listeArticles", articlesAvailable);
		}
		
		if( request.getParameter("param") != null)
		{
			HttpSession session = ((HttpServletRequest)request).getSession();
			session.removeAttribute("connect");
		}
		
		request.setAttribute("listeCategories", categorieMgr.getCategories());
		
		
		
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
		String recherche = request.getParameter("search");
		int categorie = Integer.parseInt(request.getParameter("categorie"));
		List<Article> articles = new ArrayList<Article>();
		String pseudo = (String) session.getAttribute("connect");
		boolean all = true;
		
		
		
		
		request.setAttribute("listeArticles", articlesAvailable);
		request.setAttribute("listeCategories", categorieMgr.getCategories());
		
		if( request.getParameter("achat") != null)
		{	
			if( request.getParameter("achat").equals("achat") )
			{
				request.setAttribute("achat", true);
				
				if( request.getParameter("encheres_ouvertes") != null)
				{
					
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
			
			if( request.getParameter("achat").equals("vente"))
			{
				request.setAttribute("vente", true);
				
				if( request.getParameter("mes_ventes_ouvertes") != null)
				{	

					if(articleMgr.getMyArticles( pseudo, "on sell") != null )
					{
						for( Article article : articleMgr.getMyArticles( pseudo, "on sell"))
						{
							articles.add(article);
						}
					}
					
					all = false;
					
				}	
				if( request.getParameter("mes_ventes_futur") != null)
				{
					if(articleMgr.getMyArticles( pseudo, "future") != null )
					{
						for( Article article : articleMgr.getMyArticles( pseudo, "future"))
						{
							articles.add(article);
						}
					}
					
					all = false;
					
				}
				
				if( request.getParameter("ventes_terminees") != null)
				{
					if(articleMgr.getMyArticles( pseudo, "sold") != null )
					{
						for( Article article : articleMgr.getMyArticles( pseudo, "sold"))
						{
							articles.add(article);
						}
					}
					
					all = false;
					
				}
				
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
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
		
		if( rd != null)
		{
			rd.forward(request, response);
		}
	}
}

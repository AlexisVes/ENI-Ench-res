package fr.eni.encheres.controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.User;

/**
 * Servlet implementation class SellArticleServlet
 */
@WebServlet("/connect/sell_article")
public class SellArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public SellArticleServlet() 
    {
        super();
    }

    UserManager userManager = UserManager.getInstance();
    ArticleManager articleManager = ArticleManager.getInstance();
    CategorieManager categorieMgr = CategorieManager.getInstance();
    RetraitManager retraitMgr = RetraitManager.getInstance();
    EnchereManager enchereMgr = EnchereManager.getInstance();
    
	/**
	 * Affiche la page de vente d'article
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupère toutes les catégories de la base de données
		request.setAttribute("listeCategories", categorieMgr.getCategories());
		
		try 
		{
			//Récupère un objet User correspondant à l'utilisateur actuellement connecté
			User user = userManager.searchUser(request.getParameter("pseudo"));
			
			//Renvoi cet objet dans un attribut
			request.setAttribute("userProfil", user);
			
		}
		catch (BLLException e) 
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		//Remonter cette liste vers l'IHM qui va afficher les articles disponibles aux utilisateurs
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connect/sell_article.jsp");
			
		if( rd != null)
		{
			rd.forward(request, response);
		}
	}

	/**
	 * Récupère les données rentrées par l'utilisteur pour créer un nouvelle article en base
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupère les données rentrées dans les champs du formulaire par l'utilisateur
		//Et les insères dans une variable
		String nom = request.getParameter("article");

		String description = request.getParameter("description");
		
		int categorie = Integer.parseInt(request.getParameter("categorieSelect"));
		
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		
		LocalDate debutEnchere = LocalDate.parse(request.getParameter("debutEnchere"));
		
		LocalDate finEnchere = LocalDate.parse(request.getParameter("finEnchere"));
		
		int userID = Integer.parseInt(request.getParameter("user"));
		
		String rue = request.getParameter("rue");
		
		String codePostal = request.getParameter("codePostal");
		
		String ville = request.getParameter("ville");
		
		
		//Créer un objet Article
		Article article = new Article(nom, description, debutEnchere, finEnchere, miseAPrix, userID, categorie);
		
		try 
		{
			//Creer un nouvelle article dans la base de données
			articleManager.vendreArticle(article);
			
			int noArticle = articleManager.getArticle(nom).getNoArticle();
			
			//Créer un nouveau retrait dans la base de données
			Retrait retrait = new Retrait(noArticle,rue,codePostal,ville);
			retraitMgr.insertRetrait(retrait);
			
			//Créer un nouvelle objet Enchere dans la base de données
			Enchere enchere = new Enchere( debutEnchere, miseAPrix, noArticle, userID);
			enchereMgr.insertEnchere(enchere);
			
		} 
		catch (BLLException e) 
		{	
			request.setAttribute("message", e.getMessages());
			e.printStackTrace();
		}
		
		//Remonter cette liste vers l'IHM qui va afficher les articles disponibles aux utilisateurs
		response.sendRedirect( request.getContextPath() + "/home"); 
	}

}

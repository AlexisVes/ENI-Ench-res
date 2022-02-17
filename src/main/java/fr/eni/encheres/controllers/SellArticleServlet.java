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
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.User;

/**
 * Servlet implementation class SellArticleServlet
 */
@WebServlet("/connect/sell_article")
public class SellArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    UserManager userManager = UserManager.getInstance();
    ArticleManager articleManager = ArticleManager.getInstance();
    CategorieManager categorieMgr = CategorieManager.getInstance();
    RetraitManager retraitMgr = RetraitManager.getInstance();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("listeCategories", categorieMgr.getCategories());
		
		try {
			User user = userManager.searchUser(request.getParameter("pseudo"));
			
			request.setAttribute("userProfil", user);
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		Article article = new Article(nom, description, debutEnchere, finEnchere, miseAPrix, userID, categorie);
		
		try {
			articleManager.vendreArticle(article);
			
			int noArticle = articleManager.getArticle(nom).getNoArticle();
			
			Retrait retrait = new Retrait(noArticle,rue,codePostal,ville);
			
			retraitMgr.insertRetrait(retrait);
		} catch (BLLException e) {
			
			request.setAttribute("message", e.getMessages());
			
		}
		
		//Remonter cette liste vers l'IHM qui va afficher les articles disponibles aux utilisateurs
				RequestDispatcher rd = request.getRequestDispatcher("/home");
					
				if( rd != null)
				{
					rd.forward(request, response);
				}
	}

}

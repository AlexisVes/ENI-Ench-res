package fr.eni.encheres.controllers;

import java.io.IOException;
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
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.Article;

/**
 * Déconnecte et détruit un utilisateur, et le renvoi sur la page d'acceuil
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/connect/delete_user")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    UserManager userManager = UserManager.getInstance();
    ArticleManager articleManager = ArticleManager.getInstance();
    RetraitManager retraitManager = RetraitManager.getInstance();
    EnchereManager enchereManager = EnchereManager.getInstance();
    
	/**
	 * Supprime l'utilisateur, et le déconnecte, puis le renvoi sur la page d'acceuil du site
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		try 
		{
			HttpSession session = ((HttpServletRequest)request).getSession();
			
			String pseudo = (String) session.getAttribute("connect");
			
			List<Article> userArticles = articleManager.getMyArticles(pseudo, "all");
			
			
			for( Article article : userArticles )
			{
				retraitManager.deleteRetrait(articleManager.getArticle(article.getNomArticle()).getNoArticle());
				enchereManager.deleteEnchereByNoArticle(articleManager.getArticle(article.getNomArticle()).getNoArticle());
				articleManager.deleteArticle(articleManager.getArticle(article.getNomArticle()).getNoArticle());
			}
			
			//On supprime l'utilisateur
			userManager.deleteUser(request.getParameter("utilisateur"));
			
			//On déconnecte l'utilisateur si il a supprimé son propre compte, sinon c'est que la suppression vient d'un admin
			if (userManager.searchUser(pseudo).getAdmninistrateur() != 1) {
				session.removeAttribute("connect");
			}

		}
		catch (BLLException e) 
		{
			e.printStackTrace();
			request.setAttribute("message", e.getMessage());
		}
		
		//Remonter cette liste vers l'IHM qui va afficher les articles disponibles aux utilisateurs
				RequestDispatcher rd = request.getRequestDispatcher("/home");
				
				if( rd != null)
				{
					rd.forward(request, response);
				}
	}

	
	/**
	 * Fait appel au doGet
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}

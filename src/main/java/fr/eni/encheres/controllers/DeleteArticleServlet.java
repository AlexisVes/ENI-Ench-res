package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;

/**
 * Servlet implementation class DeleteArticleServlet
 */
@WebServlet("/connect/delete_article")
public class DeleteArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    ArticleManager articleMgr = ArticleManager.getInstance();
    EnchereManager enchereMgr = EnchereManager.getInstance();
    RetraitManager retraitMgr = RetraitManager.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noArticle = Integer.parseInt(request.getParameter("article"));
		
		try 
		{
			enchereMgr.deleteEnchereByNoArticle(noArticle);
			retraitMgr.deleteRetrait(noArticle);
			articleMgr.deleteArticle(noArticle);
		} 
		catch (BLLException e) 
		{	
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		//On affiche la page: details_ventes
			RequestDispatcher rd = request.getRequestDispatcher("/home");
					
			if( rd != null)
			{
				rd.forward(request, response);
			}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

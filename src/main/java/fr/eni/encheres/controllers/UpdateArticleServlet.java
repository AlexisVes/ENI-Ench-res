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
import fr.eni.encheres.bo.Article;

/**
 * Servlet implementation class UpdateArticleServlet
 */
@WebServlet("/connect/update_article")
public class UpdateArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateArticleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    ArticleManager articleMgr = ArticleManager.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nomArticle = request.getParameter("article");
		
		try 
		{
			Article article = articleMgr.getArticle(nomArticle);
			
			request.setAttribute("article", article);
		}
		catch (BLLException e)
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		//Renvoi à la page update_article.jsp
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connect/update_article.jsp");
				
		if( rd != null)
		{
			rd.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try
		{	
			String ancienNomArticle = request.getParameter("article");
			
			int noArticle = articleMgr.getArticle(ancienNomArticle).getNoArticle();
			
			String nomArticle = request.getParameter("nomArticle");
			String description = request.getParameter("description");
			LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
			LocalDate dateFin = LocalDate.parse(request.getParameter("dateFin"));
			int prix = Integer.parseInt(request.getParameter("prix"));
			
			articleMgr.updateArticleById(nomArticle, description, dateDebut, dateFin, prix, noArticle);
		}
		catch( BLLException e)
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		//On affiche la page d'accueil
		response.sendRedirect( request.getContextPath() + "/home"); 
		return;
	}

}

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

/**
 * Servlet implementation class EncherirServlet
 */
@WebServlet("/connect/encherir")
public class EncherirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	EnchereManager enchereMgr;
	ArticleManager articleMgr;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EncherirServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.sendRedirect( request.getContextPath() + "/home"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			//On récupère le prix saisi par l'utilisateur
			int prixSaisi = Integer.parseInt(request.getParameter("proposition"));
			//On s'appuie sur le manager Enchere pour contrôler que le prix saisi répond bien aux règles métier
			enchereMgr.controlerEnchere(prixSaisi, articleMgr.getArticle(request.getParameter("nomArticle")), 5);
		}
		catch( BLLException e)
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}

}

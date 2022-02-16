package fr.eni.encheres.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
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
    List<Article> articlesAvailable;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//On vérifie que la liste d'articles n'est pas null
		if(articleMgr.getArticlesAvailable() != null) {
			//Récupérer la liste des articles disponibles à la vente en base de données et qui doivent être affichés sur la page d'accueil
			articlesAvailable = articleMgr.getArticlesAvailable();
			request.setAttribute("listeArticles", articlesAvailable);
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

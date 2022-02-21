package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.CategorieManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class getDetailsOnSellServlet
 */
@WebServlet("/connect/sell_details")
public class getDetailsOnSellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getDetailsOnSellServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    CategorieManager categorieMgr = CategorieManager.getInstance();
    ArticleManager articleMgr = ArticleManager.getInstance();
    RetraitManager retraitMgr = RetraitManager.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Article article = articleMgr.getArticle(request.getParameter("nomArticle"));
		int noArticle = article.getNoArticle();
		
		//On vérifie que le libellé de catégorie n'est pas null
		if(categorieMgr.getLibelleCategorie(article.getNoCategorie()) != null){
			String libelleCategorie = categorieMgr.getLibelleCategorie(article.getNoCategorie());
			request.setAttribute("libelleCategorie", libelleCategorie);
		} 
		//On récupère le prix de départ de l'article 
		if(request.getParameter("nomArticle") != null) {
			request.setAttribute("article", article);
		}
		
		//On recupère le lieu du retrait que l'on envoie en attribut 
		try {
			Retrait retrait = retraitMgr.getRetrait(noArticle);
			request.setAttribute("retrait", retrait);
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
		
		
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connect/details_ventes.jsp");
				
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

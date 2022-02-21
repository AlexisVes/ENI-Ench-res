package fr.eni.encheres.controllers;

import java.io.IOException;

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
import fr.eni.encheres.bll.EnchereManager;
import fr.eni.encheres.bll.RetraitManager;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Enchere;
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
    EnchereManager enchereMgr = EnchereManager.getInstance();
    UserManager userMgr = UserManager.getInstance();

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
		
		//On récupère l'enchère en cours (si elle existe...)
		if (enchereMgr.getEnchere(article.getNoArticle()) != null) {
			Enchere enchere = enchereMgr.getEnchere(article.getNoArticle());
			request.setAttribute("enchere", enchere.getMontant_enchere()) ;
			try {
				request.setAttribute("encherisseur", userMgr.getUserById(enchere.getNo_utilisateur()).getPseudo());
			} catch (DALException e) {
				e.printStackTrace();
			}
			
		}
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		String pseudo = (String) session.getAttribute("connect");
		
		int userId = 0;
		
		try {
			userId = userMgr.searchUser(pseudo).getUserId();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int proposition = 0;
		System.out.println("oui" + request.getParameter("proposition"));
		if( request.getParameter("proposition") != null)
		{
			proposition = Integer.parseInt(request.getParameter("proposition"));
		}
		
		enchereMgr.controlerEnchere(proposition, article, userId);
		
		
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
		
		doGet(request, response);

	}
}

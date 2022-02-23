package fr.eni.encheres.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
 * Affiche les différents détails sur une vente
 * Servlet implementation class getDetailsOnSellServlet
 */
@WebServlet("/connect/sell_details")
public class getDetailsOnSellServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

    public getDetailsOnSellServlet() 
    {
        super();
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
		
		try 
		{
			//Récupère l'objet Article correspondant à celui dont on veux afficher les détails
			Article article = articleMgr.getArticle(request.getParameter("nomArticle"));
			
			//Récupère le numéro d'article de notre article
			int noArticle = article.getNoArticle();
			
			request.setAttribute("nomVendeur", userMgr.getUserById(article.getNoUtilisateur()));
			
			//On vérifie que le libellé de catégorie n'est pas null
			if(categorieMgr.getLibelleCategorie(article.getNoCategorie()) != null)
			{
				//On récupère dans une variable le libellé de notre catégorie
				String libelleCategorie = categorieMgr.getLibelleCategorie(article.getNoCategorie());
				//Et on l'insère dans un attribut
				request.setAttribute("libelleCategorie", libelleCategorie);
			} 
			
			//On récupère le prix de départ de l'article 
			if(request.getParameter("nomArticle") != null) 
			{
				request.setAttribute("article", article);
			}
			
			//On recupère le lieu du retrait que l'on envoie en attribut 
			Retrait retrait = retraitMgr.getRetrait(noArticle);
			request.setAttribute("retrait", retrait);
		
			
			//On récupère l'enchère en cours (si elle existe...)
			if (enchereMgr.getEnchere(article.getNoArticle()) != null) 
			{
				//Récupère l'objet Enchere pour notre article
				Enchere enchere = enchereMgr.getEnchere(article.getNoArticle());
				//Renvoi le montant de l'enchère dans un attribut
				request.setAttribute("enchere", enchere.getMontant_enchere()) ;
			
				//Renvoi dans un attribut le nom de l'encherisseur
				request.setAttribute("encherisseur", userMgr.getUserById(enchere.getNo_utilisateur()).getPseudo());		
				
				final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-YYYY HH:mm");
				request.setAttribute("dateEnchere", enchere.getDate_enchere().format(FORMATTER));
			}
			
			
			HttpSession session = ((HttpServletRequest)request).getSession();
			
			//On récupère le pseudo de l'utilisateur en cours
			String pseudo = (String) session.getAttribute("connect");
			
			if( LocalDate.now().isBefore(article.getDateFinEncheres()) && article.getNoUtilisateur() == userMgr.getIdByPseudo(pseudo))
			{
				request.setAttribute("delete", "delete");
			}
			
			if( LocalDate.now().isAfter(article.getDateFinEncheres()) )
			{
				request.setAttribute("end", "end");
			}
			
			if( LocalDate.now().isBefore(article.getDateDebutEncheres()) && article.getNoUtilisateur() == userMgr.getIdByPseudo(pseudo))
			{
				request.setAttribute("update", "update");
			}
		
		}
		catch( BLLException | DALException e )
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		//On affiche la page: details_ventes
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
		
		try
		{
			//Récupère l'objet Article correspondant à celui dont on veux afficher les détails
			Article article = articleMgr.getArticle(request.getParameter("nomArticle"));
			
			HttpSession session = ((HttpServletRequest)request).getSession();
			
			//On récupère le pseudo de l'utilisateur en cours
			String pseudo = (String) session.getAttribute("connect");

			int userId = 0;
			
			//On récupère le numéro d'utilisateur de l'utilisateur actuellement connecté
			userId = userMgr.searchUser(pseudo).getUserId();
			
			int proposition = 0;
			
			//Si l'utilisateur a fait une proposition d'enchère, on l'a récupère
			if( request.getParameter("proposition") != null)
			{
				proposition = Integer.parseInt(request.getParameter("proposition"));
				
				//On met à jour l'enchère de notre article
				enchereMgr.gererEnchere(proposition, article, userId);
			}	
		}
		catch(BLLException e)
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		//On affiche la page: details_ventes
		response.sendRedirect( request.getContextPath() + "/home"); 
		return;	
	}
}


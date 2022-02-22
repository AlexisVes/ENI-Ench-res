package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.User;

/**
 * Affiche le profil d'un utilisateur
 * Servlet implementation class DisplayProfilServlet
 */
@WebServlet("/connect/profil")
public class DisplayProfilServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       

    public DisplayProfilServlet()
    {
        super();
    }
    
    RegisterServlet userVerification = new RegisterServlet();
    UserManager userManager = UserManager.getInstance();

	/**
	 * Affiche le profil d'un utilisateur
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			//Récupère l'objet utilisateur correspondant à celui recherché
			User user = userManager.searchUser(request.getParameter("pseudo"));
			
			//Renvoi l'objet utilisateur en attribut
			request.setAttribute("userProfil", user);
			
		} 
		catch (BLLException e) 
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		//Affiche la page profil
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connect/profil.jsp");
		
		if( rd != null)
		{
			rd.forward(request, response );
		}
		
	}

	/**
	 * Appel la méthode doGet, et affiche donc le profil d'un utilisateur
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		doGet(request, response);
		
	}
}

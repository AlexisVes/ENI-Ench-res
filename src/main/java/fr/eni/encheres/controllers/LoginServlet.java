package fr.eni.encheres.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bll.UserManager;

/**
 * Affiche la page de connection, et permet de l'utilisateur de se connecter
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginServlet() 
    {
        super();
    }
    
    UserManager userManager = UserManager.getInstance();

	/**
	 * Affiche la page login.jsp
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/login.jsp");
		
		if( rd != null)
		{
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pseudo = "";
		
		//Si l'utilisateur a bien rentré un pseudo, nous l'assimilons à une variable pseudo
		if ( request.getParameter("pseudo") != null || request.getParameter("pseudo") != "") 
		{
			pseudo = request.getParameter("pseudo").toLowerCase();		
		}
		
		String password = "";
		
		//Si l'utilisateur a bien rentré un mot de passe, nous l'assimilons à une variable password
		if ( request.getParameter("password") != null || request.getParameter("password") != "") 
		{	
			password = request.getParameter("password");
		}
		
		// Nous vérifions si les données rentrées par l'utilisateurs correspondent à un utilisateurs dans notre base de données
		// Si oui on ouvre une session qui correspond à une connection réussie
		try 
		{
			//Si nous trouvons bien un utilisateur avec ce pseudo associé, au bon mot de passe
			if ( userManager.searchUser(pseudo, password) == true ){
				
				HttpSession session = ((HttpServletRequest)request).getSession();
				
				//Si l'utilisateur a coché "se souvenir"
				if(  request.getParameter("souvenir") != null )
				{
					// On créée de nouveaux objets Cookie
					Cookie login = new Cookie("login", pseudo);  
					
					// On définit leurs durées maximum
					login.setMaxAge(24*3600); 
					
					// On envoi les cookie via HTTP 
					response.addCookie(login);			
				}
				
				// On crée un attribut de session connect, son éxistance détermine que l'utilisateur est crée
				session.setMaxInactiveInterval(300);
				session.setAttribute("connect", pseudo);
				
				//On affiche la page d'accueil
				response.sendRedirect( request.getContextPath() + "/home"); 
				return;
			}

		} 
		catch (BLLException e) 
		{
			request.setAttribute("message", e.getMessages());
		}
		
		doGet(request, response);
		
	}

}

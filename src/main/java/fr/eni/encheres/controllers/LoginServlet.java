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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    UserManager userManager = UserManager.getInstance();

	/**
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
		if ( request.getParameter("pseudo") != null || request.getParameter("pseudo") != "") {
			
			pseudo = request.getParameter("pseudo");
			
		}
		
		String password = "";
		
		//Si l'utilisateur a bien rentré un mot de passe, nous l'assimilons à une variable password
		if ( request.getParameter("password") != null || request.getParameter("password") != "") {
			
			password = request.getParameter("password");
			
		}
		
		// Nous vérifions si les données rentrées par l'utilisateurs correspondent à un utilisateurs dans notre base de données
		// Si oui on ouvre une session qui correspond à une connection réussie
		try {
			if ( userManager.searchUser(pseudo, password) == true ){
				
				HttpSession session = ((HttpServletRequest)request).getSession();
				
				if(  request.getParameter("souvenir") != null )
				{
					// create objet cookie 
					Cookie login = new Cookie("login", pseudo); 
					Cookie mdp = new Cookie("password", password); 
					// set the validity 
					login.setMaxAge(24*3600); 
					mdp.setMaxAge(24*3600);
					// send cookie via HTTP 
					response.addCookie(login);
					response.addCookie(mdp);				
				}
				
				
				session.setAttribute("connect", pseudo);
				
				response.sendRedirect("http://localhost:9189/ENI-Encheres/home"); 
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

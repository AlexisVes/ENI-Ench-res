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

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    UserManager userManager = UserManager.getInstance();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/register.jsp");
		
		if( rd != null)
		{
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String pseudo = null;
			String nom = null;
			String prenom = null;
			String email = null;
			String tel = null;
			String rue = null;
			String code_postal = null;
			String ville = null;
			String password = null;
			boolean echec = false;
			
			ControllersException exception = new ControllersException();
			
			if(!request.getParameter("pseudo").isEmpty())
			{
				pseudo = request.getParameter("pseudo");
			}
			else
			{
				exception.addMessage("Veuillez rentrer un pseudo");
				echec = true;

			}
			
			
			if(!request.getParameter("nom").isEmpty())
			{
				nom = request.getParameter("nom");
			}
			else
			{
				exception.addMessage("Veuillez rentrer un nom");
				echec = true;
			}
			
			
			if(!request.getParameter("prenom").isEmpty())
			{
				prenom = request.getParameter("prenom");
			}
			else
			{
				exception.addMessage("Veuillez rentrer un prénom");
				echec = true;
			}
			
			
			if(!request.getParameter("email").isEmpty())
			{
				email = request.getParameter("email");
			}
			else
			{
				exception.addMessage("Veuillez rentrer un email");
				echec = true;
			}
			
			
			if(!request.getParameter("tel").isEmpty())
			{
				tel = request.getParameter("tel");
			}
			else
			{
				exception.addMessage("Veuillez rentrer un téléphone");
				echec = true;

			}
			
			
			if(!request.getParameter("rue").isEmpty())
			{
				rue = request.getParameter("rue");
			}
			else
			{
				exception.addMessage("Veuillez rentrer une rue");
				echec = true;

			}
			
			
			if(!request.getParameter("code_postal").isEmpty())
			{
				code_postal = request.getParameter("code_postal");
			}
			else
			{
				exception.addMessage("Veuillez rentrer un code postal");
				echec = true;

			}
			
			
			if( !request.getParameter("ville").isEmpty())
			{
				ville = request.getParameter("ville");
			}
			else
			{
				exception.addMessage("Veuillez rentrer une ville");
				echec = true;

			}
			
			
			if(request.getParameter("password") != "")
			{
				password = request.getParameter("password");
			}
			else
			{
				exception.addMessage("Veuillez rentrer un mot de passe");
				echec = true;
			}
			
			if( echec == false )
			{
				userManager.createUser(pseudo, nom, prenom, email, tel, rue, code_postal, ville, password);	
			}
			
			if( exception.hasErrors())
			{
				throw exception;
			}
			
		} 
		catch (BLLException e) 
		{	
			System.out.println(e.getMessages());
			request.setAttribute("message", e.getMessages());
		}
		
		doGet(request, response);
	}

}

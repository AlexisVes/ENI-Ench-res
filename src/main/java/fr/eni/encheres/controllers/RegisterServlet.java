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
import fr.eni.encheres.dal.DALException;

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
			boolean reussite = false;
			
			if(request.getParameter("pseudo") != null)
			{
				pseudo = request.getParameter("pseudo");
			}
			else
			{
				request.setAttribute("message", "Veuillez rentrer un mot de passe");
				reussite = true;
			}
			
			
			if(request.getParameter("nom") != null)
			{
				nom = request.getParameter("nom");
			}
			else
			{
				request.setAttribute("message", "Veuillez rentrer un mot de passe");
				reussite = true;
			}
			
			
			if(request.getParameter("prenom") != null)
			{
				prenom = request.getParameter("prenom");
			}
			else
			{
				request.setAttribute("message", "Veuillez rentrer un mot de passe");
				reussite = true;
			}
			
			
			if(request.getParameter("email") != null)
			{
				email = request.getParameter("email");
			}
			else
			{
				request.setAttribute("message", "Veuillez rentrer un mot de passe");
				reussite = true;
			}
			
			
			if(request.getParameter("tel") != null)
			{
				tel = request.getParameter("tel");
			}else
			{
				request.setAttribute("message", "Veuillez rentrer un mot de passe");
				reussite = true;
			}
			
			
			if(request.getParameter("rue") != null)
			{
				rue = request.getParameter("rue");
			}
			else
			{
				request.setAttribute("message", "Veuillez rentrer un mot de passe");
				reussite = true;
			}
			
			
			if(request.getParameter("code_postal") != null)
			{
				code_postal = request.getParameter("code_postal");
			}
			else
			{
				request.setAttribute("message", "Veuillez rentrer un mot de passe");
				reussite = true;
			}
			
			
			if(request.getParameter("ville") != null)
			{
				ville = request.getParameter("ville");
			}
			else
			{
				request.setAttribute("message", "Veuillez rentrer un mot de passe");
				reussite = true;
			}
			
			
			if(request.getParameter("password") != null)
			{
				password = request.getParameter("password");
			}
			else
			{
				request.setAttribute("message", "Veuillez rentrer un mot de passe");
				reussite = true;
			}
			
			if( !reussite )
			{
				try {
					userManager.createUser(pseudo, nom, prenom, email, tel, rue, code_postal, ville, password);
				} catch (DALException e) {
					request.setAttribute("message", e.getMessage());;
				}		
			}
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		doGet(request, response);
	}

}

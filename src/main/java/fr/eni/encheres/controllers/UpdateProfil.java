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
 * Servlet implementation class UpdateProfil
 */
@WebServlet("/connect/update_profil")
public class UpdateProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UpdateProfil() 
    {
        super();
    }
    
    UserManager userManager = UserManager.getInstance();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try 
		{
			//Créer un objet User de l'utilisateur connecté
			User user = userManager.searchUser(request.getParameter("pseudo"));
			
			//Le renvoi dans un attribut
			request.setAttribute("userProfil", user);
			
		}
		catch (BLLException e) 
		{
			request.setAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		
		//Renvoi à la page update_profil.jsp
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connect/update_profil.jsp");
		
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
			
			//Si l'utilisateur a rentrer son bon mot de passe
			boolean verification = userManager.searchUser(request.getParameter("pseudo"), request.getParameter("password"));
			
			if( verification )
			{

				User userCredit = userManager.searchUser(request.getParameter("pseudo"));

				String pseudo = null;
				String nom = null;
				String prenom = null;
				String email = null;
				String tel = null;
				String rue = null;
				String code_postal = null;
				String ville = null;
				String password = null;
				int userId = userCredit.getUserId();
				int credit = userCredit.getCredit();
				
				
				//On créer une exception pour pouvoir lui attribuer des messages d'erreurs
				ControllersException exception = new ControllersException();
				
				//Si l'utilisateur n'a pas rentré de pseudo, on rajoute une message à notre exception
				if(!request.getParameter("pseudo").isEmpty())
				{
					pseudo = request.getParameter("pseudo");
				}
				else
				{
					exception.addMessage("Veuillez rentrer un pseudo");
				}
				
				
				//Si l'utilisateur n'a pas rentré de nom, on rajoute une message à notre exception
				if(!request.getParameter("nom").isEmpty())
				{
					nom = request.getParameter("nom");
				}
				else
				{
					exception.addMessage("Veuillez rentrer un nom");
				}
				
				
				//Si l'utilisateur n'a pas rentré de prénom, on rajoute une message à notre exception
				if(!request.getParameter("prenom").isEmpty())
				{
					prenom = request.getParameter("prenom");
				}
				else
				{
					exception.addMessage("Veuillez rentrer un prénom");
				}
				
				
				//Si l'utilisateur n'a pas rentré de de mail, on rajoute une message à notre exception
				if(!request.getParameter("email").isEmpty())
				{
					email = request.getParameter("email");
				}
				else
				{
					exception.addMessage("Veuillez rentrer un email");
				}
				
				
				//Si l'utilisateur n'a pas rentré de numéro, on rajoute une message à notre exception
				if(!request.getParameter("tel").isEmpty())
				{
					tel = request.getParameter("tel");
				}
				else
				{
					exception.addMessage("Veuillez rentrer un téléphone") ;
				}
				
				
				//Si l'utilisateur n'a pas rentré de rue, on rajoute une message à notre exception
				if(!request.getParameter("rue").isEmpty())
				{
					rue = request.getParameter("rue");
				}
				else
				{
					exception.addMessage("Veuillez rentrer une rue");
				}
				
				
				//Si l'utilisateur n'a pas rentré de code postal, on rajoute une message à notre exception
				if(!request.getParameter("code_postal").isEmpty())
				{
					code_postal = request.getParameter("code_postal");
				}
				else
				{
					exception.addMessage("Veuillez rentrer un code postal");
				}
				
				
				//Si l'utilisateur n'a pas rentré de ville, on rajoute une message à notre exception
				if( !request.getParameter("ville").isEmpty())
				{
					ville = request.getParameter("ville");
				}
				else
				{
					exception.addMessage("Veuillez rentrer une ville");
				}
				
				
				//Si l'utilisateur n'a pas rentré de mot de passe, on rajoute une message à notre exception
				if(request.getParameter("new_password") != "")
				{
					password = request.getParameter("new_password");
				}
				else
				{
					password = request.getParameter("password");
				}
				
				
				//Si l'exception contient des messages d'erreurs, nous la jetons
				// Sinon nous créeons l'utilisateur
				if( exception.hasErrors())
				{
					throw exception;
				}
				else
				{
					User user = new User( userId, pseudo, nom, prenom, email, tel, rue, code_postal, ville, password, credit, (byte) 0);	
					
					userManager.updateUser(user);

					RequestDispatcher rd = request.getRequestDispatcher("/home");
					
					request.setAttribute("confirmation", "La modification demandée a bien été effectuée");
					
					if( rd != null)
					{
						rd.forward(request, response);
						return;
					}

				}
				
			}
			
			
		} catch (BLLException e) 
		{
			request.setAttribute("message", e.getMessages());
			e.printStackTrace();
		}
		catch (ControllersException e)
		{
			request.setAttribute("message", e.getMessages());
			e.printStackTrace();
		} 
		
		doGet(request, response);
		
	}
		

}

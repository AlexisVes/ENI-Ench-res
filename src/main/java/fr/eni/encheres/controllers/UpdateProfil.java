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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfil() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    UserManager userManager = UserManager.getInstance();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			User user = userManager.searchUser(request.getParameter("pseudo"));
			
			request.setAttribute("userProfil", user);
			
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
			
			boolean verification = userManager.searchUser(request.getParameter("pseudo"), request.getParameter("password"));
			
			if( verification )
			{
		
				User user = userManager.searchUser(request.getParameter("pseudo"));
				
				userManager.updateUser(user);
				
			}
			
			doGet(request, response);
			
			
		} catch (BLLException e) {
			request.setAttribute("message", e.getMessages());
		} 
		
	}
		

}

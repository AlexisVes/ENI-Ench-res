package fr.eni.encheres.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.bo.User;
import fr.eni.encheres.dal.DALException;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/administrator/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	UserManager userMgr = UserManager.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//On récupère la liste des users en BDD afin de la mettre à disposition de l'administrateur
		List<User> allUsers = null;
		
		try {
			allUsers = userMgr.selectAllUsers();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("allUsers", allUsers);
		
		//rediriger vers la JSP admin qui permet de supprimer des comptes utilisateur 
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/administrator/admin.jsp");
		if(rd != null) {
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

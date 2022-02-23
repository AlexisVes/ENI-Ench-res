package fr.eni.encheres.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.UserManager;
import fr.eni.encheres.dal.DALException;

/**
 * Servlet Filter implementation class AdminFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
, urlPatterns = { "/administrator/*" })
public class AdminFilter implements Filter {

	UserManager userMgr = UserManager.getInstance();
    /**
     * Default constructor. 
     */
    public AdminFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		String pseudo = (String) session.getAttribute("connect");
		
		byte admin = 0;
		try 
		{
			admin = userMgr.getUserById(userMgr.getIdByPseudo(pseudo)).getAdmninistrateur();
		} 
		catch (DALException e) 
		{
			e.printStackTrace();
		}
		
		if( admin == 1 ) 
		{
			chain.doFilter(request, response);
		}
		else 
		{
			((HttpServletRequest)request).setAttribute("message", "Vous n'êtes pas administrateur");
			
			RequestDispatcher rd = ((HttpServletRequest)request).getRequestDispatcher("/home");
			
			if(rd != null) 
			{
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

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

/**
 * Servlet Filter implementation class ConnectFilter
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
, urlPatterns = { "/connect/*" })
public class ConnectFilter implements Filter {

    /**
     * Default constructor. 
     */
    public ConnectFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		
		if(session.getAttribute("connect") != null) 
		{
			chain.doFilter(request, response);
		}
		else 
		{
			((HttpServletRequest)request).setAttribute("message", "Vous n'êtes pas connecté");
			
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

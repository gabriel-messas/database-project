package trab;

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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}, urlPatterns = { "/loginFilter" })
public class LoginFilter implements Filter {

    public LoginFilter() {
        
    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);
        
        if ((session == null || session.getAttribute("loggedInUser") == null) && (!request.getRequestURL().toString().contains("login") && !request.getRequestURL().toString().contains("register"))) {
            response.sendRedirect("login.jsp");
        } else {
            chain.doFilter(request, response);
        }
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}

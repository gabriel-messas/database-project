package trab;
 
import java.io.*;
import java.sql.SQLException;
 
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public RegisterServlet() {
        super();
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
         
        UserDAO userDao = new UserDAO();
        if(userDao.register(username, password)) {
        	String destPage = "login.jsp";
            
            String message = "Registered successfully, now login";
            request.setAttribute("message2", message);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
            
            try {
            	Thread.sleep (3000); 
            }
            catch (InterruptedException e) {
            	throw new ServletException(e);
            }
         
        }
        else {
        	String destPage = "register.jsp";
        	
        	String message = "An error has happened, please try again";
            request.setAttribute("message2", message);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
            dispatcher.forward(request, response);
        }
        
        
             
        
    }
}
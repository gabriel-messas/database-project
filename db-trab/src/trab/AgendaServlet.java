package trab;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/agenda")
public class AgendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AgendaServlet() {
        super();
    }
    
    public List<Contato> listaContatos;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listaContatos = new LinkedList<Contato>();
		
		AgendaDAO agdao = new AgendaDAO();
		
		listaContatos = agdao.buscarPorNome("");
		
		request.setAttribute("listaContatos", listaContatos);		
		
		RequestDispatcher view = request.getRequestDispatcher("agenda.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

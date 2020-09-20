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
import javax.servlet.http.HttpSession;

@WebServlet("/buscarContato")
public class BuscarContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BuscarContatoServlet() {
        super();
    }
    
    public List<Contato> listaContatos; 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AgendaDAO agdao = new AgendaDAO(); 

		String nome = request.getParameter("nome");
		
		this.listaContatos = agdao.buscarPorNome(nome);
		
		request.setAttribute("listaContatos", listaContatos);
		
		RequestDispatcher view = request.getRequestDispatcher("agenda.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response, List<Produto> c) throws ServletException, IOException {

	}

}

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

@WebServlet("/buscarClientes")
public class BuscarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BuscarClienteServlet() {
        super();
    }
    
    public List<Contato> listaClientes;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listaClientes = new LinkedList<Contato>();
		
		AgendaDAO agdao = new AgendaDAO();
		
		listaClientes = agdao.buscarClientesPorNome("");
		
		request.removeAttribute("listaClientes");
		request.setAttribute("listaClientes", listaClientes);		
		
		RequestDispatcher view = request.getRequestDispatcher("clientes.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

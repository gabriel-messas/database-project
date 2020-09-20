package trab;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/selecionarCliente")
public class SelecionarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelecionarClienteServlet() {
        super();
        this.cliente = SelecionarClienteServlet.lastCliente;
    }

    public Contato cliente;
    public static Contato lastCliente;
	public Contato getCliente() {
		return this.cliente;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Contato cliente = new Contato();
		
		AgendaDAO agdao = new AgendaDAO();
		
		cliente = agdao.buscarPorId(Integer.parseInt(request.getParameter("id")));
			
		session.setAttribute("clienteVenda", cliente);
		
		SelecionarClienteServlet.lastCliente = cliente;
		
		RequestDispatcher view = request.getRequestDispatcher("cliente-escolhido.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

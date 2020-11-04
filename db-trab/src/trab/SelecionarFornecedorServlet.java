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

@WebServlet("/selecionarFornecedor")
public class SelecionarFornecedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelecionarFornecedorServlet() {
        super();
        this.fornecedor = SelecionarFornecedorServlet.lastFornecedor;
    }

    public Contato fornecedor;
    public static Contato lastFornecedor;
	public Contato getFornecedor() {
		return this.fornecedor;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Contato fornecedor = new Contato();
		
		AgendaDAO agdao = new AgendaDAO();
		
		fornecedor = agdao.buscarPorId(Integer.parseInt(request.getParameter("id")));
			
		session.setAttribute("fornecedor", fornecedor);
		
		SelecionarFornecedorServlet.lastFornecedor = fornecedor;
		
		RequestDispatcher view = request.getRequestDispatcher("fornecedor-escolhido.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

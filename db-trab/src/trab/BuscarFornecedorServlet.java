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

@WebServlet("/buscarFornecedores")
public class BuscarFornecedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BuscarFornecedorServlet() {
        super();
    }
    
    public List<Contato> listaFornecedores;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listaFornecedores = new LinkedList<Contato>();
		
		AgendaDAO agdao = new AgendaDAO();
		
		listaFornecedores = agdao.buscarFornecedoresPorNome("");
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("listaFornecedores");
		session.setAttribute("listaFornecedores", listaFornecedores);
		
		
		RequestDispatcher view = request.getRequestDispatcher("fornecedores.jsp");
		
		view.forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

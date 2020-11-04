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

@WebServlet("/buscarFornecedoresEndereco")
public class BuscarFornecedorEnderecoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BuscarFornecedorEnderecoServlet() {
        super();
    }
    
    public List<Contato> listaFornecedores; 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AgendaDAO agdao = new AgendaDAO(); 

		String nome = request.getParameter("nome");
		
		this.listaFornecedores = agdao.buscarFornecedoresPorEndereco(nome);
		
		request.setAttribute("listaFornecedores", listaFornecedores);
		
		RequestDispatcher view = request.getRequestDispatcher("fornecedores.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response, List<Produto> c) throws ServletException, IOException {

	}

}

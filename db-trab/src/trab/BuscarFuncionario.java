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

@WebServlet("/buscarFuncionarios")
public class BuscarFuncionario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BuscarFuncionario() {
        super();
    }
    
    public List<Funcionario> listaFuncionarios; 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FuncionarioDAO fndao = new FuncionarioDAO(); 

		String nome = request.getParameter("nome");
		if(nome == null) {
			this.listaFuncionarios = fndao.buscarPorNome("");
		}
		else {
			this.listaFuncionarios = fndao.buscarPorNome(nome);
		}
		
		request.setAttribute("listaFuncionarios", listaFuncionarios);
		
		RequestDispatcher view = request.getRequestDispatcher("funcionarios.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response, List<Produto> c) throws ServletException, IOException {

	}

}

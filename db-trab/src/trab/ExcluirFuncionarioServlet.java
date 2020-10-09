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

@WebServlet("/excluirFuncionario")
public class ExcluirFuncionarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ExcluirFuncionarioServlet() {
        super();
    }

    public long idLastFuncionarioDeleted;
    
    public long getIdLastFuncionarioDeleted() {
		return this.idLastFuncionarioDeleted;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FuncionarioDAO fndao = new FuncionarioDAO();
		Funcionario lastFuncionarioRemoved = fndao.buscarPorId(Integer.parseInt(request.getParameter("id")));
		
		fndao = new FuncionarioDAO();
		fndao.remove(Long.parseLong(request.getParameter("id")));
		
		this.idLastFuncionarioDeleted = Long.parseLong(request.getParameter("id"));
		
		request.setAttribute("lastFuncionarioRemoved", lastFuncionarioRemoved);
		
		RequestDispatcher view = request.getRequestDispatcher("excluir-funcionario-resposta.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

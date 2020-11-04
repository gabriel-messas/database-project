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

@WebServlet("/selecionarFuncionario")
public class SelecionarFuncionarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SelecionarFuncionarioServlet() {
        super();
        this.funcionario = SelecionarFuncionarioServlet.lastFuncionario;
    }

    public Funcionario funcionario;
    public static Funcionario lastFuncionario;
	public Funcionario getFuncionario() {
		return this.funcionario;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Funcionario funcionario = new Funcionario();
		
		FuncionarioDAO fndao = new FuncionarioDAO();
		
		funcionario = fndao.buscarPorId(Integer.parseInt(request.getParameter("id")));
			
		session.setAttribute("funcionarioVenda", funcionario);
		
		SelecionarFuncionarioServlet.lastFuncionario = funcionario;
		
		RequestDispatcher view = request.getRequestDispatcher("funcionario-escolhido.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

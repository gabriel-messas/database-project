package trab;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/alterarFuncionario")
public class AlterarFuncionarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AlterarFuncionarioServlet() {
        super();
        this.lastFuncionarioAlterado = AlterarFuncionarioServlet.funcionarioAux;
    }

	public static Funcionario funcionarioAux;
    public Funcionario lastFuncionarioAlterado;
    
    public Funcionario getLastFuncionarioAlterado() {
		return this.lastFuncionarioAlterado;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt((request.getParameter("id")));
		FuncionarioDAO fndao = new FuncionarioDAO();
		Funcionario funcionario = fndao.buscarPorId(id);
		
		request.setAttribute("funcionario", funcionario);
		
		AlterarFuncionarioServlet.funcionarioAux = funcionario;
		this.lastFuncionarioAlterado = funcionario;
		
		RequestDispatcher view = request.getRequestDispatcher("efetuar-alteracao-funcionario.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

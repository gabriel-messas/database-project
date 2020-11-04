package trab;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adicionarFuncionario")
public class AdicionarFuncionarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdicionarFuncionarioServlet() {
        super();
        this.funcionario = AdicionarFuncionarioServlet.lastFuncionarioAdded;
    }

    public Funcionario funcionario;
    public static Funcionario lastFuncionarioAdded;
	public Funcionario getFuncionario() {
		return this.funcionario;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(request.getParameter("nome"));
		try {
			funcionario.setIdade(Integer.parseInt(request.getParameter("idade")));
			funcionario.setSalario(Double.parseDouble(request.getParameter("salario")));
		}
		catch(NumberFormatException nfe) {
			funcionario.setIdade(0);
			funcionario.setSalario(0);
		}
		
		funcionario.setHoraInicio(request.getParameter("horaInicio"));
		funcionario.setHoraFim(request.getParameter("horaFim"));
		
		FuncionarioDAO fndao = new FuncionarioDAO();
		fndao.inserir(funcionario);
		
		this.funcionario = funcionario;
		AdicionarFuncionarioServlet.lastFuncionarioAdded = funcionario;
		
		RequestDispatcher view = request.getRequestDispatcher("adicionar-funcionario-resposta.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

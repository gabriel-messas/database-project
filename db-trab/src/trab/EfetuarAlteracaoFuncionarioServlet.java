package trab;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/efetuarAlteracaoFuncionario")
public class EfetuarAlteracaoFuncionarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EfetuarAlteracaoFuncionarioServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FuncionarioDAO fndao = new FuncionarioDAO();
		
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome(request.getParameter("nome"));
		funcionario.setIdade(Integer.parseInt(request.getParameter("idade")));
		funcionario.setSalario(Double.parseDouble(request.getParameter("salario")));
		funcionario.setHoraInicio(request.getParameter("horaInicio"));
		funcionario.setHoraFim(request.getParameter("horaFim"));
		funcionario.setId(Integer.parseInt(request.getParameter("id")));
		
		fndao.alterar(funcionario);
		
		RequestDispatcher view = request.getRequestDispatcher("alterar-funcionario-resposta.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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

@WebServlet("/efetuarAlteracaoContato")
public class EfetuarAlteracaoContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EfetuarAlteracaoContatoServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AgendaDAO agdao = new AgendaDAO();
		
		Contato contato = new Contato();
		
		contato.setId(Integer.parseInt(request.getParameter("id")));
		contato.setNome(request.getParameter("nome"));
		contato.setApelido(request.getParameter("apelido"));
		contato.setEmpresa(request.getParameter("empresa"));
		contato.setEndereco(request.getParameter("endereco"));
		contato.setBairro(request.getParameter("bairro"));
		contato.setCidade(request.getParameter("cidade"));
		contato.setTelefone1(request.getParameter("telefone1"));
		contato.setTelefone2(request.getParameter("telefone2"));
		contato.setTelefone3(request.getParameter("telefone3"));
		contato.setObservacao(request.getParameter("observacao"));
		//System.out.println(request.getParameter("cliente"));
		
		Contato contato2 = new Contato();
		contato2 = agdao.buscarPorId(contato.getId());
		
		contato.setCliente(contato2.getCliente());
		
		agdao.alterar(contato);
		
		if(contato.getCliente() == 1) {
			RequestDispatcher view = request.getRequestDispatcher("alterar-cliente-resposta.jsp");
			view.forward(request, response);
		}
		else {
			RequestDispatcher view = request.getRequestDispatcher("alterar-fornecedor-resposta.jsp");
			view.forward(request, response);
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

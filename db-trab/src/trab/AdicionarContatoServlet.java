package trab;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adicionarContato")
public class AdicionarContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdicionarContatoServlet() {
        super();
        this.contato = AdicionarContatoServlet.lastContatoAdded;
    }

    public Contato contato;
    public static Contato lastContatoAdded;
	public Contato getContato() {
		return this.contato;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Contato contato = new Contato();
		
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
		contato.setCliente(Integer.parseInt(request.getParameter("cliente")));
		
		AgendaDAO agdao = new AgendaDAO();
		agdao.inserir(contato);
		
		this.contato = contato;
		AdicionarContatoServlet.lastContatoAdded = contato;
		
		RequestDispatcher view = request.getRequestDispatcher("adicionar-contato-resposta.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

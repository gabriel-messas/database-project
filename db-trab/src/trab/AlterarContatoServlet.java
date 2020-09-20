package trab;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/alterarContato")
public class AlterarContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AlterarContatoServlet() {
        super();
        this.lastContatoAlterado = AlterarContatoServlet.contatoAux;
    }

	public static Contato contatoAux;
    public Contato lastContatoAlterado;
    
    public Contato getLastContatoAlterado() {
		return this.lastContatoAlterado;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt((request.getParameter("id")));
		AgendaDAO agdao = new AgendaDAO();
		Contato contato = agdao.buscarPorId(id);
		
		request.setAttribute("contato", contato);
		
		AlterarContatoServlet.contatoAux = contato;
		this.lastContatoAlterado = contato;
		
		RequestDispatcher view = request.getRequestDispatcher("efetuar-alteracao-contato.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

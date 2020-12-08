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

@WebServlet("/excluirContato")
public class ExcluirContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ExcluirContatoServlet() {
        super();
    }

    public long idLastContatoDeleted;
    
    public long getIdLastContatoDeleted() {
		return this.idLastContatoDeleted;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AgendaDAO agdao = new AgendaDAO();
		Contato lastContatoRemoved = agdao.buscarPorId(Integer.parseInt(request.getParameter("id")));
		
		agdao = new AgendaDAO();
		agdao.remove(Long.parseLong(request.getParameter("id")));
		
		this.idLastContatoDeleted = Long.parseLong(request.getParameter("id"));
		
		request.setAttribute("lastContatoRemoved", lastContatoRemoved);
		
		if(lastContatoRemoved.getCliente() == 1) {
			RequestDispatcher view = request.getRequestDispatcher("excluir-cliente-resposta.jsp");
			view.forward(request, response);
		}
		else {
			RequestDispatcher view = request.getRequestDispatcher("excluir-fornecedor-resposta.jsp");
			view.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

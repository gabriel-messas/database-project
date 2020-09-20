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

@WebServlet("/excluir")
public class ExcluirProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ExcluirProdutoServlet() {
        super();
    }

    public long idLastDeleted;
    
    public long getIdLastDeleted() {
		return this.idLastDeleted;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdutoDAO ptdao = new ProdutoDAO();
		Produto lastRemoved = ptdao.buscarPorId(Integer.parseInt(request.getParameter("id")));
		
		ptdao = new ProdutoDAO();
		ptdao.remove(Long.parseLong(request.getParameter("id")));
		
		this.idLastDeleted = Long.parseLong(request.getParameter("id"));
		
		ptdao = new ProdutoDAO();
		
		HttpSession session = request.getSession();
		session.setAttribute("lastRemoved", lastRemoved);
		
		Object listaRecentesObj = session.getAttribute("listaRecentes");
		if(listaRecentesObj != null) {
			LinkedList<Produto> listaRecentes = (LinkedList<Produto>)listaRecentesObj;
			synchronized (listaRecentes) {
				for(Produto p : listaRecentes) {
					if(p.getId() == this.idLastDeleted) {
						listaRecentes.remove(listaRecentes.indexOf(p));
					}
				}
			}
			session.setAttribute("listaRecentes", listaRecentes);
		}
		
		ptdao = new ProdutoDAO();
		
		Object listaBuscadosObj = session.getAttribute("listaBuscados");
		if(listaBuscadosObj != null) {
			LinkedList<Produto> listaBuscados = (LinkedList<Produto>)listaBuscadosObj;
			synchronized (listaBuscados) {
				for(Produto p : listaBuscados) {
					if(p.getId() == this.idLastDeleted) {
						listaBuscados.remove(listaBuscados.indexOf(p));
					}
				}
			}
			session.setAttribute("listaBuscados", listaBuscados);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("excluir-resposta.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

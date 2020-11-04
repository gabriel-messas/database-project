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

@WebServlet("/excluirVenda")
public class ExcluirVendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ExcluirVendaServlet() {
        super();
    }

    public int idLastDeleted;
    
    public int getIdLastDeleted() {
		return this.idLastDeleted;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VendaDAO vndao = new VendaDAO();
		Venda lastRemoved = vndao.buscarPorId(Integer.parseInt(request.getParameter("id")));
		
		vndao = new VendaDAO();
		vndao.remove(Integer.parseInt(request.getParameter("id")));
		
		this.idLastDeleted = Integer.parseInt(request.getParameter("id"));
		
		ContVendaDAO cvdao = new ContVendaDAO();
		cvdao.removeVenda(idLastDeleted);
		
		FuncVendaDAO fvdao = new FuncVendaDAO();
		fvdao.removeVenda(idLastDeleted);
		
		ProdVendaDAO pvdao = new ProdVendaDAO();
		pvdao.removeVenda(idLastDeleted);
		
		vndao = new VendaDAO();
		
		HttpSession session = request.getSession();
		session.setAttribute("lastVendaRemoved", lastRemoved);
		
		Object listaVendasObj = session.getAttribute("listaVendas");
		if(listaVendasObj != null) {
			LinkedList<Venda> listaVendas = (LinkedList<Venda>)listaVendasObj;
			synchronized (listaVendas) {
				for(Venda v : listaVendas) {
					if(v.getId() == this.idLastDeleted) {
						listaVendas.remove(listaVendas.indexOf(v));
					}
				}
			}
			session.setAttribute("listaVendas", listaVendas);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("excluir-venda-resposta.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

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

@WebServlet("/finalizarVenda")
public class FinalizarVendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public FinalizarVendaServlet() {
        super();
        this.venda = FinalizarVendaServlet.lastVenda;
    }

    public Venda venda;
    public static Venda lastVenda;
	public Venda getVenda() {
		return this.venda;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VendaDAO vndao = new VendaDAO();
		Venda venda = new Venda();
		
		HttpSession session = request.getSession();
		Object listaCarrinhoObj = session.getAttribute("listaCarrinho");
		Object clienteVendaObj = session.getAttribute("clienteVenda");
		if(listaCarrinhoObj == null) {
			RequestDispatcher view = request.getRequestDispatcher("falta-produto-venda.jsp");
			view.forward(request, response);
		}
		else if(clienteVendaObj == null){
			RequestDispatcher view = request.getRequestDispatcher("falta-cliente-venda.jsp");
			view.forward(request, response);
		}
		else {
			LinkedList<Produto> listaCarrinho = (LinkedList<Produto>)listaCarrinhoObj;
			Contato cliente = (Contato)clienteVendaObj;
			
			double valorVenda = 0;
			for(Produto p : listaCarrinho) {
				valorVenda += p.getPrecoVenda();
			}
			
			venda.setProdutos(listaCarrinho);
			venda.setCliente(cliente);
			venda.setValorVenda(valorVenda);
			
			vndao.inserir(venda);
		}
		
		this.venda = venda;
		FinalizarVendaServlet.lastVenda = venda;
		
		session.removeAttribute("listaCarrinho");
		
		RequestDispatcher view = request.getRequestDispatcher("venda-resposta.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

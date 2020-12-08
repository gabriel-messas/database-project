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
		Object funcionarioVendaObj = session.getAttribute("funcionarioVenda");
		if(listaCarrinhoObj == null) {
			RequestDispatcher view = request.getRequestDispatcher("falta-produto-venda.jsp");
			view.forward(request, response);
			return;
		}
		else if(clienteVendaObj == null){
			RequestDispatcher view = request.getRequestDispatcher("falta-cliente-venda.jsp");
			view.forward(request, response);
			return;
		}
		else if(funcionarioVendaObj == null){
			RequestDispatcher view = request.getRequestDispatcher("falta-funcionario-venda.jsp");
			view.forward(request, response);
			return;
		}
		else {
			LinkedList<Produto> listaCarrinho = (LinkedList<Produto>)listaCarrinhoObj;
			Contato cliente = (Contato)clienteVendaObj;
			Funcionario funcionario = (Funcionario)funcionarioVendaObj;
			
			int quantidade = 0;
			double preco = 0;
			
			for(Produto p : listaCarrinho) {
				quantidade += p.getQuantidade();
				preco += (p.getPrecoVenda()*p.getQuantidade());
			}
			
			venda.setData(String.valueOf(java.time.LocalDate.now()));
			venda.setQuantidade(quantidade);
			venda.setValorVenda(preco);
			
			vndao.inserir(venda);
			
			Object listaVendasObj = session.getAttribute("listaVendas");
			if(listaVendasObj == null) {
				LinkedList<Venda> listaVendas = new LinkedList<Venda>();
				for(Venda v : listaVendas) {
					listaVendas.add(v);
				}
				session.setAttribute("listaVendas", listaVendas);
			}
			else {
				LinkedList<Venda> listaVendas = (LinkedList<Venda>)listaVendasObj;
				for(Venda v : listaVendas) {
					if(!listaVendas.contains(v)) {
						listaVendas.add(v);
					}
				}
				session.setAttribute("listaVendas", listaVendas);
			}
			
			venda = vndao.getLast();
			//
			for(Produto p : listaCarrinho) {
				ProdVenda pv = new ProdVenda();
				pv.setId_produto(p.getId());
				//System.out.println("Produto " + p.getId());
				pv.setId_venda(venda.getId());
				//System.out.println("Venda " + pv.getId_venda());
				pv.setQuantidade(p.getQuantidade());
				pv.setPreco(p.getPrecoVenda());
				ProdVendaDAO pvdao = new ProdVendaDAO();
				pvdao.inserir(pv);
				
				ProdutoDAO ptdao = new ProdutoDAO();
				Produto hack = new Produto();
				hack = ptdao.buscarPorId(pv.getId_produto());
				hack.setQuantidade(hack.getQuantidade() - pv.getQuantidade());
				
				ptdao = new ProdutoDAO();
				ptdao.alterar(hack);
			}
			//
			FuncVenda fv = new FuncVenda();
			fv.setId_funcionario(funcionario.getId());
			fv.setId_venda(venda.getId());
			
			FuncVendaDAO fvdao = new FuncVendaDAO();
			fvdao.inserir(fv);
			//
			ContVenda cv = new ContVenda();
			cv.setId_contato(cliente.getId());
			cv.setId_venda(venda.getId());
			
			ContVendaDAO cvdao = new ContVendaDAO();
			cvdao.inserir(cv);
			//
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

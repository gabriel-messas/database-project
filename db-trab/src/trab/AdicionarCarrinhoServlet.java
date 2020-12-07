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

@WebServlet("/addCarrinho")
public class AdicionarCarrinhoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdicionarCarrinhoServlet() {
        super();
        this.produto = AdicionarCarrinhoServlet.lastProdutoAddedCarrinho;
    }

    public Produto produto;
    public static Produto lastProdutoAddedCarrinho;
	public Produto getProduto() {
		return this.produto;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Produto produto = new Produto();
		produto.setId(Integer.parseInt(request.getParameter("id")));
		//System.out.println("AddCarrinhoServlet " + produto.getId());
		produto.setNome(request.getParameter("nome"));
		produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		produto.setPrecoVenda(Double.parseDouble(request.getParameter("preco")));
		
		Object listaCarrinhoObj = session.getAttribute("listaCarrinho");
		if(listaCarrinhoObj == null) {
			LinkedList<Produto> listaCarrinho = new LinkedList<Produto>();
			listaCarrinho.add(produto);
			
			session.setAttribute("listaCarrinho", listaCarrinho);
		}
		else {
			LinkedList<Produto> listaCarrinho = (LinkedList<Produto>)listaCarrinhoObj;
			listaCarrinho.add(produto);
			
			session.setAttribute("listaCarrinho", listaCarrinho);
		}
		
		AdicionarCarrinhoServlet.lastProdutoAddedCarrinho = produto;
		
		RequestDispatcher view = request.getRequestDispatcher("adicionar-carrinho-resposta.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

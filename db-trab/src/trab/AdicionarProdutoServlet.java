package trab;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adicionar")
public class AdicionarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdicionarProdutoServlet() {
        super();
        this.produto = AdicionarProdutoServlet.lastProdutoAdded;
    }

    public Produto produto;
    public static Produto lastProdutoAdded;
	public Produto getProduto() {
		return this.produto;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("fornecedor") == null) {
			RequestDispatcher view = request.getRequestDispatcher("falta-fornecedor-add-produto.jsp");
			view.forward(request, response);
		}
		else {
		
			Produto produto = new Produto();
			produto.setNome(request.getParameter("nome"));
			produto.setPrecoVenda(Double.parseDouble(request.getParameter("precoVenda")));
			produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
			produto.setPrecoCompra1(request.getParameter("precoCompra1"));
			produto.setPrecoCompra2(request.getParameter("precoCompra2"));
			produto.setFornecedor((Contato)session.getAttribute("fornecedor"));
			
			ProdutoDAO ptdao = new ProdutoDAO();
			ptdao.inserir(produto);
			
			this.produto = produto;
			AdicionarProdutoServlet.lastProdutoAdded = produto;
			
			RequestDispatcher view = request.getRequestDispatcher("adicionar-resposta.jsp");
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

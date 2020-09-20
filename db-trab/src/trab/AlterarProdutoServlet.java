package trab;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/alterar")
public class AlterarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AlterarProdutoServlet() {
        super();
        this.lastProdutoAlterado = AlterarProdutoServlet.produtoAux;
    }

	public static Produto produtoAux;
    public Produto lastProdutoAlterado;
    
    public Produto getLastProdutoAlterado() {
		return this.lastProdutoAlterado;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt((request.getParameter("id")));
		ProdutoDAO ptdao = new ProdutoDAO();
		Produto produto = ptdao.buscarPorId(id);
		
		request.setAttribute("produto", produto);
		
		AlterarProdutoServlet.produtoAux = produto;
		this.lastProdutoAlterado = produto;
		
		RequestDispatcher view = request.getRequestDispatcher("efetuar-alteracao.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

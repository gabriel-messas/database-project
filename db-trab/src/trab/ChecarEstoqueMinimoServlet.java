package trab;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/checar")
public class ChecarEstoqueMinimoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ChecarEstoqueMinimoServlet() {
        super();
    }

    public List<Produto> listaProdutosEstoque;
    public List<Produto> listaProdutosEstoqueBaixo;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listaProdutosEstoque = new LinkedList<Produto>();
		listaProdutosEstoqueBaixo = new LinkedList<Produto>();
		
		ProdutoDAO ptdao = new ProdutoDAO(); 
		
		this.listaProdutosEstoque = ptdao.buscarPorNome("");
		for(Produto p : listaProdutosEstoque) {
			if(p.getQuantidade() <= 2) {
				listaProdutosEstoqueBaixo.add(p);
			}
		}
		request.setAttribute("listaEstoqueBaixo", listaProdutosEstoqueBaixo);		
		
		RequestDispatcher view = request.getRequestDispatcher("checar-estoque-minimo.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

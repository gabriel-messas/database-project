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
import javax.servlet.http.HttpSession;

@WebServlet("/buscar")
public class BuscarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BuscarProdutoServlet() {
        super();
        this.listaProdutos = BuscarProdutoServlet.listaProdutosAux;
        this.listaRecentes = BuscarProdutoServlet.listaRecentesAux;
    }
    
    public static List<Produto> listaProdutosAux;
    public static List<Produto> listaRecentesAux;
    public List<Produto> listaProdutos;
    public List<Produto> listaRecentes;
    
    public List<Produto> getListaProdutos(){
    	return this.listaProdutos;
    }
    
    public List<Produto> getListaRecentes(){
    	return this.listaRecentes;
    }    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdutoDAO ptdao = new ProdutoDAO(); 

		String nome = request.getParameter("nome");
		if(nome == null) {
			this.listaProdutos = ptdao.buscarPorNome("");
		}
		else {
			this.listaProdutos = ptdao.buscarPorNome(nome);
		}
		
		HttpSession session = request.getSession();
		request.setAttribute("listaProdutos", listaProdutos);
		session.setAttribute("listaProdutos", listaProdutos);
		session.setAttribute("listaRecentes", listaRecentes);
		
		Object listaRecentesObj = session.getAttribute("listaRecentes");
		if(listaRecentesObj == null) {
			LinkedList<Produto> listaRecentes = new LinkedList<Produto>();
			for(Produto p : this.listaProdutos) {
				listaRecentes.add(p);
			}
			session.setAttribute("listaRecentes", listaRecentes);
		}
		else {
			LinkedList<Produto> listaRecentes = (LinkedList<Produto>)listaRecentesObj;
			for(Produto p : this.listaProdutos) {
				if(!listaRecentes.contains(p)) {
					listaRecentes.add(p);
				}
			}
		}
		
		Object listaBuscadosObj = request.getAttribute("listaBuscados");
		if(listaBuscadosObj == null) {
			LinkedList<Produto> listaBuscados = new LinkedList<Produto>();
			for(Produto p : this.listaProdutos) {
				listaBuscados.add(p);
			}
			request.setAttribute("listaBuscados", listaBuscados);
		}
		else {
			LinkedList<Produto> listaBuscados = new LinkedList<Produto>();
			for(Produto p : this.listaProdutos) {
				if(!listaBuscados.contains(p)) {
					listaBuscados.add(p);
				}
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher("buscar-resposta.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response, List<Produto> c) throws ServletException, IOException {

	}

}

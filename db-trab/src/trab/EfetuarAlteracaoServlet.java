package trab;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/efetuarAlteracao")
public class EfetuarAlteracaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EfetuarAlteracaoServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProdutoDAO ptdao = new ProdutoDAO();
		Produto produto = new Produto();
		produto.setNome(request.getParameter("nome"));
		produto.setPrecoVenda(Double.parseDouble(request.getParameter("precoVenda")));
		produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
		produto.setId(Integer.parseInt(request.getParameter("id")));
		produto.setPrecoCompra1(request.getParameter("precoCompra1"));
		produto.setPrecoCompra2(request.getParameter("precoCompra2"));
		
		ptdao.alterar(produto);
		
		ptdao = new ProdutoDAO();
		
		HttpSession session = request.getSession();
		Object listaRecentesObj = session.getAttribute("listaRecentes");
		if(listaRecentesObj != null) {
			LinkedList<Produto> listaRecentes = (LinkedList<Produto>)listaRecentesObj;
			synchronized (listaRecentes) {
				for(Produto p : listaRecentes) {
					listaRecentes.set(listaRecentes.indexOf(p), ptdao.buscarPorId(p.getId()));
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
					listaBuscados.set(listaBuscados.indexOf(p), ptdao.buscarPorId(p.getId()));
				}
			}
			session.setAttribute("listaBuscados", listaBuscados);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("alterar-resposta.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

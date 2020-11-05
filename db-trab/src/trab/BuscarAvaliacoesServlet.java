package trab;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/buscarAvaliacoes")
public class BuscarAvaliacoesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BuscarAvaliacoesServlet() {
        super();
    }
    
    public List<Avaliacao> listaAvaliacoes; 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AvaliacaoDAO avdao = new AvaliacaoDAO();
		LinkedList<Avaliacao> listaAvaliacoes = new LinkedList<Avaliacao>();
		
		listaAvaliacoes = avdao.buscarTodas();
    
    	HttpSession session = request.getSession();
        session.setAttribute("listaAvaliacoes", listaAvaliacoes);
		
		RequestDispatcher view = request.getRequestDispatcher("avaliacoes.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response, List<Produto> c) throws ServletException, IOException {

	}

}

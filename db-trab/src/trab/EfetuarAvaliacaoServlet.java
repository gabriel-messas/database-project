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

@WebServlet("/efetuarAvaliacao")
public class EfetuarAvaliacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public EfetuarAvaliacaoServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AvaliacaoDAO avdao = new AvaliacaoDAO();
		Avaliacao av = new Avaliacao();
		av.setId_entrega(Integer.parseInt(request.getParameter("id_entrega")));
		av.setNota(request.getParameter("nota"));
		av.setObservacao(request.getParameter("observacao"));
		
		avdao.inserir(av);		
		
		RequestDispatcher view = request.getRequestDispatcher("buscarAvaliacoes");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

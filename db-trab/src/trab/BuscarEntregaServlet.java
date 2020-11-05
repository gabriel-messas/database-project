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

@WebServlet("/buscarEntregas")
public class BuscarEntregaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BuscarEntregaServlet() {
        super();
    }
    
    public List<Entrega> listaEntregas; 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntregaDAO endao = new EntregaDAO();
		LinkedList<Entrega> listaEntregas = new LinkedList<Entrega>();
		
		listaEntregas = endao.buscarTodas();
    
    	HttpSession session = request.getSession();
        session.setAttribute("listaEntregas", listaEntregas);
		
		RequestDispatcher view = request.getRequestDispatcher("entregas.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response, List<Produto> c) throws ServletException, IOException {

	}

}

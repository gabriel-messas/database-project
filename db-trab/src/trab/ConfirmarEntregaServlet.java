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

@WebServlet("/confirmarEntrega")
public class ConfirmarEntregaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConfirmarEntregaServlet() {
        super();
        this.entrega = ConfirmarEntregaServlet.lastEntrega;
    }

    public Entrega entrega;
    public static Entrega lastEntrega;
	public Entrega getEntrega() {
		return this.entrega;
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntregaDAO endao = new EntregaDAO();
		Entrega entrega = new Entrega();
		
		HttpSession session = request.getSession();
		Object listaEntregasObj = session.getAttribute("listaEntregas");
		
		LinkedList<Entrega> listaEntregas = null;
		
		if(listaEntregasObj == null) {
			listaEntregas = new LinkedList<Entrega>();
		}
		else {
			listaEntregas = (LinkedList<Entrega>)listaEntregasObj;
		}
			
		entrega = endao.buscarPorId(Integer.parseInt(request.getParameter("id_venda")));
		listaEntregas.remove(entrega);
		
		entrega.setDataEntrega(String.valueOf(java.time.LocalDate.now()));
		
		endao = new EntregaDAO();
		endao.alterar(entrega);
			
		listaEntregas.add(entrega);
				
		session.setAttribute("listaEntregas", listaEntregas);

		this.entrega = entrega;
		ConfirmarEntregaServlet.lastEntrega = entrega;
	
		RequestDispatcher view = request.getRequestDispatcher("entrega-confirmada.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

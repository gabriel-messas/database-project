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

@WebServlet("/entregarVenda")
public class EntregarVendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EntregarVendaServlet() {
        super();
        this.entrega = EntregarVendaServlet.lastEntrega;
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
			
		entrega.setId_venda(Integer.parseInt(request.getParameter("id")));
		entrega.setDataPrevista(String.valueOf(java.time.LocalDate.ofYearDay(java.time.LocalDate.now().getYear(), java.time.LocalDate.now().getDayOfYear() + 7)));
		entrega.setDataEntrega("");
		endao.inserir(entrega);
		
		endao = new EntregaDAO();
		entrega = endao.buscarPorId(entrega.getId_venda());
			
		listaEntregas.add(entrega);
				
		session.setAttribute("listaEntregas", listaEntregas);

		this.entrega = entrega;
		EntregarVendaServlet.lastEntrega = entrega;
	
		RequestDispatcher view = request.getRequestDispatcher("entrega-iniciada.jsp");
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

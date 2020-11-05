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

@WebServlet("/buscarVendas")
public class BuscarVendaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BuscarVendaServlet() {
        super();
    }
    
    public List<Venda> listaVendas; 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VendaDAO vddao = new VendaDAO();
		LinkedList<Venda> listaVendas = new LinkedList<Venda>();
		Venda venda = new Venda();
		venda = vddao.getLast();
		
    	for(int g = 0; g <= venda.getId(); g++) {
    		if(vddao.buscarPorId(g) != null) {
    			listaVendas.add(vddao.buscarPorId(g));
    			
    		}
    	}
    
    	HttpSession session = request.getSession();
        session.setAttribute("listaVendas", listaVendas);
		
		RequestDispatcher view = request.getRequestDispatcher("vendas.jsp");
		
		view.forward(request, response);
	}
	
	public List<Produto> getProdutos(Venda venda) {
		
		ProdVendaDAO pvdao = new ProdVendaDAO();
		
		return pvdao.buscarPorIdTodosProdutos(venda.getId());
	}
	
	public Contato getCliente(Venda venda) {
		
		ContVendaDAO cvdao = new ContVendaDAO();
		
		return cvdao.buscarPorIdCliente(venda.getId());
	}
	
	public Funcionario getFuncionario(Venda venda) {
		
		FuncVendaDAO fvdao = new FuncVendaDAO();
		
		return fvdao.buscarPorIdFuncionario(venda.getId());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response, List<Produto> c) throws ServletException, IOException {

	}

}

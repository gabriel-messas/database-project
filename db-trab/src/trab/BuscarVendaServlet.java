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
		
		int index = 0;
		
		File file = new File("C:\\Users\\Gabriel\\Dropbox\\Banco de Dados\\trab-db\\db-trab\\config.properties");
		file.createNewFile();
		
		InputStream input = new FileInputStream("C:\\Users\\Gabriel\\Dropbox\\Banco de Dados\\trab-db\\db-trab\\config.properties");
		
        Properties prop = new Properties();

        // load a properties file
        prop.load(input);

        // get the property value and print it out
        if (prop.getProperty("venda_index") != null){
        	index = Integer.parseInt(prop.getProperty("venda_index"));
        	for(int g = 0; g < index; g++) {
        		listaVendas.add(vddao.buscarPorId(g));
        	}
        }
        
        request.setAttribute("listaVendas", listaVendas);
		
		RequestDispatcher view = request.getRequestDispatcher("vendas.jsp");
		
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response, List<Produto> c) throws ServletException, IOException {

	}

}

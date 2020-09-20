package trab;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class VendaDAO {
	
	private Connection connection;
	
	static int index = 0;
	
	public VendaDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void inserir(Venda venda) {
		try{
		
			File file = new File("C:\\Users\\Gabriel\\Dropbox\\Banco de Dados\\trab-db\\db-trab\\config.properties");
			file.createNewFile();
			
			InputStream input = new FileInputStream("C:\\Users\\Gabriel\\Dropbox\\Banco de Dados\\trab-db\\db-trab\\config.properties");
			
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            if (prop.getProperty("venda_index") != null){
            	VendaDAO.index = Integer.parseInt(prop.getProperty("venda_index"));
            }
            else {
            	input.close();
            	OutputStream output = new FileOutputStream("C:\\Users\\Gabriel\\Dropbox\\Banco de Dados\\trab-db\\db-trab\\config.properties");
            	
            	prop = new Properties();

            	// set the properties value
	            prop.setProperty("venda_index", String.valueOf(index));
	
	            // save properties to project root folder
	            prop.store(output, null);
	            
	            output.close();
            }
            
            input.close();
            
        }catch (IOException ex) {
            ex.printStackTrace();
        }
		
		
		for(Produto produto : venda.getProdutos()) {
			String sql = 
					"INSERT INTO venda (ID, PRODUTO, QUANTIDADE, PRECO, CLIENTE) VALUES (?,?,?,?,?)";
			
			try {
				PreparedStatement prstate = connection.prepareStatement(sql);
				
				prstate.setInt(1, index);
				prstate.setString(2, produto.getNome());
				prstate.setInt(3, produto.getQuantidade());
				prstate.setDouble(4, produto.getPrecoVenda());
				prstate.setString(5, venda.getCliente().getNome());
				
				prstate.execute();
				
				prstate.close();
			}
			catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		VendaDAO.index++;
		
		
		try (InputStream input = new FileInputStream("C:\\Users\\Gabriel\\Dropbox\\Banco de Dados\\trab-db\\db-trab\\config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            
            input.close();

            try (OutputStream output = new FileOutputStream("C:\\Users\\Gabriel\\Dropbox\\Banco de Dados\\trab-db\\db-trab\\config.properties")) {

            	// set the properties value
	            prop.setProperty("venda_index", String.valueOf(index));
	
	            // save properties to project root folder
	            prop.store(output, null);
	
	            System.out.println(prop);
	            
	            //input.close();
	            output.close();
            }
		}
		catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	
	public void remove(int id) {
		String sql = "DELETE FROM venda WHERE ID=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id);
			prstate.execute();
			prstate.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				connection.close();
			}
			catch(SQLException e){
				throw new RuntimeException(e);
			}
		}
	}
	
	public Venda buscarPorId(int id){
		String sql = "SELECT * FROM venda WHERE ID = ?";
		
		Venda venda = new Venda();
		Produto produto = new Produto();
		Contato cliente = new Contato();
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setLong(1, id);
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				venda.setId(resultado.getInt("ID"));
				
				produto.setNome(resultado.getString("PRODUTO"));
				produto.setQuantidade(resultado.getInt("QUANTIDADE"));
				produto.setPrecoVenda(resultado.getDouble("PRECO"));
				cliente.setNome(resultado.getString("CLIENTE"));
				venda.setCliente(cliente);
				
				venda.getProdutos().add(produto);
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return venda;
	}
	
	public void alterar(Venda venda) {
		this.remove(venda.getId());
		this.inserir(venda);
	}
}

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

public class ContVendaDAO {
	
	private Connection connection;
	
	static int index = 0;
	
	public ContVendaDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void inserir(ContVenda cv) {

		String sql = 
				"INSERT INTO contato_venda (ID_CONTATO, ID_VENDA) VALUES (?,?)";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setInt(1, cv.getId_contato());
			prstate.setInt(2, cv.getId_venda());
			
			prstate.execute();
			
			prstate.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void remove(int id_contato, int id_venda) {
		String sql = "DELETE FROM contato_venda WHERE ID_CONTATO=? AND ID_VENDA=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_contato);
			prstate.setInt(2, id_venda);
			
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
	
	public void removeVenda(int id_venda) {
		String sql = "DELETE FROM contato_venda WHERE ID_VENDA=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_venda);
			
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
	
	public ContVenda buscarPorId(int id_contato, int id_venda){
		String sql = "SELECT * FROM contato_venda WHERE ID_CONTATO = ? AND ID_VENDA=?";
		
		ContVenda cv = new ContVenda();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_contato);
			prstate.setInt(2, id_venda);
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				cv.setId_contato(id_contato);
				cv.setId_venda(id_venda);
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return cv;
	}
	
	public Contato buscarPorIdCliente(int id_venda){
		String sql = "SELECT * FROM contato_venda WHERE ID_VENDA=?";
		
		ContVenda cv = new ContVenda();
		
		Contato cliente = null;
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_venda);
			
			ResultSet resultado = prstate.executeQuery();
			
			cliente = new Contato();
			
			while(resultado.next()) {
				cv.setId_contato(resultado.getInt("ID_CONTATO"));
			}
			
			AgendaDAO ctdao = new AgendaDAO();
			cliente = ctdao.buscarPorId(cv.getId_contato());
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return cliente;
	}
	
	public void alterar(ContVenda cv) {
		this.remove(cv.getId_contato(), cv.getId_venda());
		ContVendaDAO cvdao = new ContVendaDAO();
		cvdao.inserir(cv);
	}
}

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
	
	public Venda getLast() {
		String sql = "SELECT * FROM venda ORDER BY ID DESC LIMIT 1";
		
		Venda venda = new Venda();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				venda.setId(resultado.getInt("ID"));
				venda.setData(resultado.getString("DATA"));
				venda.setQuantidade(resultado.getInt("QUANTIDADE"));
				venda.setValorVenda(resultado.getDouble("PRECO"));
			}
			
			resultado.close();
			
			prstate.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
		return venda;
	}
	
	public void inserir(Venda venda) {

		String sql = 
				"INSERT INTO venda (DATA, QUANTIDADE, PRECO) VALUES (?,?,?)";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, venda.getData());
			prstate.setInt(2, venda.getQuantidade());
			prstate.setDouble(3, venda.getValorVenda());
			
			prstate.execute();
			
			prstate.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
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
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setLong(1, id);
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				venda.setId(resultado.getInt("ID"));
				venda.setData(resultado.getString("DATA"));
				venda.setQuantidade(resultado.getInt("QUANTIDADE"));
				venda.setValorVenda(resultado.getDouble("PRECO"));
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		if(!(venda.getId() >= 1)) {
			
			return null;
		}
		return venda;
	}
	
	public void alterar(Venda venda) {
		this.remove(venda.getId());
		VendaDAO vndao = new VendaDAO();
		vndao.inserir(venda);
	}
}

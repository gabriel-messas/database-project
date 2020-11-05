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

public class EntregaDAO {
	
	private Connection connection;
	
	static int index = 0;
	
	public EntregaDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void inserir(Entrega en) {

		String sql = 
				"INSERT INTO entrega (ID_VENDA, DATA_PREVISTA, DATA_ENTREGA) VALUES (?,?,?)";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setInt(1, en.getId_venda());
			prstate.setString(2, en.getDataPrevista());
			prstate.setString(3, en.getDataEntrega());
			
			prstate.execute();
			
			prstate.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void remove(int id_venda) {
		String sql = "DELETE FROM entrega WHERE ID_VENDA=?";
		
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
	
	public Entrega buscarPorId(int id_venda){
		String sql = "SELECT * FROM entrega WHERE ID_VENDA=?";
		
		Entrega en = new Entrega();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_venda);
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				en.setId(resultado.getInt("ID"));
				en.setId_venda(id_venda);
				en.setDataPrevista(resultado.getString("DATA_PREVISTA"));
				en.setDataEntrega(resultado.getString("DATA_ENTREGA"));
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return en;
	}
	
	public Entrega buscarPorIdEntrega(int id){
		String sql = "SELECT * FROM entrega WHERE ID=?";
		
		Entrega en = new Entrega();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id);
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				en.setId(resultado.getInt("ID"));
				en.setId_venda(resultado.getInt("ID_VENDA"));
				en.setDataPrevista(resultado.getString("DATA_PREVISTA"));
				en.setDataEntrega(resultado.getString("DATA_ENTREGA"));
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return en;
	}
	
	public LinkedList<Entrega> buscarTodas(){
		String sql = "SELECT * FROM entrega";
		
		Entrega en = new Entrega();
		LinkedList<Entrega> list = null;
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			ResultSet resultado = prstate.executeQuery();
			
			list = new LinkedList<Entrega>();
			
			while(resultado.next()) {
				en.setId(resultado.getInt("ID"));
				en.setId_venda(resultado.getInt("ID_VENDA"));
				en.setDataPrevista(resultado.getString("DATA_PREVISTA"));
				en.setDataEntrega(resultado.getString("DATA_ENTREGA"));
				list.add(en);
				en = new Entrega();
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean isVendaEntregue(Venda venda) {
		String sql = "SELECT COUNT(*) AS count FROM entrega WHERE ID_VENDA=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, venda.getId());
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				if(resultado.getInt("count") == 1){
					return true;
				}
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean isVendaEntregue2(int id_venda) {
		String sql = "SELECT * FROM entrega WHERE ID_VENDA=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_venda);
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				if(resultado.getString("DATA_ENTREGA").equals("")){
					return false;
				}
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void alterar(Entrega en) {
		this.remove(en.getId_venda());
		EntregaDAO endao = new EntregaDAO();
		endao.inserir(en);
	}
}

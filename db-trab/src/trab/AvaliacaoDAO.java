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

public class AvaliacaoDAO {
	
	private Connection connection;
	
	static int index = 0;
	
	public AvaliacaoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void inserir(Avaliacao av) {

		String sql = 
				"INSERT INTO avaliacao (ID_ENTREGA, NOTA, OBSERVACAO) VALUES (?,?,?)";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setInt(1, av.getId_entrega());
			prstate.setString(2, av.getNota());
			prstate.setString(3, av.getObservacao());
			
			prstate.execute();
			
			prstate.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void remove(int id_entrega) {
		String sql = "DELETE FROM avaliacao WHERE ID_ENTREGA=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_entrega);
			
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
	
	public Avaliacao buscarPorId(int id_entrega){
		String sql = "SELECT * FROM avaliacao WHERE ID_VENDA=?";
		
		Avaliacao av = new Avaliacao();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_entrega);
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				av.setId(resultado.getInt("ID"));
				av.setId_entrega(id_entrega);
				av.setNota(resultado.getString("NOTA"));
				av.setObservacao(resultado.getString("OBSERVACAO"));
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return av;
	}
	
	public LinkedList<Avaliacao> buscarTodas(){
		String sql = "SELECT * FROM avaliacao";
		
		Avaliacao av = new Avaliacao();
		LinkedList<Avaliacao> list = null;
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			ResultSet resultado = prstate.executeQuery();
			
			list = new LinkedList<Avaliacao>();
			
			while(resultado.next()) {
				av.setId(resultado.getInt("ID"));
				av.setId_entrega(resultado.getInt("ID_ENTREGA"));
				av.setNota(resultado.getString("NOTA"));
				av.setObservacao(resultado.getString("OBSERVACAO"));
				list.add(av);
				av = new Avaliacao();
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public boolean isEntregaAvaliada(Entrega entrega) {
		String sql = "SELECT COUNT(*) AS count FROM avaliacao WHERE ID_ENTREGA=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, entrega.getId());
			
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
	
	public void alterar(Avaliacao av) {
		this.remove(av.getId_entrega());
		AvaliacaoDAO avdao = new AvaliacaoDAO();
		avdao.inserir(av);
	}
}

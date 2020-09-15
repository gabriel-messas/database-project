package trab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgendaDAO {
	
	private Connection connection;
	
	public AgendaDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void inserir(Contato contato) {
		String sql = 
				"INSERT INTO contato (NOME, APELIDO, OFICINA, ENDERECO, BAIRRO, CIDADE, TELEFONE1, TELEFONE2, TELEFONE3, OBSERVACAO) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, contato.getNome());
			prstate.setString(2, contato.getApelido());
			prstate.setString(3, contato.getOficina());
			prstate.setString(4, contato.getEndereco());
			prstate.setString(5, contato.getBairro());
			prstate.setString(6, contato.getCidade());
			prstate.setString(7, contato.getTelefone1());
			prstate.setString(8, contato.getTelefone2());
			prstate.setString(9, contato.getTelefone3());
			prstate.setString(10, contato.getObservacao());
			
			prstate.execute();
			
			prstate.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Contato> buscarPorNome(String nome){
		String sql = "SELECT * FROM contato WHERE NOME LIKE UPPER(?) OR APELIDO LIKE UPPER(?) OR OFICINA LIKE UPPER(?) ORDER BY NOME";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setString(1, new String("%" + nome + "%").toUpperCase());
			prstate.setString(2, new String("%" + nome + "%").toUpperCase());
			prstate.setString(3, new String("%" + nome + "%").toUpperCase());
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				Contato contato = new Contato();
				contato.setId(resultado.getInt("ID"));
				contato.setNome(resultado.getString("NOME"));
				contato.setApelido(resultado.getString("APELIDO"));
				contato.setOficina(resultado.getString("OFICINA"));
				contato.setEndereco(resultado.getString("ENDERECO"));
				contato.setBairro(resultado.getString("BAIRRO"));
				contato.setCidade(resultado.getString("CIDADE"));
				contato.setTelefone1(resultado.getString("TELEFONE1"));
				contato.setTelefone2(resultado.getString("TELEFONE2"));
				contato.setTelefone3(resultado.getString("TELEFONE3"));
				contato.setObservacao(resultado.getString("OBSERVACAO"));
				
				contatos.add(contato);
			}
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return contatos;
	}
	
	public List<Contato> buscarPorEndereco(String nome){
		String sql = "SELECT * FROM contato WHERE ENDERECO LIKE UPPER(?) OR BAIRRO LIKE UPPER(?) ORDER BY NOME";
		
		List<Contato> contatos = new ArrayList<Contato>();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setString(1, new String("%" + nome + "%").toUpperCase());
			prstate.setString(2, new String("%" + nome + "%").toUpperCase());
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				Contato contato = new Contato();
				contato.setId(resultado.getInt("ID"));
				contato.setNome(resultado.getString("NOME"));
				contato.setApelido(resultado.getString("APELIDO"));
				contato.setOficina(resultado.getString("OFICINA"));
				contato.setEndereco(resultado.getString("ENDERECO"));
				contato.setBairro(resultado.getString("BAIRRO"));
				contato.setCidade(resultado.getString("CIDADE"));
				contato.setTelefone1(resultado.getString("TELEFONE1"));
				contato.setTelefone2(resultado.getString("TELEFONE2"));
				contato.setTelefone3(resultado.getString("TELEFONE3"));
				contato.setObservacao(resultado.getString("OBSERVACAO"));
				
				contatos.add(contato);
			}
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return contatos;
	}
	
	public void remove(Long id) {
		String sql = "DELETE FROM contato WHERE ID=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setLong(1, id);
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
	
	public Contato buscarPorId(int id){
		String sql = "SELECT * FROM contato WHERE ID = ?";
		
		Contato contato = null;
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setLong(1, id);
			
			ResultSet resultado = prstate.executeQuery();
			resultado.next();
			
			contato = new Contato();
			contato.setId(resultado.getInt("ID"));
			contato.setNome(resultado.getString("NOME"));
			contato.setApelido(resultado.getString("APELIDO"));
			contato.setOficina(resultado.getString("OFICINA"));
			contato.setEndereco(resultado.getString("ENDERECO"));
			contato.setBairro(resultado.getString("BAIRRO"));
			contato.setCidade(resultado.getString("CIDADE"));
			contato.setTelefone1(resultado.getString("TELEFONE1"));
			contato.setTelefone2(resultado.getString("TELEFONE2"));
			contato.setTelefone3(resultado.getString("TELEFONE3"));
			contato.setObservacao(resultado.getString("OBSERVACAO"));
				
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return contato;
	}
	
	public void alterar(Contato contato) {
		String sql = "UPDATE contato SET NOME=?, APELIDO=?, OFICINA=?, ENDERECO=?, BAIRRO=?, CIDADE=?, TELEFONE1=?, TELEFONE2=?, TELEFONE3=?, OBSERVACAO=? WHERE ID=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, contato.getNome());
			prstate.setString(2, contato.getApelido());
			prstate.setString(3, contato.getOficina());
			prstate.setString(4, contato.getEndereco());
			prstate.setString(5, contato.getBairro());
			prstate.setString(6, contato.getCidade());
			prstate.setString(7, contato.getTelefone1());
			prstate.setString(8, contato.getTelefone2());
			prstate.setString(9, contato.getTelefone3());
			prstate.setString(10, contato.getObservacao());
			prstate.setInt(11, contato.getId());
			
			prstate.execute();
			prstate.close();
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}
		finally {
			try {
				connection.close();
			}
			catch(SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
}

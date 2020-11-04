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
				"INSERT INTO contato (NOME, APELIDO, EMPRESA, ENDERECO, BAIRRO, CIDADE, TELEFONE1, TELEFONE2, TELEFONE3, OBSERVACAO, CLIENTE) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, contato.getNome());
			prstate.setString(2, contato.getApelido());
			prstate.setString(3, contato.getEmpresa());
			prstate.setString(4, contato.getEndereco());
			prstate.setString(5, contato.getBairro());
			prstate.setString(6, contato.getCidade());
			prstate.setString(7, contato.getTelefone1());
			prstate.setString(8, contato.getTelefone2());
			prstate.setString(9, contato.getTelefone3());
			prstate.setString(10, contato.getObservacao());
			prstate.setInt(11, contato.getCliente());
			
			prstate.execute();
			
			prstate.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Contato> buscarClientesPorNome(String nome){
		String sql = "SELECT * FROM contato WHERE CLIENTE=1 AND NOME LIKE UPPER(?) OR CLIENTE=1 AND APELIDO LIKE UPPER(?) OR CLIENTE=1 AND EMPRESA LIKE UPPER(?) ORDER BY NOME";
		
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
				contato.setEmpresa(resultado.getString("EMPRESA"));
				contato.setEndereco(resultado.getString("ENDERECO"));
				contato.setBairro(resultado.getString("BAIRRO"));
				contato.setCidade(resultado.getString("CIDADE"));
				contato.setTelefone1(resultado.getString("TELEFONE1"));
				contato.setTelefone2(resultado.getString("TELEFONE2"));
				contato.setTelefone3(resultado.getString("TELEFONE3"));
				contato.setObservacao(resultado.getString("OBSERVACAO"));
				contato.setCliente(resultado.getInt("CLIENTE"));
				
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
	
	public List<Contato> buscarFornecedoresPorNome(String nome){
		String sql = "SELECT * FROM contato WHERE CLIENTE=0 AND NOME LIKE UPPER(?) OR CLIENTE=0 AND APELIDO LIKE UPPER(?) OR CLIENTE=0 AND EMPRESA LIKE UPPER(?) ORDER BY NOME";
		
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
				contato.setEmpresa(resultado.getString("EMPRESA"));
				contato.setEndereco(resultado.getString("ENDERECO"));
				contato.setBairro(resultado.getString("BAIRRO"));
				contato.setCidade(resultado.getString("CIDADE"));
				contato.setTelefone1(resultado.getString("TELEFONE1"));
				contato.setTelefone2(resultado.getString("TELEFONE2"));
				contato.setTelefone3(resultado.getString("TELEFONE3"));
				contato.setObservacao(resultado.getString("OBSERVACAO"));
				contato.setCliente(resultado.getInt("CLIENTE"));
				
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
	
	public List<Contato> buscarClientesPorEndereco(String nome){
		String sql = "SELECT * FROM contato WHERE CLIENTE=1 AND ENDERECO LIKE UPPER(?) OR CLIENTE=1 AND BAIRRO LIKE UPPER(?) ORDER BY NOME";
		
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
				contato.setEmpresa(resultado.getString("EMPRESA"));
				contato.setEndereco(resultado.getString("ENDERECO"));
				contato.setBairro(resultado.getString("BAIRRO"));
				contato.setCidade(resultado.getString("CIDADE"));
				contato.setTelefone1(resultado.getString("TELEFONE1"));
				contato.setTelefone2(resultado.getString("TELEFONE2"));
				contato.setTelefone3(resultado.getString("TELEFONE3"));
				contato.setObservacao(resultado.getString("OBSERVACAO"));
				contato.setCliente(resultado.getInt("CLIENTE"));
				
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
	
	public List<Contato> buscarFornecedoresPorEndereco(String nome){
		String sql = "SELECT * FROM contato WHERE CLIENTE=0 AND ENDERECO LIKE UPPER(?) OR CLIENTE=0 AND BAIRRO LIKE UPPER(?) ORDER BY NOME";
		
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
				contato.setEmpresa(resultado.getString("EMPRESA"));
				contato.setEndereco(resultado.getString("ENDERECO"));
				contato.setBairro(resultado.getString("BAIRRO"));
				contato.setCidade(resultado.getString("CIDADE"));
				contato.setTelefone1(resultado.getString("TELEFONE1"));
				contato.setTelefone2(resultado.getString("TELEFONE2"));
				contato.setTelefone3(resultado.getString("TELEFONE3"));
				contato.setObservacao(resultado.getString("OBSERVACAO"));
				contato.setCliente(resultado.getInt("CLIENTE"));
				
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
			contato.setEmpresa(resultado.getString("EMPRESA"));
			contato.setEndereco(resultado.getString("ENDERECO"));
			contato.setBairro(resultado.getString("BAIRRO"));
			contato.setCidade(resultado.getString("CIDADE"));
			contato.setTelefone1(resultado.getString("TELEFONE1"));
			contato.setTelefone2(resultado.getString("TELEFONE2"));
			contato.setTelefone3(resultado.getString("TELEFONE3"));
			contato.setObservacao(resultado.getString("OBSERVACAO"));
			contato.setCliente(resultado.getInt("CLIENTE"));
				
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return contato;
	}
	
	public void alterar(Contato contato) {
		String sql = "UPDATE contato SET NOME=?, APELIDO=?, EMPRESA=?, ENDERECO=?, BAIRRO=?, CIDADE=?, TELEFONE1=?, TELEFONE2=?, TELEFONE3=?, OBSERVACAO=?, CLIENTE=? WHERE ID=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, contato.getNome());
			prstate.setString(2, contato.getApelido());
			prstate.setString(3, contato.getEmpresa());
			prstate.setString(4, contato.getEndereco());
			prstate.setString(5, contato.getBairro());
			prstate.setString(6, contato.getCidade());
			prstate.setString(7, contato.getTelefone1());
			prstate.setString(8, contato.getTelefone2());
			prstate.setString(9, contato.getTelefone3());
			prstate.setString(10, contato.getObservacao());
			prstate.setInt(11, contato.getCliente());
			prstate.setInt(12, contato.getId());
			
			
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

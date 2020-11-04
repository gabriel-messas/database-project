package trab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
	
	private Connection connection;
	
	public ProdutoDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void inserir(Produto produto) {
		String sql = 
				"INSERT INTO produto (NOME, PRECOVENDA, QUANTIDADE, PRECOCOMPRA1, PRECOCOMPRA2, ID_CONTATO) VALUES (?,?,?,?,?,?)";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, produto.getNome());
			prstate.setDouble(2, produto.getPrecoVenda());
			prstate.setInt(3, produto.getQuantidade());
			prstate.setString(4, produto.getPrecoCompra1());
			prstate.setString(5, produto.getPrecoCompra2());
			prstate.setInt(6, produto.getFornecedor().getId());
			
			prstate.execute();
			
			prstate.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Produto> buscarPorNome(String nome){
		String sql = "SELECT * FROM produto WHERE NOME LIKE UPPER(?)";
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setString(1, new String("%" + nome + "%").toUpperCase());
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				Produto produto = new Produto();
				produto.setId(resultado.getInt("ID"));
				produto.setNome(resultado.getString("NOME"));
				produto.setPrecoVenda(resultado.getDouble("PRECOVENDA"));
				produto.setQuantidade(resultado.getInt("QUANTIDADE"));
				produto.setPrecoCompra1(resultado.getString("PRECOCOMPRA1"));
				produto.setPrecoCompra2(resultado.getString("PRECOCOMPRA2"));
				int id_contato_temp = resultado.getInt("ID_CONTATO");
				
				AgendaDAO agdao = new AgendaDAO();
				Contato fornecedor = new Contato();
				fornecedor = agdao.buscarPorId(id_contato_temp);
				produto.setFornecedor(fornecedor);
				
				produtos.add(produto);
			}
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return produtos;
	}
	
	public void remove(Long id) {
		String sql = "DELETE FROM produto WHERE ID=?";
		
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
	
	public Produto buscarPorId(int id){
		String sql = "SELECT * FROM produto WHERE ID = ?";
		
		Produto produto = null;
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setLong(1, id);
			
			ResultSet resultado = prstate.executeQuery();
			resultado.next();
			
			produto = new Produto();
			produto.setId(resultado.getInt("ID"));
			produto.setNome(resultado.getString("NOME"));
			produto.setPrecoVenda(resultado.getDouble("PRECOVENDA"));
			produto.setQuantidade(resultado.getInt("QUANTIDADE"));
			produto.setPrecoCompra1(resultado.getString("PRECOCOMPRA1"));
			produto.setPrecoCompra2(resultado.getString("PRECOCOMPRA2"));
				
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return produto;
	}
	
	public void alterar(Produto produto) {
		String sql = "UPDATE produto SET NOME=?, PRECOVENDA=?, QUANTIDADE=?, PRECOCOMPRA1=?, PRECOCOMPRA2=? WHERE ID=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setString(1, produto.getNome());
			prstate.setDouble(2, produto.getPrecoVenda());
			prstate.setInt(3, produto.getQuantidade());
			prstate.setString(4, produto.getPrecoCompra1());
			prstate.setString(5, produto.getPrecoCompra2());
			prstate.setInt(6, produto.getId());
			
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

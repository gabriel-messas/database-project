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

public class ProdVendaDAO {
	
	private Connection connection;
	
	static int index = 0;
	
	public ProdVendaDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void inserir(ProdVenda pv) {

		String sql = 
				"INSERT INTO produto_venda (ID_PRODUTO, ID_VENDA, QUANTIDADE, PRECO) VALUES (?,?,?,?)";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setInt(1, pv.getId_produto());
			prstate.setInt(2, pv.getId_venda());
			prstate.setInt(3, pv.getQuantidade());
			prstate.setDouble(4, pv.getPreco());
			
			prstate.execute();
			
			prstate.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void remove(int id_produto, int id_venda) {
		String sql = "DELETE FROM produto_venda WHERE ID_PRODUTO=? AND ID_VENDA=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_produto);
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
		String sql = "DELETE FROM produto_venda WHERE ID_VENDA=?";
		
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
	
	public ProdVenda buscarPorId(int id_produto, int id_venda){
		String sql = "SELECT * FROM produto_venda WHERE ID_PRODUTO = ? AND ID_VENDA=?";
		
		ProdVenda pv = new ProdVenda();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_produto);
			prstate.setInt(2, id_venda);
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				pv.setId_produto(id_produto);
				pv.setId_venda(id_venda);
				pv.setQuantidade(resultado.getInt("QUANTIDADE"));
				pv.setPreco(resultado.getDouble("PRECO"));
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return pv;
	}
	
	public LinkedList<Produto> buscarPorIdTodosProdutos(int id_venda){
		String sql = "SELECT * FROM produto_venda WHERE ID_VENDA=?";
		
		ProdVenda pv = new ProdVenda();
		
		LinkedList<Produto> list = null;
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_venda);
			
			ResultSet resultado = prstate.executeQuery();
			
			list = new LinkedList<Produto>();
			
			while(resultado.next()) {
				pv.setId_produto(resultado.getInt("ID_PRODUTO"));
				pv.setId_venda(id_venda);
				pv.setQuantidade(resultado.getInt("QUANTIDADE"));
				pv.setPreco(resultado.getDouble("PRECO"));
				
				ProdutoDAO ptdao = new ProdutoDAO();
				Produto p = new Produto();
				p = ptdao.buscarPorId(pv.getId_produto());
				p.setQuantidade(pv.getQuantidade());
				p.setPrecoVenda(pv.getPreco());
				
				list.add(p);
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void alterar(ProdVenda pv) {
		this.remove(pv.getId_produto(), pv.getId_venda());
		ProdVendaDAO pvdao = new ProdVendaDAO();
		pvdao.inserir(pv);
	}
}

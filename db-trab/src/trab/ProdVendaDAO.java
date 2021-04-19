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
	
	static String _less18;
	public static String get_less18() {
		return _less18;
	}

	public static void set_less18(String _less18) {
		ProdVendaDAO._less18 = _less18;
	}

	public static String get_1830() {
		return _1830;
	}

	public static void set_1830(String _1830) {
		ProdVendaDAO._1830 = _1830;
	}

	public static String get_3150() {
		return _3150;
	}

	public static void set_3150(String _3150) {
		ProdVendaDAO._3150 = _3150;
	}

	public static String get_more50() {
		return _more50;
	}

	public static void set_more50(String _more50) {
		ProdVendaDAO._more50 = _more50;
	}

	public static String get__less18() {
		return __less18;
	}

	public static void set__less18(String __less18) {
		ProdVendaDAO.__less18 = __less18;
	}

	public static String get__1830() {
		return __1830;
	}

	public static void set__1830(String __1830) {
		ProdVendaDAO.__1830 = __1830;
	}

	public static String get__3150() {
		return __3150;
	}

	public static void set__3150(String __3150) {
		ProdVendaDAO.__3150 = __3150;
	}

	public static String get__more50() {
		return __more50;
	}

	public static void set__more50(String __more50) {
		ProdVendaDAO.__more50 = __more50;
	}

	static String _1830;
	static String _3150;
	static String _more50;
	static String __less18;
	static String __1830;
	static String __3150;
	static String __more50;
	
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
	
	public int statsMaisVentidoFaixaEtaria(int idadeMin, int idadeMax){
		String sql = "SELECT p.nome, COUNT(p.nome) as c"
				+ "	FROM venda v"
				+ "	JOIN produto_venda pv ON v.id = pv.id_venda"
				+ "	JOIN produto p ON pv.id_produto = p.id"
				+ "	WHERE v.id IN (SELECT id_venda FROM contato_venda WHERE id_contato IN (SELECT id FROM contato WHERE idade >= ? AND idade <= ?))"
				+ "	GROUP BY p.nome"
				+ "	ORDER BY c DESC"
				+ "	LIMIT 1";
		
		int result = 0;
		String res;
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, idadeMin);
			prstate.setInt(2, idadeMax);
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				res = resultado.getString("NOME");
				result = resultado.getInt("c");
				
				if(idadeMin == 0) {
					_less18 = res;
				}
				else if(idadeMin == 18) {
					_1830 = res;
				}
				else if(idadeMin == 31) {
					_3150 = res;
				}
				else {
					_more50 = res;
				}
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int statsMenosVentidoFaixaEtaria(int idadeMin, int idadeMax){
		String sql = "SELECT p.nome, COUNT(p.nome) as c"
				+ "	FROM venda v"
				+ "	JOIN produto_venda pv ON v.id = pv.id_venda"
				+ "	JOIN produto p ON pv.id_produto = p.id"
				+ "	WHERE v.id IN (SELECT id_venda FROM contato_venda WHERE id_contato IN (SELECT id FROM contato WHERE idade >= ? AND idade <= ?))"
				+ "	GROUP BY p.nome"
				+ "	ORDER BY c ASC"
				+ "	LIMIT 1";
		
		int result = 0;
		String res;
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, idadeMin);
			prstate.setInt(2, idadeMax);
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				res = resultado.getString("NOME");
				result = resultado.getInt("c");
				
				if(idadeMin == 0) {
					__less18 = res;
				}
				else if(idadeMin == 18) {
					__1830 = res;
				}
				else if(idadeMin == 31) {
					__3150 = res;
				}
				else {
					__more50 = res;
				}
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return result;
	}
}

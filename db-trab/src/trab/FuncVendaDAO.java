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

public class FuncVendaDAO {
	
	private Connection connection;
	
	static int index = 0;
	
	public FuncVendaDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void inserir(FuncVenda fv) {

		String sql = 
				"INSERT INTO funcionario_venda (ID_FUNCIONARIO, ID_VENDA, COMISSAO) VALUES (?,?,?)";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setInt(1, fv.getId_funcionario());
			prstate.setInt(2, fv.getId_venda());
			prstate.setDouble(3, fv.getComissao());
			
			prstate.execute();
			
			prstate.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void remove(int id_funcionario, int id_venda) {
		String sql = "DELETE FROM funcionario_venda WHERE ID_FUNCIONARIO=? AND ID_VENDA=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_funcionario);
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
		String sql = "DELETE FROM funcionario_venda WHERE ID_VENDA=?";
		
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
	
	public FuncVenda buscarPorId(int id_funcionario, int id_venda){
		String sql = "SELECT * FROM funcionario_venda WHERE ID_FUNCIONARIO = ? AND ID_VENDA=?";
		
		FuncVenda fv = new FuncVenda();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_funcionario);
			prstate.setInt(2, id_venda);
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				fv.setId_funcionario(id_funcionario);
				fv.setId_venda(id_venda);
				fv.setComissao(resultado.getDouble("COMISSAO"));
			}
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return fv;
	}
	
	public Funcionario buscarPorIdFuncionario(int id_venda){
		String sql = "SELECT * FROM funcionario_venda WHERE ID_VENDA=?";
		
		FuncVenda fv = new FuncVenda();
		
		Funcionario funcionario = null;
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setInt(1, id_venda);
			
			ResultSet resultado = prstate.executeQuery();
			
			funcionario = new Funcionario();
			
			while(resultado.next()) {
				fv.setId_funcionario(resultado.getInt("ID_FUNCIONARIO"));
				fv.setComissao(resultado.getDouble("COMISSAO"));
			}
			
			FuncionarioDAO fndao = new FuncionarioDAO();
			funcionario = fndao.buscarPorId(fv.getId_funcionario());
			
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return funcionario;
	}
	
	public void alterar(FuncVenda fv) {
		this.remove(fv.getId_funcionario(), fv.getId_venda());
		FuncVendaDAO fvdao = new FuncVendaDAO();
		fvdao.inserir(fv);
	}
}

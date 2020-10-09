package trab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
	
	private Connection connection;
	
	public FuncionarioDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void inserir(Funcionario funcionario) {
		String sql = 
				"INSERT INTO funcionario (NOME, IDADE, SALARIO, HORAINICIO, HORAFIM) VALUES (?,?,?,?,?)";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, funcionario.getNome());
			prstate.setInt(2, funcionario.getIdade());
			prstate.setDouble(3, funcionario.getSalario());
			prstate.setString(4, funcionario.getHoraInicio());
			prstate.setString(5, funcionario.getHoraFim());
			
			prstate.execute();
			
			prstate.close();
		}
		catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Funcionario> buscarPorNome(String nome){
		String sql = "SELECT * FROM funcionario WHERE NOME LIKE UPPER(?) ORDER BY NOME";
		
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setString(1, new String("%" + nome + "%").toUpperCase());
			
			ResultSet resultado = prstate.executeQuery();
			
			while(resultado.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(resultado.getInt("ID"));
				funcionario.setNome(resultado.getString("NOME"));
				funcionario.setIdade(Integer.parseInt(resultado.getString("IDADE")));
				funcionario.setSalario(Double.parseDouble(resultado.getString("SALARIO")));
				funcionario.setHoraInicio(resultado.getString("HORAINICIO"));
				funcionario.setHoraFim(resultado.getString("HORAFIM"));
				
				funcionarios.add(funcionario);
			}
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return funcionarios;
	}
	
	public void remove(Long id) {
		String sql = "DELETE FROM funcionario WHERE ID=?";
		
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
	
	public Funcionario buscarPorId(int id){
		String sql = "SELECT * FROM funcionario WHERE ID = ?";
		
		Funcionario funcionario = null;
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			prstate.setLong(1, id);
			
			ResultSet resultado = prstate.executeQuery();
			resultado.next();
			
			funcionario = new Funcionario();
			funcionario.setId(resultado.getInt("ID"));
			funcionario.setNome(resultado.getString("NOME"));
			funcionario.setIdade(Integer.parseInt(resultado.getString("IDADE")));
			funcionario.setSalario(Double.parseDouble(resultado.getString("SALARIO")));
			funcionario.setHoraInicio(resultado.getString("HORAINICIO"));
			funcionario.setHoraFim(resultado.getString("HORAFIM"));
				
			resultado.close();
			prstate.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return funcionario;
	}
	
	public void alterar(Funcionario funcionario) {
		String sql = "UPDATE funcionario SET NOME=?, IDADE=?, SALARIO=?, HORAINICIO=?, HORAFIM=? WHERE ID=?";
		
		try {
			PreparedStatement prstate = connection.prepareStatement(sql);
			
			prstate.setString(1, funcionario.getNome());
			prstate.setInt(2, funcionario.getIdade());
			
			prstate.setDouble(3, funcionario.getSalario());
			prstate.setString(4, funcionario.getHoraInicio());
			prstate.setString(5, funcionario.getHoraFim());
			prstate.setInt(6, funcionario.getId());
			
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

package trab;

import java.io.Serializable;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class FuncVenda implements Serializable{
	private int id_funcionario;
	private int id_venda;
	private double comissao;

	public int getId_venda() {
		return id_venda;
	}

	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	@Override
	public boolean equals(Object obj) {
		FuncVenda p = ((FuncVenda)obj);
		if(p.id_funcionario == this.id_funcionario && p.id_venda == this.id_venda) {
			return true;
		}
		return false;
	}
	
}
	
	
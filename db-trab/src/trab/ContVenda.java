package trab;

import java.io.Serializable;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class ContVenda implements Serializable{
	private int id_contato;
	private int id_venda;

	public int getId_venda() {
		return id_venda;
	}

	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}

	public int getId_contato() {
		return id_contato;
	}

	public void setId_contato(int id_contato) {
		this.id_contato = id_contato;
	}

	@Override
	public boolean equals(Object obj) {
		ContVenda p = ((ContVenda)obj);
		if(p.id_contato == this.id_contato && p.id_venda == this.id_venda) {
			return true;
		}
		return false;
	}
	
}
	
	
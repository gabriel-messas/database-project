package trab;

import java.io.Serializable;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Avaliacao implements Serializable{
	private int id;
	private int id_entrega;
	private String nota;
	private String observacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_entrega() {
		return id_entrega;
	}

	public void setId_entrega(int id_entrega) {
		this.id_entrega = id_entrega;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public boolean equals(Object obj) {
		Avaliacao p = ((Avaliacao)obj);
		if(p.id == this.id) {
			return true;
		}
		return false;
	}
	
}
	
	
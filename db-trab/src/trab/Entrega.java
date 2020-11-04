package trab;

import java.io.Serializable;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Entrega implements Serializable{
	private int id;
	private int id_venda;
	private String dataPrevista;
	private String dataEntrega;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_venda() {
		return id_venda;
	}

	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}

	public String getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	@Override
	public boolean equals(Object obj) {
		Entrega p = ((Entrega)obj);
		if(p.id == this.id) {
			return true;
		}
		return false;
	}
	
}
	
	
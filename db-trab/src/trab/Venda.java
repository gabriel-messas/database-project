package trab;

import java.io.Serializable;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Venda implements Serializable{
	public static int index = 0;
	
	private int id;
	private String data;
	private int quantidade;
	private double valorVenda;
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(double valorVenda) {
		this.valorVenda = valorVenda;
	}

	@Override
	public boolean equals(Object obj) {
		Venda p = ((Venda)obj);
		if(p.id == this.id) {
			return true;
		}
		return false;
	}
	
}
	
	
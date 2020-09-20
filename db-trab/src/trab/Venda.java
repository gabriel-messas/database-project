package trab;

import java.io.Serializable;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class Venda implements Serializable{
	private int id;
	private LinkedList<Produto> produtos;
	private Contato cliente;
	private double valorVenda;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LinkedList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(LinkedList<Produto> produtos) {
		this.produtos = produtos;
	}

	public Contato getCliente() {
		return cliente;
	}

	public void setCliente(Contato cliente) {
		this.cliente = cliente;
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
	
	
package trab;

import java.io.Serializable;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class ProdVenda implements Serializable{
	private int id_produto;
	private int id_venda;
	private int quantidade;
	private double preco;

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getId_venda() {
		return id_venda;
	}

	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}

	public int getId_produto() {
		return id_produto;
	}

	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}

	@Override
	public boolean equals(Object obj) {
		ProdVenda p = ((ProdVenda)obj);
		if(p.id_produto == this.id_produto && p.id_venda == this.id_venda) {
			return true;
		}
		return false;
	}
	
}
	
	
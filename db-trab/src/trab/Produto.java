package trab;

import java.io.Serializable;

public class Produto implements Serializable{
	private int id;
	private String nome;
	private double preco;
	private int quantidade;
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public double getPreco() {
		return preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	@Override
	public boolean equals(Object obj) {
		Produto p = ((Produto)obj);
		if(p.id == this.id) {
			return true;
		}
		return false;
	}
	
}
	
	
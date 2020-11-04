package trab;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Produto implements Serializable{
	private int id;
	private String nome;
	private double precoVenda;
	private String precoCompra1;
	private String precoCompra2;
	private int quantidade;
	private Contato fornecedor;
	
	public Contato getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Contato fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public double getPrecoVenda() {
		return precoVenda;
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
	
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getPrecoCompra1() {
		return precoCompra1;
	}
	public void setPrecoCompra1(String precoCompra1) {
		this.precoCompra1 = precoCompra1;
	}
	public String getPrecoCompra2() {
		return precoCompra2;
	}
	public void setPrecoCompra2(String precoCompra2) {
		this.precoCompra2 = precoCompra2;
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
	
	
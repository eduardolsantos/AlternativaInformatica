package br.com.alternativaInformatica;

public class Servico {
	private String id;
	private String descricao;
	private double preco;
	
	public Servico(String id){
		this.id = id;
	}
	
	public Servico(String descricao, double preco){
		this.descricao = descricao;
		this.preco = preco;
	}
	
	
	public Servico(String id, String descricao, double preco){
		this(descricao, preco);
		this.id = id;
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	public void setPreco(double preco){
		this.preco = preco;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getDescricao(){
		return this.descricao;
	}
	
	public double getPreco(){
		return this.preco;
	}
}
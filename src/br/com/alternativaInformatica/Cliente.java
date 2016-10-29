package br.com.alternativaInformatica;

public class Cliente {

	private String id = "";
	private String nome = "";
	private String contato = "";
	private String endereco = "";
	private String fone = "";
	private String bairro = "";
	private String cep = "";
	private String cidade = "";
	private String estado = "";
	private String cnpj = "";
	private String ie = "";
	private String email = "";

	public Cliente(String nome) {
		this.nome = nome;
	}
	
	public Cliente(String nome, String contato, String endereco, String fone, String bairro, 
			String cep, String cidade, String estado, String cnpj, String ie, String email, String id){
		this.nome = nome;
		this.contato = contato;
		this.endereco = endereco;
		this.fone = fone;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.cnpj = cnpj;
		this.ie = ie;
		this.email = email;
		this.id = id;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getContato(){
		return this.contato;
	}
	
	public String getEndereco(){
		return this.endereco;
	}
	
	public String getFone(){
		return this.fone;
	}
	
	public String getBairro(){
		return this.bairro;
	}
	
	public String getCep(){
		return this.cep;
	}
	
	public String getCidade(){
		return this.cidade;
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public String getCnpj(){
		return this.cnpj;
	}
	
	public String getIE(){
		return this.ie;
	}
	
	public String getEmail(){
		return this.email;
	}


}

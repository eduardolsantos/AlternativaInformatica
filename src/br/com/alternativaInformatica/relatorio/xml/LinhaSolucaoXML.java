package br.com.alternativaInformatica.relatorio.xml;

public class LinhaSolucaoXML {
	private String solucao;
	private double valor;
	
	public LinhaSolucaoXML(String solucao, double valor) {
		this(solucao);
		this.valor = valor;
	}
	
	public LinhaSolucaoXML(String solucao) {
		this.solucao = solucao;
		this.valor = 0;
	}
	
	public void setValor(double valor) {
		if (valor >= 0) {
			this.valor = valor;
		}
	}
	
	public String getSolucao() {
		return this.solucao;
	}
	
	public String getValorStr() {
		return "R$" + String.format("%.2f", this.valor);
	}
	
	public double getValor() {
		return this.valor;
	}
}

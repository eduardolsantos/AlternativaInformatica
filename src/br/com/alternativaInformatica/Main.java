package br.com.alternativaInformatica;

public class Main {
	public static void main(String[] args) {
		InterfaceTextual tmpIE = new InterfaceTextual();
		
		try {
			tmpIE.executar();
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}

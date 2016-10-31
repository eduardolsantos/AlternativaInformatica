package br.com.alternativaInformatica.relatorio.xml;

import org.w3c.dom.Element;

public class TextoXML extends ItemXML {
	private String texto;
	
	public TextoXML(Element node) throws Exception {
		carregar(node);
	}
	
	@Override
	protected void carregar(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar Texto.");
		}
		else {
			carregarFormatacao(node);
			this.texto = node.getTextContent();
		}
	}
	
	@Override
	public String toString() {
		return this.texto.replace("|", "<br>");
	}
}

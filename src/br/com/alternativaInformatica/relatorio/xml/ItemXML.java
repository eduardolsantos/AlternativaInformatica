package br.com.alternativaInformatica.relatorio.xml;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public abstract class ItemXML {
	private String formatacao;
	
	public ItemXML() {
		this.formatacao = "";
	}
	
	protected void carregarFormatacao(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar formatação.");
		}
		else {
			NamedNodeMap tmpAtributos = node.getAttributes();
			StringBuffer tmpFormatacao = new StringBuffer();
			
			for (int i = 0; i < tmpAtributos.getLength(); i++) {
				Node tmpAtributo = tmpAtributos.item(i);
				
				tmpFormatacao.append(tmpAtributo.getNodeName());
				tmpFormatacao.append("=\"");
				tmpFormatacao.append(tmpAtributo.getNodeValue());
				tmpFormatacao.append("\" ");
			}
			
			this.formatacao = tmpFormatacao.toString();
		}
	}
	
	public void setFormatacao(String formatacao) {
		if (formatacao != null) {
			this.formatacao = formatacao;
		}
	}
	
	public String getFormatacao() {
		return this.formatacao;
	}
	
	protected abstract void carregar(Element node) throws Exception;
}

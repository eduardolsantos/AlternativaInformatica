package br.com.alternativaInformatica.relatorio.xml;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class ImagemXML extends ItemXML{
	private String altura;
	private String largura;
	private String src;
	
	public ImagemXML(Element node) throws Exception {
		carregar(node);
	}

	@Override
	protected void carregar(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar imagem.");
		}
		else {
			this.carregarFormatacao(node);
			
			this.altura = node.getAttribute(ConstantesXML.ATR_IMAGEM_LARGURA);
			this.largura = node.getAttribute(ConstantesXML.ATR_IMAGEM_ALTURA);
			this.src = node.getAttribute(ConstantesXML.ATR_IMAGEM_SRC);
		}
	}
	
	@Override
	protected void carregarFormatacao(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar formatação da imagem.");
		}
		else {
			NamedNodeMap tmpAtributos = node.getAttributes();
			StringBuffer tmpFormatacao = new StringBuffer();
			
			for (int i = 0; i < tmpAtributos.getLength(); i++) {
				Node tmpAtributo = tmpAtributos.item(i);
				
				if (!tmpAtributo.getNodeName().equals(ConstantesXML.ATR_IMAGEM_SRC)) {
					tmpFormatacao.append(tmpAtributo.getNodeName());
					tmpFormatacao.append("=\"");
					tmpFormatacao.append(tmpAtributo.getNodeValue());
					tmpFormatacao.append("\" ");
				}
			}
			
			super.setFormatacao(tmpFormatacao.toString());
		}
	}
	
	@Override
	public String toString() {
		return "<img width=\"" + this.largura + "\" height=\"" + this.altura + "\" src=\"" + this.src + "\" />";
	}
}

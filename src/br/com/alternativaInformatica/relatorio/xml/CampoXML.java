package br.com.alternativaInformatica.relatorio.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class CampoXML extends ItemXML {
	private String texto;
	private String valor;
	private String valorDefault;
	private String complemento;
	
	public CampoXML(Element node) throws Exception {
		carregar(node);
	}
	
	public void setValor(String valor) {
		if (valor != null) {
			this.valor = valor;
		}
	}
	
	public String getValor() {
		return this.valor;
	}
	
	public void setValorDefault(String valorDefault) {
		if (this.valorDefault != null) {
			this.valorDefault = valorDefault;
		}
	}
	
	public String getValorDefault() {
		return this.valorDefault;
	}
	
	public void setTexto(String texto) {
		if (texto != null) {
			this.texto = texto;
		}
	}
	
	@Override
	protected void carregar(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar campo.");
		}
		else {
			this.carregarFormatacao(node);
			
			this.texto = node.getAttribute(ConstantesXML.ATR_CAMPO_TEXTO);
			this.valor = node.getAttribute(ConstantesXML.ATR_CAMPO_VALOR);
			this.valorDefault = valor;
			this.complemento = node.getAttribute(ConstantesXML.ATR_CAMPO_COMPLEMENTO);
		}
	}
	
	@Override
	protected void carregarFormatacao(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar formatação do campo.");
		}
		else {
			NamedNodeMap tmpAtributos = node.getAttributes();
			StringBuffer tmpFormatacao = new StringBuffer();
			
			List<String> tmpAtributosIgnorados = new ArrayList<>();
			tmpAtributosIgnorados.add(ConstantesXML.ATR_CAMPO_TEXTO);
			tmpAtributosIgnorados.add(ConstantesXML.ATR_CAMPO_VALOR);
			tmpAtributosIgnorados.add(ConstantesXML.ATR_CAMPO_COMPLEMENTO);
			
			for (int i = 0; i < tmpAtributos.getLength(); i++) {
				Node tmpAtributo = tmpAtributos.item(i);
				
				if (!tmpAtributosIgnorados.contains(tmpAtributo.getNodeName())) {
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
		return this.texto + this.valor + this.complemento;
	}
}

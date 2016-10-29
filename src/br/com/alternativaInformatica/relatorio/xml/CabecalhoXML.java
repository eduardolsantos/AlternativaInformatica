package br.com.alternativaInformatica.relatorio.xml;

import org.w3c.dom.Element;

import br.com.alternativaInformatica.utils.XMLUtils;

public class CabecalhoXML extends ItemXML {
	private ItemXML logo;
	private ItemXML titulo;
	private ItemXML subtitulo;
	private ItemXML info1;
	private ItemXML info2;
	
	public CabecalhoXML(Element node) throws Exception {
		carregar(node);
	}

	@Override
	protected void carregar(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar cabeçalho.");
		}
		else {
			Element tmpChild;
			carregarFormatacao(node);
			
			tmpChild = XMLUtils.getFirstChildElement(node);
			this.logo = new ImagemXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.titulo = new TextoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.subtitulo = new TextoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.info1 = new TextoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.info2 = new TextoXML(tmpChild);
		}
	}
	
	@Override
	public String toString() {
		final String COL_INICIO = "<td ";
		final String COL_FIM = "</td>";
		final String LINHA_INICIO = "<tr>";
		final String LINHA_FIM = "</tr>";
		
		StringBuffer tmpResult = new StringBuffer();
		
		tmpResult.append("<table ");
		tmpResult.append(super.getFormatacao());
		tmpResult.append('>');
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.logo.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.logo.toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.titulo.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.titulo.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
			
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.subtitulo.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.subtitulo.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);	
			
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.info1.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.info1.toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.info2.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.info2.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
			
			tmpResult.append(LINHA_INICIO);
				tmpResult.append("<td colspan=\"3\" style=\"border-bottom: 3px solid blue;\" />");
			tmpResult.append(LINHA_FIM);
		tmpResult.append("</table>");
		
		return tmpResult.toString();
	}
}

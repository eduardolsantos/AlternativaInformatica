package br.com.alternativaInformatica.relatorio.xml;

import org.w3c.dom.Element;

import br.com.alternativaInformatica.utils.DateUtils;
import br.com.alternativaInformatica.utils.XMLUtils;

public class RodapeXML extends ItemXML {
	private TextoXML autoriza;
	private TextoXML naoAutoriza;
	private CampoXML dataAssinatura;
	private TextoXML tecnico;
	private TextoXML cliente;
	private CampoXML garantia;
	
	public RodapeXML(Element node) throws Exception {
		carregar(node);
	}

	private void carregarAutorizacao(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar rodapé.");
		}
		else {
			Element tmpChild;
			
			tmpChild = XMLUtils.getFirstChildElement(node);
			this.autoriza = new TextoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.naoAutoriza = new TextoXML(tmpChild);
		}
	}
	
	private void carregarAssinatura(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar rodapé.");
		}
		else {
			Element tmpChild;
			
			tmpChild = XMLUtils.getFirstChildElement(node);
			this.tecnico = new TextoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.cliente = new TextoXML(tmpChild);
		}
	}
	
	@Override
	protected void carregar(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar rodapé.");
		}
		else {
			Element tmpChild;
			
			tmpChild = XMLUtils.getFirstChildElement(node);
			carregarAutorizacao(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.dataAssinatura = new CampoXML(tmpChild);
			this.dataAssinatura.setValor(tmpChild.getAttribute(ConstantesXML.ATR_CAMPO_VALOR) + DateUtils.getCurrentYear());
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			carregarAssinatura(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.garantia = new CampoXML(tmpChild);
		}
	}
	
	@Override
	public String toString() {
		final String COL_INICIO = "<td ";
		final String COL_FIM = "</td>";
		final String LINHA_INICIO = "<tr>";
		final String LINHA_FIM = "</tr>";
		final String TABELA_INICIO = "<table align=\"center\" style=\"width: 680px; text-align: center;\">";
		final String TABELA_FIM = "</table>";
		final String CHECKBOX = "<input type=\"checkbox\" style=\"width: 20px; height: 20px; padding: 3px;\"/>";
		final String ASSINATURA = "<td>______________________________</td>";
		
		StringBuffer tmpResult = new StringBuffer();
		
		tmpResult.append("<br>");
		tmpResult.append(TABELA_INICIO);
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append('>');
				tmpResult.append(CHECKBOX);
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.autoriza.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.autoriza.toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append('>');
				tmpResult.append(CHECKBOX);
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.naoAutoriza.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.naoAutoriza.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
		tmpResult.append(TABELA_FIM);
		
		tmpResult.append("<p ");
		tmpResult.append(this.dataAssinatura.getFormatacao());
		tmpResult.append('>');
		tmpResult.append(this.dataAssinatura.toString());
		tmpResult.append("</p><br><br>");
		
		tmpResult.append(TABELA_INICIO);
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(ASSINATURA);
				tmpResult.append(ASSINATURA);
			tmpResult.append(LINHA_FIM);
			
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.tecnico.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.tecnico.toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.cliente.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.cliente.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
		tmpResult.append(TABELA_FIM);
		
		tmpResult.append("<br><br>");
		
		tmpResult.append(TABELA_INICIO);
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.garantia.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.garantia.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
		tmpResult.append(TABELA_FIM);
		
		return tmpResult.toString();
	}
}

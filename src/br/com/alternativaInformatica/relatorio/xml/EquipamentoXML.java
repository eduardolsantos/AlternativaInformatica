package br.com.alternativaInformatica.relatorio.xml;

import org.w3c.dom.Element;

import br.com.alternativaInformatica.utils.XMLUtils;

public class EquipamentoXML extends ItemXML {
	private CampoXML descricao;
	private CampoXML acessorios;
	private CampoXML nroSerie;
	private CampoXML defeito;
	
	public EquipamentoXML(Element node) throws Exception {
		carregar(node);
	}
	
	public void setEquipamento(String equipamento) {
		this.descricao.setValor(equipamento);
	}
	
	public void setDefeito(String defeito) {
		this.defeito.setValor(defeito);
	}
	
	public void clear() {
		this.descricao.setValor(this.descricao.getValorDefault());
		this.acessorios.setValor(this.acessorios.getValorDefault());
		this.nroSerie.setValor(this.nroSerie.getValorDefault());
		this.defeito.setValor(this.defeito.getValorDefault());
	}
	
	@Override
	protected void carregar(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar Equipamento.");
		}
		else {
			Element tmpChild;
			
			carregarFormatacao(node);
			
			tmpChild = XMLUtils.getFirstChildElement(node);
			this.descricao = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.acessorios = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.nroSerie = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.defeito = new CampoXML(tmpChild);
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
				tmpResult.append(this.descricao.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.descricao.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);

			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.acessorios.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.acessorios.toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.nroSerie.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.nroSerie.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
			
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.defeito.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.defeito.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
		tmpResult.append("</table>");
		
		tmpResult.append("<br>");
			
		return tmpResult.toString();
	}
}

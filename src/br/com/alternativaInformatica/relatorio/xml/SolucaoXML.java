package br.com.alternativaInformatica.relatorio.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import br.com.alternativaInformatica.utils.DateUtils;
import br.com.alternativaInformatica.utils.XMLUtils;

public class SolucaoXML extends ItemXML {
	private CampoXML elaborado;
	private CampoXML aprovado;
	private CampoXML reprovado;
	private CampoXML liberado;
	private TextoXML[] cabecalho;
	private LinhaSolucaoXML total;
	private List<LinhaSolucaoXML> listaLinhas;
	
	public SolucaoXML(Element node) throws Exception {
		carregar(node);
		this.listaLinhas = new ArrayList<>();
	}
	
	public void addSolucao(LinhaSolucaoXML linha) {
		if (linha != null) {
			this.listaLinhas.add(linha);
			atualizarTotal();
		}
	}
	
	public void clear() {
		this.listaLinhas.clear();
		this.total.setValor(0);
		
		this.elaborado.setValor(this.elaborado.getValorDefault());
		this.aprovado.setValor(this.aprovado.getValorDefault());
		this.reprovado.setValor(this.reprovado.getValorDefault());
		this.liberado.setValor(this.liberado.getValorDefault());
	}
	
	private void atualizarTotal() {
		double tmpSoma = 0;
		
		for (LinhaSolucaoXML tmpLinha : this.listaLinhas) {
			tmpSoma += tmpLinha.getValor();
		}
		
		this.total.setValor(tmpSoma);
	}
	
	private void carregarCabecalho(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar cabeçalho da solução.");
		}
		else {
			Element tmpChild;
			
			this.cabecalho = new TextoXML[3];
			
			tmpChild = XMLUtils.getFirstChildElement(node);
			this.cabecalho[0] = new TextoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.cabecalho[1] = new TextoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.cabecalho[2] = new TextoXML(tmpChild);
		}
	}
	
	private void carregarOrcamento(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar orçamento.");
		}
		else {
			Element tmpChild;
			String tmpCurrentYear = DateUtils.getCurrentYear();
			
			tmpChild = XMLUtils.getFirstChildElement(node);
			this.elaborado = new CampoXML(tmpChild);
			this.elaborado.setValor(DateUtils.getCurrentDate());
			this.elaborado.setValorDefault(this.elaborado.getValor());

			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.aprovado = new CampoXML(tmpChild);
			this.aprovado.setValor(tmpChild.getAttribute(ConstantesXML.ATR_CAMPO_VALOR) + tmpCurrentYear);
			this.aprovado.setValorDefault(this.aprovado.getValor());
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.reprovado = new CampoXML(tmpChild);
			this.reprovado.setValor(tmpChild.getAttribute(ConstantesXML.ATR_CAMPO_VALOR) + tmpCurrentYear);
			this.reprovado.setValorDefault(this.reprovado.getValor());
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.liberado = new CampoXML(tmpChild);
			this.liberado.setValor(tmpChild.getAttribute(ConstantesXML.ATR_CAMPO_VALOR) + tmpCurrentYear);
			this.liberado.setValorDefault(this.liberado.getValor());
		}
	}
	
	private void carregarRodape(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar rodapé da solução.");
		}
		else {
			Element tmpChild = XMLUtils.getFirstChildElement(node);
			
			this.total = new LinhaSolucaoXML(tmpChild.getTextContent());
		}
	}
	
	@Override
	protected void carregar(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar solução.");
		}
		else {
			Element tmpChild;
			
			carregarFormatacao(node);
			
			tmpChild = XMLUtils.getFirstChildElement(node);
			carregarCabecalho(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			carregarOrcamento(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			carregarRodape(tmpChild);
		}
	}
	
	@Override
	public String toString() {
		final String COL_INICIO = "<td ";
		final String COL_FIM = "</td>";
		final String LINHA_INICIO = "<tr>";
		final String LINHA_FIM = "</tr>";
		final String VAZIO = "&nbsp;";
		
		StringBuffer tmpResult = new StringBuffer();
		String tmpFormatacaoSolucao = this.cabecalho[0].getFormatacao();
		String tmpFormatacaoValor = this.cabecalho[1].getFormatacao();
		String tmpFormatacaoOrcamento = this.cabecalho[2].getFormatacao();
		
		tmpResult.append("<table ");
		tmpResult.append(super.getFormatacao());
		tmpResult.append('>');
			//Cabeçalho
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append(tmpFormatacaoSolucao);
				tmpResult.append('>');
				tmpResult.append(this.cabecalho[0].toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(tmpFormatacaoValor);
				tmpResult.append('>');
				tmpResult.append(this.cabecalho[1].toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append('>');
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(tmpFormatacaoOrcamento);
				tmpResult.append('>');
				tmpResult.append(this.cabecalho[2].toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
			
			for(int i = 0; ((i < this.listaLinhas.size()) || (i < 6)); i++) {
				LinhaSolucaoXML tmpLinha = (i < this.listaLinhas.size())? this.listaLinhas.get(i) : null;
			
				tmpResult.append(LINHA_INICIO);
					tmpResult.append(COL_INICIO);
					tmpResult.append(tmpFormatacaoSolucao);
					tmpResult.append('>');
					if (tmpLinha == null) {
						tmpResult.append(VAZIO);
					}
					else {
						tmpResult.append(tmpLinha.getSolucao());
					}
					tmpResult.append(COL_FIM);
					
					tmpResult.append(COL_INICIO);
					tmpResult.append(tmpFormatacaoValor);
					tmpResult.append('>');
					if (tmpLinha == null) {
						tmpResult.append(VAZIO);
					}
					else {
						tmpResult.append(tmpLinha.getValorStr());
					}
					tmpResult.append(COL_FIM);
					
					tmpResult.append("<td>");
					tmpResult.append(VAZIO);
					tmpResult.append(COL_FIM);
					
					tmpResult.append(COL_INICIO);
					
					switch(i) {
						case 1:
							tmpResult.append(this.elaborado.getFormatacao());
							tmpResult.append('>');
							tmpResult.append(this.elaborado.toString());
							break;
							
						case 2:
							tmpResult.append(this.aprovado.getFormatacao());
							tmpResult.append('>');
							tmpResult.append(this.aprovado.toString());
							break;
							
						case 3:
							tmpResult.append(this.reprovado.getFormatacao());
							tmpResult.append('>');
							tmpResult.append(this.reprovado.toString());
							break;
							
						case 4:
							tmpResult.append(this.liberado.getFormatacao());
							tmpResult.append('>');
							tmpResult.append(this.liberado.toString());
							break;
							
						default:
							tmpResult.append(tmpFormatacaoOrcamento);
							tmpResult.append('>');
							tmpResult.append(VAZIO);
							break;
					}
					
					tmpResult.append(COL_FIM);
				tmpResult.append(LINHA_FIM);
			}
			
			//Rodapé
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append(tmpFormatacaoSolucao);
				tmpResult.append('>');
				tmpResult.append(this.total.getSolucao());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(tmpFormatacaoValor);
				tmpResult.append('>');
				tmpResult.append(this.total.getValorStr());
				tmpResult.append(COL_FIM);
				
				tmpResult.append("<td>");
				tmpResult.append(VAZIO);
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(tmpFormatacaoOrcamento);
				tmpResult.append('>');
				tmpResult.append(VAZIO);
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
		tmpResult.append("</table>");
		
		return tmpResult.toString();
	}
}

package br.com.alternativaInformatica.relatorio.xml;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import br.com.alternativaInformatica.Cliente;
import br.com.alternativaInformatica.utils.FileUtils;
import br.com.alternativaInformatica.utils.XMLUtils;

public class RelatorioXML {
	private CabecalhoXML cabecalho;
	private CorpoXML corpo;
	private RodapeXML rodape;
	
	public RelatorioXML(String caminho) throws Exception {
		carregar(caminho);
	}
	
	public void setCliente(Cliente cliente) {
		this.corpo.setCliente(cliente);
	}
	
	public void setEquipamento(String equipamento, String defeito) {
		this.corpo.setEquipamento(equipamento, defeito);
	}
	
	public void addServico(String servico, double valor) {
		this.corpo.addServico(servico, valor);
	}
	
	public void setNumeroOS(int numero) {
		this.corpo.setNumeroOS(numero);
	}
	
	public String getNumeroOS() {
		return this.corpo.getNumeroOS();
	}
	
	public void clear() {
		this.corpo.clear();
	}
	
	private void carregar(String caminho) throws Exception {
		try {
			Element tmpRaiz = XMLUtils.abrirXML(caminho);
			
			this.cabecalho = new CabecalhoXML(XMLUtils.recuperarReferencia(tmpRaiz, ConstantesXML.NODE_CABECALHO));
			this.corpo = new CorpoXML(XMLUtils.recuperarReferencia(tmpRaiz, ConstantesXML.NODE_CORPO));
			this.rodape = new RodapeXML(XMLUtils.recuperarReferencia(tmpRaiz, ConstantesXML.NODE_RODAPE));
			
		} catch (Exception e) {
			throw new Exception("Erro ao carregar relatório: " + e.getMessage());
		}
	}
	
	public void salvarRelatorio(String caminho) throws IOException {
		List<String> tmpLinhas = new ArrayList<>();
		tmpLinhas.add("<html><head /><body>");
		tmpLinhas.add(this.cabecalho.toString());
		tmpLinhas.add(this.corpo.toString());
		tmpLinhas.add(this.rodape.toString());
		tmpLinhas.add("</body></html>");
		
		FileUtils.salvarArquivo(caminho, tmpLinhas);
	}
}

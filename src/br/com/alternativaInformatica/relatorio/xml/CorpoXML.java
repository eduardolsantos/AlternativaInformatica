package br.com.alternativaInformatica.relatorio.xml;

import org.w3c.dom.Element;

import br.com.alternativaInformatica.Cliente;
import br.com.alternativaInformatica.utils.DateUtils;
import br.com.alternativaInformatica.utils.XMLUtils;

public class CorpoXML extends ItemXML {
	private CampoXML dataEntrada;
	private CampoXML nroOrdemServico;
	private SolucaoXML solucao;
	private ClienteXML cliente;
	private EquipamentoXML equipamento;
	
	public CorpoXML(Element node) throws Exception {
		carregar(node);
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente.setNome(cliente.getNome());
		this.cliente.setContato(cliente.getContato());
		this.cliente.setEndereco(cliente.getEndereco());
		this.cliente.setFone(cliente.getFone());
		this.cliente.setBairro(cliente.getBairro());
		this.cliente.setCep(cliente.getCep());
		this.cliente.setCidade(cliente.getCidade());
		this.cliente.setEstado(cliente.getEstado());
		this.cliente.setCNPJ(cliente.getCnpj());
		this.cliente.setIE(cliente.getIE());
		this.cliente.setEmail(cliente.getEmail());
	}
	
	public void setEquipamento(String equipamento, String defeito) {
		this.equipamento.setEquipamento(equipamento);
		this.equipamento.setDefeito(defeito);
	}
	
	public void addServico(String servico, double valor) {
		this.solucao.addSolucao(new LinhaSolucaoXML(servico, valor));
	}
	
	public void setNumeroOS(int numero) {
		this.nroOrdemServico.setValor(Integer.toString(numero));
	}
	
	public String getNumeroOS() {
		return this.nroOrdemServico.getValor();
	}
	
	private void carregarInicio(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar corpo.");
		}
		else {
			Element tmpChild = XMLUtils.getFirstChildElement(node);
			this.dataEntrada = new CampoXML(tmpChild);
			this.dataEntrada.setValor(tmpChild.getAttribute(ConstantesXML.ATR_CAMPO_VALOR) + DateUtils.getCurrentYear());
			this.dataEntrada.setValorDefault(this.dataEntrada.getValor());
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.nroOrdemServico = new CampoXML(tmpChild);
		}
	}
	
	public void clear() {
		this.dataEntrada.setValor(this.dataEntrada.getValorDefault());
		this.nroOrdemServico.setValor(this.nroOrdemServico.getValorDefault());
		
		this.solucao.clear();
		this.cliente.clear();
		this.equipamento.clear();
	}
	
	@Override
	protected void carregar(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar corpo.");
		}
		else {
			Element tmpChild;
			
			carregarFormatacao(node);
			
			tmpChild = XMLUtils.getFirstChildElement(node);
			carregarInicio(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.cliente = new ClienteXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.equipamento = new EquipamentoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.solucao = new SolucaoXML(tmpChild);
		}
	}
	
	@Override
	public String toString() {
		StringBuffer tmpResult = new StringBuffer();
		
		tmpResult.append("<table align=\"center\" style=\"width: 680px;\">");
			tmpResult.append("<tr>");
				tmpResult.append("<td ");
				tmpResult.append(this.dataEntrada.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.dataEntrada.toString());
				tmpResult.append("</td>");
				
				tmpResult.append("<td ");
				tmpResult.append(this.nroOrdemServico.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.nroOrdemServico.toString());
				tmpResult.append("</td>");
			tmpResult.append("</tr>");
		tmpResult.append("</table>");
		
		tmpResult.append(this.cliente.toString());
		tmpResult.append(this.equipamento.toString());
		tmpResult.append(this.solucao.toString());
		
		return tmpResult.toString();
	}
}

package br.com.alternativaInformatica.relatorio.xml;

import org.w3c.dom.Element;

import br.com.alternativaInformatica.utils.XMLUtils;

public class ClienteXML extends ItemXML {
	private CampoXML nome;
	private CampoXML contato;
	private CampoXML endereco;
	private CampoXML fone;
	private CampoXML bairro;
	private CampoXML cep;
	private CampoXML cidade;
	private CampoXML estado;
	private CampoXML cnpj;
	private CampoXML ie;
	private CampoXML email;
	
	
	public ClienteXML(Element node) throws Exception {
		carregar(node);
	}
	
	public void setNome(String nome) {
		this.nome.setValor(nome);
	}
	
	public void setContato(String contato) {
		this.contato.setValor(contato);
	}
	
	public void setEndereco(String endereco) {
		this.endereco.setValor(endereco);
	}
	
	public void setFone(String fone) {
		this.fone.setValor(fone);
	}
	
	public void setBairro(String bairro) {
		this.bairro.setValor(bairro);
	}
	
	public void setCep(String cep) {
		this.cep.setValor(cep);
	}
	
	public void setCidade(String cidade) {
		this.cidade.setValor(cidade);
	}
	
	public void setEstado(String estado) {
		this.estado.setValor(estado);
	}
	
	public void setCNPJ(String cnpj) {
		this.cnpj.setValor(cnpj);
	}
	
	public void setIE(String ie) {
		this.ie.setValor(ie);
	}
	
	public void setEmail(String email) {
		this.email.setValor(email);
	}
	
	@Override
	protected void carregar(Element node) throws Exception {
		if (node == null) {
			throw new Exception("Erro ao carregar Cliente.");
		}
		else {
			Element tmpChild;
			
			carregarFormatacao(node);
			
			tmpChild = XMLUtils.getFirstChildElement(node);
			this.nome = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.contato = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.endereco = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.fone = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.bairro = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.cep = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.cidade = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.estado = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.cnpj = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.ie = new CampoXML(tmpChild);
			
			tmpChild = XMLUtils.getNextSiblingElement(tmpChild);
			this.email = new CampoXML(tmpChild);
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
				tmpResult.append(this.nome.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.nome.toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.contato.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.contato.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
			
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.endereco.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.endereco.toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.fone.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.fone.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
			
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.bairro.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.bairro.toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.cep.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.cep.toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.cidade.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.cidade.toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.estado.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.estado.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
			
			tmpResult.append(LINHA_INICIO);
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.cnpj.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.cnpj.toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.ie.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.ie.toString());
				tmpResult.append(COL_FIM);
				
				tmpResult.append(COL_INICIO);
				tmpResult.append(this.email.getFormatacao());
				tmpResult.append('>');
				tmpResult.append(this.email.toString());
				tmpResult.append(COL_FIM);
			tmpResult.append(LINHA_FIM);
		tmpResult.append("</table>");
		tmpResult.append("<br>");
		
		return tmpResult.toString();
	}
	
	public void clear() {
		this.nome.setValor(this.nome.getValorDefault());
		this.contato.setValor(this.contato.getValorDefault());
		this.endereco.setValor(this.endereco.getValorDefault());
		this.fone.setValor(this.fone.getValorDefault());
		this.bairro.setValor(this.bairro.getValorDefault());
		this.cep.setValor(this.cep.getValorDefault());
		this.cidade.setValor(this.cidade.getValorDefault());
		this.estado.setValor(this.estado.getValorDefault());
		this.cnpj.setValor(this.cnpj.getValorDefault());
		this.ie.setValor(this.ie.getValorDefault());
		this.email.setValor(this.email.getValorDefault());
	}
}


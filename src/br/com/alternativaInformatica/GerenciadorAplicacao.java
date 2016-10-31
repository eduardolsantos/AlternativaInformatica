package br.com.alternativaInformatica;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.alternativaInformatica.database.DAOCliente;
import br.com.alternativaInformatica.database.DAOServico;
import br.com.alternativaInformatica.relatorio.xml.RelatorioXML;

public class GerenciadorAplicacao {
	private final String CAMINHO_MODELO = "\\files\\modelo\\modelo.xml";
	private final String CAMINHO_OS = "\\files\\os\\";

	private static GerenciadorAplicacao instance = null;

	private List<Cliente> clientes;
	private List<Servico> servicos;
	private RelatorioXML ordemServico;

	private GerenciadorAplicacao() {
		clientes = new ArrayList<>();
		servicos = new ArrayList<>();
	}

	public static GerenciadorAplicacao getInstance() {
		if (instance == null) {
			instance = new GerenciadorAplicacao();
		}

		return instance;
	}

	public int getCountClientes() {
		if (this.clientes == null) {
			return 0;
		}

		return this.clientes.size();
	}

	public Cliente getCliente(int index) {
		if ((this.clientes == null) || (this.clientes.size() == 0)) {
			return null;
		}

		return this.clientes.get(index);
	}

	public int getCountServicos() {
		if (this.servicos == null) {
			return 0;
		}

		return this.servicos.size();
	}

	public Servico getServico(int index) {
		if ((this.servicos == null) || (this.servicos.size() == 0)) {
			return null;
		}

		return this.servicos.get(index);
	}

	public void setNumeroOs(int numero){
		this.ordemServico.setNumeroOS(numero);
	}
	
	public void carregarClientes() throws ClassNotFoundException, SQLException {
		this.clientes=DAOCliente.getInstance().recuperarClientes();
	}

	public void carregarServicos() throws ClassNotFoundException, SQLException {
		this.servicos=DAOServico.getInstance().recuperarServicos();
	}

	public void carregarModeloRelatorio() throws Exception {
		this.ordemServico = new RelatorioXML(System.getProperty("user.dir") + CAMINHO_MODELO);
	}

	public void salvarOrdemServico(String ext) throws IOException {
		StringBuffer tmpCaminho = new StringBuffer();
		tmpCaminho.append(System.getProperty("user.dir"));
		tmpCaminho.append(CAMINHO_OS);
		tmpCaminho.append("OS");
		tmpCaminho.append(this.ordemServico.getNumeroOS());
		tmpCaminho.append(ext);

		this.ordemServico.salvarRelatorio(tmpCaminho.toString());
	}

	public void limparOrdemServico() {
		if (this.ordemServico != null) {
			this.ordemServico.clear();
		}
	}

	public void addClienteOrdemServico(Cliente cliente) {
		if (this.ordemServico != null) {
			this.ordemServico.setCliente(cliente);
		}
	}

	public void addEquipamentoOrdemServico(String equipamento, String defeito) {
		if (this.ordemServico != null) {
			this.ordemServico.setEquipamento(equipamento, defeito);
		}
	}

	public void addServicoOrdemServico(Servico servico) {
		if (this.ordemServico != null) {
			this.ordemServico.addServico(servico.getDescricao(), servico.getPreco());
		}
	}

	public void cadastrarCliente(Cliente cliente) throws ClassNotFoundException, SQLException {
		DAOCliente.getInstance().cadastrar(cliente);
		this.carregarClientes();
	}

	public void cadastrarServico(Servico servico) throws ClassNotFoundException, SQLException {
		DAOServico.getInstance().cadastrar(servico);
		this.carregarServicos();
	}
}

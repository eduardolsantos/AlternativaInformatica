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
	
	public void carregarClientes() throws ClassNotFoundException, SQLException {
//		List<Cliente> tmpClientesRecuperados = DAOCliente.getInstance().recuperarClientes();
//		
//		for (Cliente tmpCliente : tmpClientesRecuperados) {
//			this.clientes.add(tmpCliente);
//		}
		
		this.clientes.add(new Cliente("Rafael", "1688382","Rua do IFSP","1235123","Jardim da mata","1234122","Sao Carlos", "SP","12341222","industria", "asddas@has.com", Integer.toString(this.clientes.size() + 1)));
		this.clientes.add(new Cliente("Eduardo"));
		this.clientes.add(new Cliente("Victor"));
		this.clientes.add(new Cliente("João"));
		this.clientes.add(new Cliente("Maria"));
	}
	
	public void carregarServicos() throws ClassNotFoundException, SQLException {
//		List<Servico> tmpServicosRecuperados = DAOServico.getInstance().recuperarServicos();
//		
//		for(Servico tmpServico : tmpServicosRecuperados) {
//			this.servicos.add(tmpServico);
//		}
		
		this.servicos.add(new Servico(Integer.toString(this.servicos.size() + 1), "formatar", 50.00));
		this.servicos.add(new Servico(Integer.toString(this.servicos.size() + 1), "limpeza", 25.00));
		this.servicos.add(new Servico(Integer.toString(this.servicos.size() + 1), "BACKUP", 15.00));
		this.servicos.add(new Servico(Integer.toString(this.servicos.size() + 1), "troca de peça", 100.00));
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
		if (this.clientes == null) {
			this.clientes = new ArrayList<>();
		}
		
//		DAOCliente.getInstance().cadastrar(cliente);
		this.clientes.add(cliente);
	}
	
	public void cadastrarServico(Servico servico) throws ClassNotFoundException, SQLException {
		if (this.servicos == null) {
			this.servicos = new ArrayList<>();
		}
		
//		DAOServico.getInstance().cadastrar(servico);
		this.servicos.add(servico);
	}
}

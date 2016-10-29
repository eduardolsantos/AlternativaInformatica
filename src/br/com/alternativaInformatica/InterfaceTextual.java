package br.com.alternativaInformatica;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceTextual {
	private Scanner input;
	
	public InterfaceTextual() {
		this.input = new Scanner(System.in);
	}
	
	public void executar() throws Exception {
		byte tmpOpcao;
		GerenciadorAplicacao tmpGerenciador = GerenciadorAplicacao.getInstance();
		tmpGerenciador.carregarClientes();
		tmpGerenciador.carregarServicos();
		tmpGerenciador.carregarModeloRelatorio();
		
		do {
			tmpOpcao = getMenuOption();
			
			try {
				switch(tmpOpcao) {
					case 1:
						cadastrarCliente();
						break;
						
					case 2:
						cadastrarServico();
						break;
						
					case 3:
						criarOrdemServico(true);
						break;
					
					case 4:
						criarOrdemServico(false);
						break;
					
					case 5:
						input.close();
						break;
					
					default:
						System.out.println("Opção inválida.");
						break;
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		} while (tmpOpcao != 5);
	}
	
	private byte getMenuOption() {
		System.out.println(" == Menu ==");
		System.out.println(" [1] Cadastrar cliente");
		System.out.println(" [2] Cadastrar serviço");
		System.out.println(" [3] Criar Ordem de Serviço");
		System.out.println(" [4] Criar Ordem de Serviço Vazia");
		System.out.println(" [5] Sair");
		System.out.print(" -> ");

		try {
			return Byte.parseByte(input.nextLine());
		} catch (NumberFormatException e) {
			return -1;
		}
	}
	
	private void cadastrarCliente() {
		String tmpNome, tmpContato, tmpEndereco, tmpFone, tmpBairro, tmpCep, tmpCidade, tmpEstado, tmpCnpj, tmpIe, tmpEmail;
		
		System.out.println();
		System.out.println(" == Cadastro de Clientes ==");
		System.out.print(" Nome: ");
		tmpNome = this.input.nextLine();
		
		System.out.print(" Contato: ");
		tmpContato = this.input.nextLine();
		
		System.out.print(" Endereço: ");
		tmpEndereco = this.input.nextLine();
		
		System.out.print(" Fone: ");
		tmpFone = this.input.nextLine();
		
		System.out.print(" Bairro: ");
		tmpBairro = this.input.nextLine();
		
		System.out.print(" CEP: ");
		tmpCep = this.input.nextLine();
		
		System.out.print(" Cidade: ");
		tmpCidade = this.input.nextLine();
		
		System.out.print(" Estado: ");
		tmpEstado = this.input.nextLine();
		
		System.out.print(" CNPJ: ");
		tmpCnpj = this.input.nextLine();
		
		System.out.print(" I.E.: ");
		tmpIe = this.input.nextLine();
		
		System.out.print(" Email: ");
		tmpEmail = this.input.nextLine();
		
		String tmpId = Integer.toString(GerenciadorAplicacao.getInstance().getCountClientes() + 1);
		
		try {
			GerenciadorAplicacao.getInstance().cadastrarCliente(new Cliente(tmpId, tmpNome, tmpContato, tmpEndereco, tmpFone, tmpBairro, tmpCep, tmpCidade, tmpEstado, tmpCnpj, tmpIe, tmpEmail));
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Erro ao cadastrar cliente: " + e.getMessage());
		}
	}
	
	private void cadastrarServico() {
		String tmpDescricao;
		double tmpPreco;
		
		System.out.println();
		System.out.println(" == Cadastro de Serviços ==");
		System.out.print(" Serviço: ");
		tmpDescricao = this.input.nextLine();
		System.out.print(" Preço: ");
		try {
			tmpPreco = this.input.nextDouble();
			
			String tmpId = Integer.toString(GerenciadorAplicacao.getInstance().getCountServicos() + 1);
			
			GerenciadorAplicacao.getInstance().cadastrarServico(new Servico(tmpId, tmpDescricao, tmpPreco));
		} catch (InputMismatchException e) {
			System.out.println("Erro ao cadastrar serviço: Preço inválido.");
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar serviço: " + e.getMessage());
		}
		
		this.input.nextLine();
	}
	
	private void criarOrdemServico(boolean preenchida) {
		GerenciadorAplicacao tmpGerenciador = GerenciadorAplicacao.getInstance();
		boolean tmpSalvar = true;
		byte tmpOpcao;
		
		tmpGerenciador.limparOrdemServico();
		
		if (preenchida) {
			String tmpEquipamento, tmpDefeito;
			
			tmpOpcao = selecionarCliente();
			
			// Adicionando Cliente à OS
			if (tmpOpcao == tmpGerenciador.getCountClientes() + 1) {
				cadastrarCliente();
			}
			
			tmpGerenciador.addClienteOrdemServico(tmpGerenciador.getCliente(tmpOpcao - 1));
			
			
			// Adicionando Equipamento à OS
			System.out.println();
			System.out.println(" == Equipamento ==");
			System.out.print(" Equipamento: ");
			tmpEquipamento = this.input.nextLine();
			System.out.print(" Defeito: ");
			tmpDefeito = this.input.nextLine();
			tmpGerenciador.addEquipamentoOrdemServico(tmpEquipamento, tmpDefeito);
			
			// Adicionando Serviços à OS
			do {
				tmpOpcao = selecionarServico();
				
				if (tmpOpcao > 0) {
					if (tmpOpcao == tmpGerenciador.getCountServicos() + 2) {
						break;
					}
					else if (tmpOpcao == tmpGerenciador.getCountServicos() + 1) {
						cadastrarServico();
					}
					
					tmpGerenciador.addServicoOrdemServico(tmpGerenciador.getServico(tmpOpcao - 1));
				}
				else {
					System.out.println();
					System.out.println("Opção inválida.");
				}
				
				System.out.println();
			} while (tmpOpcao != tmpGerenciador.getCountServicos() + 2);
			
		}
		
		if (tmpSalvar) {
			try {
				System.out.println();
				System.out.println(" == Formato ==");
				System.out.println(" [1] doc");
				System.out.println(" [2] html");
				System.out.print(" -> ");
				
				tmpOpcao = Byte.parseByte(this.input.nextLine());
				
				if (tmpOpcao == 1) {
					tmpGerenciador.salvarOrdemServico(".doc");
				}
				else if (tmpOpcao == 2) {
					tmpGerenciador.salvarOrdemServico(".html");
				}
				else {
					System.out.println("Opção invalida.");
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (NumberFormatException e) {
				System.out.println("Opção inválida.");
			}
		}
	}

	private byte selecionarCliente() {
		System.out.println();
		System.out.println(" == Selecione o cliente ==");
		byte tmpCount = 1;
		
		for (int i = 0; i < GerenciadorAplicacao.getInstance().getCountClientes(); i++) {
			Cliente tmpCliente = GerenciadorAplicacao.getInstance().getCliente(i);
			System.out.println(" [" + tmpCount + "] " + tmpCliente.getNome());
			tmpCount++;
		}
		
		System.out.println(" [" + tmpCount + "] Novo...");
		System.out.print(" -> ");
		
		try {
			return input.nextByte();
		} catch (InputMismatchException e) {
			return -1;
		} finally {
			input.nextLine();
		}
	}
	
	private byte selecionarServico() {
		System.out.println();
		System.out.println(" == Selecione o(s) serviço(s) ==");
		byte tmpCount = 1;
		
		for (int i = 0; i < GerenciadorAplicacao.getInstance().getCountServicos(); i++) {
			Servico tmpServico = GerenciadorAplicacao.getInstance().getServico(i);
			
			System.out.println(" [" + tmpCount + "] " + tmpServico.getDescricao());
			tmpCount++;
		}
		
		System.out.println(" [" + tmpCount + "] Novo...");
		System.out.println(" [" + (tmpCount + 1) + "] Concluir");
		System.out.print(" -> ");	
		
		try {
			return input.nextByte();
		} catch (InputMismatchException e) {
			return -1;
		} finally {
			input.nextLine();
		}
	}
}

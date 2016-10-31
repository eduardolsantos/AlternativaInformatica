package br.com.alternativaInformatica.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.alternativaInformatica.Cliente;

public class DAOCliente extends DAO {
	private static DAOCliente instance = null;

	public static DAOCliente getInstance() {
		if (instance == null) {
			instance = new DAOCliente();
		}

		return instance;
	}

	/**
	 * Cadastra o cliente no Banco de Dados.
	 * @param cliente O cliente a ser cadastrado.
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void cadastrar(Cliente cliente) throws ClassNotFoundException, SQLException {
		if (cliente == null) {
//			throw new Exception("Cliente inválido.");
		}
		
		StringBuffer tmpSql = new StringBuffer();
		tmpSql.append("INSERT INTO Cliente ");
		tmpSql.append("(nome, contato, endereco, fone, bairro, cep, cidade, estado, cnpj, ie, email)");
		tmpSql.append(" VALUES ('");
		tmpSql.append(cliente.getNome());
		tmpSql.append("','");
		tmpSql.append(cliente.getContato());
		tmpSql.append("','");
		tmpSql.append(cliente.getEndereco());
		tmpSql.append("','");
		tmpSql.append(cliente.getFone());
		tmpSql.append("','");
		tmpSql.append(cliente.getBairro());
		tmpSql.append("','");
		tmpSql.append(cliente.getCep());
		tmpSql.append("','");
		tmpSql.append(cliente.getCidade());
		tmpSql.append("','");
		tmpSql.append(cliente.getEstado());
		tmpSql.append("','");
		tmpSql.append(cliente.getCnpj());
		tmpSql.append("','");
		tmpSql.append(cliente.getIE());
		tmpSql.append("','");
		tmpSql.append(cliente.getEmail());
		tmpSql.append("');");

		super.executarAtualizacao(tmpSql.toString());
	}

	public void editar(Cliente cliente) {
		//TODO Implementar edição de cadastro.
	}

	public ArrayList<Cliente> recuperarClientes() throws SQLException, ClassNotFoundException {
		
		super.conectar();
		
		ArrayList<Cliente> tmpClientes = new ArrayList<Cliente>();
		
		ResultSet tmpConsulta = super.executarConsulta("SELECT * FROM cliente ORDER BY nome ASC;");
		while (tmpConsulta.next()) {
			Cliente tmpCliente = new Cliente(tmpConsulta.getString("nome"), tmpConsulta.getString("contato"),
					tmpConsulta.getString("endereco"), tmpConsulta.getString("fone") ,tmpConsulta.getString("bairro"),
					tmpConsulta.getString("cep"), tmpConsulta.getString("cidade"), tmpConsulta.getString("estado"),
					"", tmpConsulta.getString("ie"), tmpConsulta.getString("email"),
					tmpConsulta.getString("id"));
			
			tmpClientes.add(tmpCliente);
		}

		super.desconectar();

		return tmpClientes;
	}
}

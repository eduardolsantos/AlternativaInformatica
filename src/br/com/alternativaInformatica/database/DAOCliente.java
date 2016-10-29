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

	public void cadastrar(Cliente cliente) throws ClassNotFoundException, SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT into Cliente ");
		sql.append("(nome, contato, endereco, fone, bairro, cep, cidade, estado, cnpj, ie, email)");
		sql.append(" values ('");
		sql.append(cliente.getNome());
		sql.append("','");
		sql.append(cliente.getContato());
		sql.append("','");
		sql.append(cliente.getEndereco());
		sql.append("','");
		sql.append(cliente.getFone());
		sql.append("','");
		sql.append(cliente.getBairro());
		sql.append("','");
		sql.append(cliente.getCep());
		sql.append("','");
		sql.append(cliente.getCidade());
		sql.append("','");
		sql.append(cliente.getEstado());
		sql.append("','");
		sql.append(cliente.getCnpj());
		sql.append("','");
		sql.append(cliente.getIE());
		sql.append("','");
		sql.append(cliente.getEmail());
		sql.append("');");

		super.conectar();
		super.executarAtualizacao(sql.toString());
		super.desconectar();

	}

	public void editar() {

	}

	public ArrayList<Cliente> recuperarClientes() throws SQLException, ClassNotFoundException {
		
		super.conectar();
		
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		ResultSet tmpConsulta = super.executarConsulta("select * from cliente;");
		while (tmpConsulta.next()) {
			Cliente tmpCliente = new Cliente(tmpConsulta.getString("nome"), tmpConsulta.getString("contato"),
					tmpConsulta.getString("endereco"), tmpConsulta.getString("fone") ,tmpConsulta.getString("bairro"),
					tmpConsulta.getString("cep"), tmpConsulta.getString("cidade"), tmpConsulta.getString("estado"),
					"", tmpConsulta.getString("ie"), tmpConsulta.getString("email"),
					tmpConsulta.getString("id"));
			
			clientes.add(tmpCliente);
		}

		super.desconectar();

		return clientes;
	}
}

package br.com.alternativaInformatica.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.alternativaInformatica.Servico;

public class DAOServico extends DAO {
	private static DAOServico instance = null;
	
	public static DAOServico getInstance() {
		if (instance == null) {
			instance = new DAOServico();
		}
		
		return instance;
	}
	
	public void cadastrar(Servico servico) throws ClassNotFoundException, SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT into Servico ");
		sql.append("(id, descricao, preco)");
		sql.append(" values ('");
		sql.append(servico.getId());
		sql.append("','");
		sql.append(servico.getDescricao());
		sql.append("','");
		sql.append(servico.getPreco());
		sql.append("');");

		super.conectar();
		super.executarAtualizacao(sql.toString());
		super.desconectar();
	}
	
public ArrayList<Servico> recuperarServicos() throws SQLException, ClassNotFoundException {
		
		super.conectar();
		
		ArrayList<Servico> servicos = new ArrayList<Servico>();
		
		ResultSet tmpConsulta = super.executarConsulta("select * from servico;");
		while (tmpConsulta.next()) {
			Servico tmpServico = new Servico(tmpConsulta.getString("id"), 
					tmpConsulta.getString("desceicao"),
					tmpConsulta.getDouble("preco"));
			servicos.add(tmpServico);
		}

		super.desconectar();

		return servicos;
	}
	
}
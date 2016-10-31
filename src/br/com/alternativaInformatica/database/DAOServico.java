package br.com.alternativaInformatica.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		sql.append("INSERT INTO Servico ");
		sql.append("(id, descricao, preco)");
		sql.append(" VALUES ('");
		sql.append(getMaxId());
		sql.append("','");
		sql.append(servico.getDescricao());
		sql.append("','");
		sql.append(servico.getPreco());
		sql.append("');");

		super.executarAtualizacao(sql.toString());
	}
	
	public int getMaxId() throws ClassNotFoundException, SQLException{
		super.conectar();
		ResultSet tmpConsulta = super.executarConsulta("SELECT max(id) FROM servico;");
		int id = 1;
		if( tmpConsulta.next() ){
			id = tmpConsulta.getInt("max(id)");
		}
		super.desconectar();
		return id+1;
	}
	
	public List<Servico> recuperarServicos() throws SQLException, ClassNotFoundException {
		ArrayList<Servico> servicos = new ArrayList<Servico>();
		super.conectar();
		
		ResultSet tmpConsulta = super.executarConsulta("SELECT * FROM servico ORDER BY descricao ASC;");
		while (tmpConsulta.next()) {
			Servico tmpServico = new Servico(tmpConsulta.getString("id"), 
					tmpConsulta.getString("descricao"),
					tmpConsulta.getDouble("preco"));
			servicos.add(tmpServico);
		}

		super.desconectar();

		return servicos;
	}
	
}
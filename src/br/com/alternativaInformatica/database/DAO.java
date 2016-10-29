package br.com.alternativaInformatica.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {
	private final String CAMINHO_DATABASE = "C:\\Users\\Eduardo\\Desktop\\Alternativa 2.0";	
	private Database database;
	
	protected DAO() {
		this.database = SQLite.getInstance();
		this.database.setCaminho(CAMINHO_DATABASE);
	}
	
	protected boolean conectar() throws ClassNotFoundException, SQLException {
		return this.database.conectar();
	}
	
	protected boolean desconectar() throws SQLException {
		return this.database.desconectar();
	}
	
	protected int executarAtualizacao(final String sql) throws ClassNotFoundException, SQLException {
		try {
			if (conectar()) {
				return this.database.executarAtualizacao(sql);
			}
		} finally {
			desconectar();
		}
		
		return 0;
	}
	
	protected ResultSet executarConsulta(final String sql) throws SQLException {
		return this.database.executarConsulta(sql);
	}
}

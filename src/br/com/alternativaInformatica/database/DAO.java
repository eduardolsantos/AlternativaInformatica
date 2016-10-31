package br.com.alternativaInformatica.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO {
	private final String CAMINHO_DATABASE = "\\db\\Alternativa 2.0";	
	private Database database;
	
	protected DAO() {
		this.database = SQLite.getInstance();
		
		// TODO caminho do Banco usando Resources
		this.database.setCaminho(System.getProperty("user.dir") + CAMINHO_DATABASE);
	}
	
	/**
	 * Estabelece uma conex�o com o Banco de Dados utilizando os valores usuario e 
	 * senha atuais da classe. Se usuario e senha forem null, tenta estabelecer uma 
	 * conex�o sem utilizar usu�rio e senha. 
	 * @return <code>true</code> se a conex�o foi estabelecida; <code>false</code> se n�o foi.
	 * 
	 * @throws ClassNotFoundException Se n�o for poss�vel carregar o Driver do Banco de Dados.
	 * @throws SQLException Se j� houver uma conex�o estabelecida ou n�o for poss�vel acessar o Banco de Dados.
	 */
	protected boolean conectar() throws ClassNotFoundException, SQLException {
		return this.database.conectar();
	}
	
	
	/**
	 *  Encerra a conex�o atual com o Banco de Dados. 
	 * 
	 * @return <code>true</code> se a conex�o foi encerrada; <code>false</code> se n�o foi.
	 * @throws SQLException Se n�o houver nenhuma Conex�o estabelecida ou n�o for poss�vel acessar o Banco de Dados.
	 */
	protected boolean desconectar() throws SQLException {
		return this.database.desconectar();
	}
	
	/**
	 * Envia a atualiza��o <code>sql</sql> para a classe Database.
	 * 
	 * @param sql O comando sql contendo a atualiza��o a ser feita. 
	 * Pode ser <b>INSERT</b>, <b>UPDATE</b>, <b>DELETE</b> ou comandos
	 * do tipo DDL que n�o retornam valor.
	 * @return A quantidade de linhas atualizadas ou 0, se for um comando DDL.
	 * @throws ClassNotFoundException Se houver algum erro ao tentar estabelecer uma conex�o com o Banco de Dados.
	 * @throws SQLException Se ocorrer algum erro ao tentar acessar o Banco de Dados.
	 */
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
	
	/**
	 * Envia a consulta <code>sql</code> para a classe Database.
	 * <br><br>
	 * <b>OBS.:</b> � necess�rio estabelecer uma conex�o antes de realizar a consulta
	 * e encerr�-la depois de manipular os resultados. A rotina n�o faz isso, pois,
	 * ao encerrar a conex�o, o ResultSet � fechado.
	 * 
	 * @param sql O comando sql contendo a consulta a ser realizada.
	 * @return Um ResultSet contendo o resultado da consulta (nunca null).
	 * 
	 * @throws SQLException Se n�o for poss�vel acessar o Banco de Dados ou n�o 
	 * houver nenhuma conex�o estabelecida.
	 */
	protected ResultSet executarConsulta(final String sql) throws SQLException {
		return this.database.executarConsulta(sql);
	}
}

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
	 * Estabelece uma conexão com o Banco de Dados utilizando os valores usuario e 
	 * senha atuais da classe. Se usuario e senha forem null, tenta estabelecer uma 
	 * conexão sem utilizar usuário e senha. 
	 * @return <code>true</code> se a conexão foi estabelecida; <code>false</code> se não foi.
	 * 
	 * @throws ClassNotFoundException Se não for possível carregar o Driver do Banco de Dados.
	 * @throws SQLException Se já houver uma conexão estabelecida ou não for possível acessar o Banco de Dados.
	 */
	protected boolean conectar() throws ClassNotFoundException, SQLException {
		return this.database.conectar();
	}
	
	
	/**
	 *  Encerra a conexão atual com o Banco de Dados. 
	 * 
	 * @return <code>true</code> se a conexão foi encerrada; <code>false</code> se não foi.
	 * @throws SQLException Se não houver nenhuma Conexão estabelecida ou não for possível acessar o Banco de Dados.
	 */
	protected boolean desconectar() throws SQLException {
		return this.database.desconectar();
	}
	
	/**
	 * Envia a atualização <code>sql</sql> para a classe Database.
	 * 
	 * @param sql O comando sql contendo a atualização a ser feita. 
	 * Pode ser <b>INSERT</b>, <b>UPDATE</b>, <b>DELETE</b> ou comandos
	 * do tipo DDL que não retornam valor.
	 * @return A quantidade de linhas atualizadas ou 0, se for um comando DDL.
	 * @throws ClassNotFoundException Se houver algum erro ao tentar estabelecer uma conexão com o Banco de Dados.
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
	 * <b>OBS.:</b> É necessário estabelecer uma conexão antes de realizar a consulta
	 * e encerrá-la depois de manipular os resultados. A rotina não faz isso, pois,
	 * ao encerrar a conexão, o ResultSet é fechado.
	 * 
	 * @param sql O comando sql contendo a consulta a ser realizada.
	 * @return Um ResultSet contendo o resultado da consulta (nunca null).
	 * 
	 * @throws SQLException Se não for possível acessar o Banco de Dados ou não 
	 * houver nenhuma conexão estabelecida.
	 */
	protected ResultSet executarConsulta(final String sql) throws SQLException {
		return this.database.executarConsulta(sql);
	}
}

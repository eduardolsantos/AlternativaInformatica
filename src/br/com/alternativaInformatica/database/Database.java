package br.com.alternativaInformatica.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Database {
	private String url_prefix;
	private String driver;
	private String caminho;
	private String usuario;
	private String senha;
	
	private Connection conexao;
	
	protected Database(String url_prefix, String driver) {
		this.url_prefix = url_prefix;
		this.driver = driver;
		this.conexao = null;
		this.usuario = null;
		this.senha = null;
	}
	
	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
		
	/**<blockquote><br><b><i>conectar</i></b><br><br>
	 * <code>public boolean conectar() throws ClassNotFoundException, SQLException</code><br><br>
	 * 
	 * Estabelece uma conexão com o Banco de Dados utilizando os valores <code><b>usuario</b></code> e <code><b>senha</b></code> atuais da
	 * classe. Se <code><b>usuario</b></code> e <code><b>senha</b></code> forem <code><b>null</b></code>, tenta estabelecer uma conexão 
	 * sem utilizar usuário e senha. <br><br>
	 * 
	 * @return <code>true</code> se a Conexão foi estabelecida com sucesso; <code>false</code> se a Conexão não foi estabelecida.
	 * 
	 * @throws ClassNotFoundException Se não for possível carregar o Driver.
	 * @throws SQLException Se já houver uma conexão estabelecida ou não for possível acessar o Banco de Dados.
	 */
	public boolean conectar() throws ClassNotFoundException, SQLException {
		try {
			if (!estaConectado()) {
				Class.forName(this.driver);
				
				if ((this.usuario != null) && (this.senha != null)) {
					this.conexao = DriverManager.getConnection(this.url_prefix + this.caminho, this.usuario, this.senha);
				}
				else {
					this.conexao = DriverManager.getConnection(this.url_prefix + this.caminho);
				}
				
				return estaConectado();
			}
			throw new SQLException("Erro ao estabelecer nova Conexão com o Banco de Dados: Já existe uma Conexão estabelecida.");
		} catch (ClassNotFoundException cnfe) {
			throw new ClassNotFoundException("Erro ao estabelecer Conexão com o Banco de Dados: Não foi possível carregar o Driver.");
		} catch (SQLException sqle) {
			throw new SQLException("Erro ao estabelecer Conexão com o Banco de Dados: Não foi possível acessar o Banco de Dados.");
		}
	}
	
	/**<blockquote><br><b><i>desconectar</i></b><br><br>
	 * <code>public boolean desconectar() throws SQLException</code><br><br>
	 * 
	 * Encerra a conexão atual com o Banco de Dados. <br><br>
	 * 
	 * @return <code>true</code> se a conexão foi encerrada; <code>false</code> se não foi.
	 * 
	 * @throws SQLException Se não houver nenhuma Conexão estabelecida ou não for possível acessar o Banco de Dados.
	 */
	public boolean desconectar() throws SQLException {
		if (estaConectado()) {
			this.conexao.close();
			this.conexao = null;
			
			return !estaConectado();
		}
		
		throw new SQLException("Erro ao encerrar Conexão com o Banco de Dados: Não há nenhuma Conexão estabelecida.");
	}
	
	/**<blockquote><br><b><i>estaConectado</i></b><br><br>
	 * <code>public boolean estaConectado() throws SQLException</code><br><br>
	 * 
	 * Verifica se há ou não uma conexão estabelecida. <br><br>
	 * 
	 * @return <code>true</code> se houver uma Conexão aberta; <code>false</code> se não houver.
	 * 
	 * @throws SQLException Se não for possível acessar o Banco de Dados.
	 */
	public boolean estaConectado() throws SQLException {
		try {
			return (this.conexao != null) && (!this.conexao.isClosed());
		} catch (SQLException sqle) {
			throw new SQLException("Não foi possível acessar o Banco de Dados.");
		}
	}
	
	/**<blockquote><br><b><i>executarAtualizacao</i></b><br><br>
	 * <code>public int executarAtualizacao(final String sql)  throws  SQLException</code><br><br>
	 * 
	 * Executa a atualização <b><code>sql</code></b>. 
	 * <br><blockquote>O comando SQL pode ser:
	 * <br>- Do tipo DML, como <code>INSERT</code>, <code>UPDATE</code> ou <code>DELETE</code>;
	 * <br>- Ou do tipo DDL que não retorna nada.</blockquote>
	 * 
	 * @param sql O comando SQL DML (Data Manipulation Language) contendo a atualização a ser executada. Ou um comando DDL que não retorna nada.
	 * 
	 * @return Ou o número de linhas atualizadas (para comandos DML). Ou 0, para comandos DDL.
	 * 
	 * @throws SQLException 
	 */
	public int executarAtualizacao(final String sql) throws SQLException {
		if (this.estaConectado()) {
			return this.conexao.createStatement().executeUpdate(sql);
		}
		
		throw new SQLException("Não foi possível consultar o Banco de Dados: Não há nenhuma conexão estabelecida com o Banco de Dados.");
	}
	
	/**<blockquote><br><b><i>executarConsulta</i></b><br><br>
	 * <code>public ResultSet executarConsulta(final String sql) throws  SQLException</code><br><br>
	 * 
	 * Executa a consulta <b><code>sql</code></b> e retorna um <b><code>ResultSet</code></b> contendo o resultado.
	 * 
	 * @param sql SQL contendo a consulta a ser realizada.
	 * 
	 * @return Um <code>ResultSet</code> contendo o resultado da consulta.
	 * <br><b>Obs.: </b> Nunca retorna null.
	 * 
	 * @throws SQLException Se não for possível acessar o Banco de Dados ou não houver nenhuma conexão estabelecida.
	 */
	public ResultSet executarConsulta(final String sql) throws  SQLException {
		try {
			if (this.estaConectado()) {
				return this.conexao.createStatement().executeQuery(sql);
			}
			
			throw new SQLException("Não foi possível consultar o Banco de Dados: Não há nenhuma conexão estabelecida com o Banco de Dados.");
		} catch (SQLException sqle) {
			throw new SQLException("Não foi possível consultar o Banco de Dados: Não foi possível acessar o Banco de Dados.");
		}
	}
}

package br.com.alternativaInformatica.database;

public class SQLite extends Database {
	private final static String URL_PREFIX = "jdbc:sqlite:";
	private final static String DRIVER = "org.sqlite.JDBC";
	
	private static SQLite instance = null;
	
	private SQLite() {
		super(URL_PREFIX, DRIVER);
	}
	
	/**<blockquote><b><i>getInstance()</i></b><br><br>
	 * <code>public static SQLite getInstance()</code><br><br>
	 * <b>Para utilizar essa classe, deve-se:</b><br>
	 * - Incluir o SQLite JDBC no Build Path do Projeto.<br><br>
	 * 
	 * @return Um Objeto que representa um Banco de Dados SQLite.
	 * 
	 * @see <a href="https://www.sqlite.org/docs.html">SQLite Documentation</a> para maiores informações.
	 */
	protected static SQLite getInstance() {
		if (instance == null) {
			instance = new SQLite();
		}
		
		return instance;
	}
}
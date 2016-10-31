package br.com.alternativaInformatica.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * Retorna a data atual, utilizando o formato especificado.<br>
	 * Exemplo: "dd/MM/yyyy", "HH:mm:ss", "yyyy", etc.
	 * @param format O formato desejado da data atual.
	 * @return A data atual de acordo com o formato especificado.
	 */
	public static String getDate(final String format) {
		DateFormat tmpDateFormat = new SimpleDateFormat(format);
		Date tmpDate = new Date();
		
		return tmpDateFormat.format(tmpDate);
	}
	
	/**
	 * Retorna a data atual.
	 * @return A data atual no formato: "dd/MM/yyyy".
	 */
	public static String getCurrentDate() {
		return getDate("dd/MM/yyyy");
	}
	
	/**
	 * Retorna a data atual para ser armazenada no Banco de Dados.
	 * @return A data atual no formato para ser armazenada no Banco de Dados.
	 */
	public static String getCurrentDateSql() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * Retorna o horário atual.
	 * @return O horário atual: horas, minutos e segundos.
	 */
	public static String getCurrentTime() {
		return getDate("HH:mm:ss");
	}
	
	/**
	 * O ano atual.
	 * @return O ano atual, utilizando 4 dígitos.
	 */
	public static String getCurrentYear() {
		return getDate("yyyy");
	}
}

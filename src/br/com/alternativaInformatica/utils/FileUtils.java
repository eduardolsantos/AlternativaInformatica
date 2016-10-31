package br.com.alternativaInformatica.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	/**
	 * Verifica se o diret�rio especificado em caminho existe. Se n�o existir, ele � criado.
	 * @param caminho O caminho a ser validado (pode ser para um arquivo).
	 */
	private static void validarDiretorio(String caminho) {
		File tmpDir = new File(caminho.substring(0, caminho.lastIndexOf("\\")));
		
		if (!tmpDir.exists()) {
			tmpDir.mkdirs();
		}
	}
	
	/**
	 * Cria o arquivo no caminho especificado utilizando as strings da lista <i>linhas</i>.
	 * @param caminho O caminho em que o arquivo deve ser salvo.
	 * @param linhas As linhas que o arquivo deve conter.
	 * @throws IOException Se ocorrer algum erro de Input ou Output.
	 */
	public static void salvarArquivo(final String caminho, final List<String> linhas) throws IOException {
		File tmpFile = new File(caminho);
		boolean tmpSalvar = false;
		int tmpCount = 1;
		
		// Se n�o existir uma pasta no caminho especificado, ela � criada
		validarDiretorio(tmpFile.getAbsolutePath());
		
		String tmpNome = caminho.substring(0, caminho.lastIndexOf('.'));
		String tmpExt = caminho.substring(caminho.lastIndexOf('.'));
		
		// Garante que os arquivos anteriores n�o sejam sobrescritos
		while(!tmpSalvar) {
			if (tmpFile.exists()) {
				tmpNome = caminho.substring(0, caminho.lastIndexOf('.')) + "(" + tmpCount + ")";
				tmpCount++;
				
				tmpFile = new File(tmpNome + tmpExt);
			}
			else {
				tmpSalvar = true;
			}
		}
		
		OutputStream tmpOutputStream = new FileOutputStream(tmpFile);
		OutputStreamWriter tmpOutputWriter = new OutputStreamWriter(tmpOutputStream);
		BufferedWriter tmpWriter = new BufferedWriter(tmpOutputWriter);
		
		try {
			// Escrevendo o Arquivo
			for (String tmpLinha : linhas) {
				tmpWriter.write(tmpLinha);
			}
		} finally {
			tmpWriter.close();
		}
	}
	
	
	/**
	 * L� o arquivo indicado em caminho como um texto e retorna uma lista de Strings contendo
	 * as linhas lidas.
	 * @param caminho 
	 * @return Uma lista de Strings contendo as linhas lidas do arquivo.
	 * @throws IOException Se o arquivo indicado n�o existir ou ocorrer algum erro de Input/Output.
	 */
	public static ArrayList<String> lerArquivo(final String caminho) throws IOException {
		ArrayList<String> tmpResult = new ArrayList<>();
		File tmpFile = new File(caminho);

		if (tmpFile.exists()) {
			InputStream tmpInputStream = new FileInputStream(tmpFile);
			InputStreamReader tmpInputReader = new InputStreamReader(tmpInputStream);
			BufferedReader tmpReader = new BufferedReader(tmpInputReader);
			
			String tmpLinha;
			
			try {
				while ((tmpLinha = tmpReader.readLine()) != null) {
					tmpResult.add(tmpLinha);
				}
			} finally {
				tmpReader.close();
			}
			
			return tmpResult;
		}
		
		throw new FileNotFoundException("O arquivo indicado n�o existe.");
	}
}
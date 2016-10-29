package br.com.alternativaInformatica.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
	// Se não existir uma pasta no caminho especificado, ela é criada
	private static void validarDiretorio(String caminho) {
		File tmpDir = new File(caminho.substring(0, caminho.lastIndexOf("\\")));
		
		if (!tmpDir.exists()) {
			tmpDir.mkdirs();
		}
	}
	
	public static void salvarArquivo(String caminho, List<String> linhas) throws IOException {
		File tmpFile = new File(caminho);
		boolean tmpSalvar = false;
		int tmpCount = 1;
		
		// Se não existir uma pasta no caminho especificado, ela é criada
		validarDiretorio(tmpFile.getAbsolutePath());
		
		String tmpNome = caminho.substring(0, caminho.lastIndexOf('.'));
		String tmpExt = caminho.substring(caminho.lastIndexOf('.'));
		
		// Garante que os arquivos anteriores não sejam sobrescritos
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
			for (String tmpLinha : linhas) {
				tmpWriter.write(tmpLinha);
			}
		} finally {
			tmpWriter.close();
		}
	}
		
	public static ArrayList<String> lerArquivo(String caminho) throws IOException {
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
		}
		
		return tmpResult;
	}
}
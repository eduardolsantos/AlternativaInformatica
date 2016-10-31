package br.com.alternativaInformatica.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XMLUtils {
	
	/**
	 * Converte um arquivo XML para uma árvore e retorna sua raiz.
	 * @param caminho O caminho do arquivo a ser convertido.
	 * @return A raiz da árvore montada.
	 * @throws Exception Se o arquivo não existir, não for do tipo XML ou se houver 
	 * algum erro para abrir, ler ou converter o arquivo indicado.
	 */
	public static Element abrirXML(final String caminho) throws Exception {
		if (caminho.toLowerCase().endsWith(".xml")) {
			File tmpArquivo = new File(caminho);
			
			if (tmpArquivo.exists()) {
				try {
					FileInputStream tmpInputStream = new FileInputStream(tmpArquivo);
					DocumentBuilderFactory tmpDocBuilderFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder tmpDocBuilder = tmpDocBuilderFactory.newDocumentBuilder();
					Document tmpXMLDoc;
					tmpXMLDoc = tmpDocBuilder.parse(tmpInputStream);
					
					return tmpXMLDoc.getDocumentElement();
				} catch (SAXException e) {
					throw new Exception("Erro ao converter arquivo.");
				} catch (ParserConfigurationException e) {
					throw new Exception("Erro ao abrir arquivo.");
				} catch (IOException e) {
					throw new IOException("Erro ao ler arquivo.");
				}
				
			}
			
			throw new FileNotFoundException("O arquivo indicado não existe.");
		}
		
		throw new Exception("Arquivo inválido. Indique apenas arquivos do tipo XML.");
	}
	
	// Considera apenas camada de filhos de parent. Desconsidera filhos de filhos.
	/**
	 * Retorna a referência do primeiro elemento filho de <i>parent</i>, cujo nome for 
	 * igual a <i>childName</i>.
	 * @param parent O pai do elemento desejado.
	 * @param childName O nome do elemento desejado. Obs.: case sensitive.
	 * @return O primeiro elemento filho de <i>parent</i> cujo nome seja igual a <i>childName</i>,
	 *  ou null caso nenhum elemento com esse nome seja encontrado.
	 */
	public static Element recuperarReferencia(final Element parent, final String childName) {
		Element tmpElement = getFirstChildElement(parent);
		
		while (tmpElement != null) {
			if (tmpElement.getTagName().equals(childName)) {
				return tmpElement;
			}
			
			tmpElement = getNextSiblingElement(tmpElement);
		}

		return null;
	}
	
	
	/**
	 * Retorna o próximo elemento irmão de <i>node</i>.
	 * @param node O elemento a ser usado para procurar o próximo irmão.
	 * @return O próximo elemento irmão de <i>node</i> ou null, caso não haja 
	 * mais irmãos.
	 */
	public static Element getNextSiblingElement(final Node node) {
		if (node != null) {
			Node tmpResult = node.getNextSibling();
			
			while (tmpResult != null) {
				if (Node.ELEMENT_NODE == tmpResult.getNodeType()) {
					return (Element) tmpResult;
				}
				
				tmpResult = tmpResult.getNextSibling();
			}
		}
		
		return null;
	}
	
	
	/**
	 * Retorna o primeiro elemento filho de <i>parent</i>.
	 * 
	 * @param parent O pai do elemento filho desejado.
	 * @return O primeiro elemento filho de <i>parent</i> ou null, caso não haja filhos.
	 */
	public static Element getFirstChildElement(final Node parent) {
		if (parent != null) {
			Node tmpResult = parent.getFirstChild();
			
			if (tmpResult != null) {
				if (Node.ELEMENT_NODE == tmpResult.getNodeType()) {
					return (Element) tmpResult;
				}
				else {
					return getNextSiblingElement(tmpResult);
				}
			}
		}
		
		return null;
	}
}

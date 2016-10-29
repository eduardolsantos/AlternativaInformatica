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
	public static Element abrirXML(String caminho) throws SAXException, IOException, ParserConfigurationException {
		File tmpArquivo = new File(caminho);
		
		if (tmpArquivo.exists()) {
			FileInputStream tmpInputStream = new FileInputStream(tmpArquivo);
			DocumentBuilderFactory tmpDocBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder tmpDocBuilder = tmpDocBuilderFactory.newDocumentBuilder();
			Document tmpXMLDoc = tmpDocBuilder.parse(tmpInputStream);
			Element tmpRaiz = tmpXMLDoc.getDocumentElement();
			
			return tmpRaiz;
		}
		
		throw new FileNotFoundException("O arquivo indicado não existe.");
	}
	
	// Considera apenas camada de filhos de parent. Desconsidera filhos de filhos.
	public static Element recuperarReferencia(Element parentTag, String tagName) {
		Element tmpElement = getFirstChildElement(parentTag);
		
		while (tmpElement != null) {
			if (tmpElement.getTagName().equals(tagName)) {
				return tmpElement;
			}
			
			tmpElement = getNextSiblingElement(tmpElement);
		}

		return null;
	}
	
	public static Element getNextSiblingElement(Node node) {
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
	
	public static Element getFirstChildElement(Node parentNode) {
		if (parentNode != null) {
			Node tmpResult = parentNode.getFirstChild();
			
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

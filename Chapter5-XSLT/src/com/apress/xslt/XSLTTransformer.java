package com.apress.xslt;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class XSLTTransformer {

	public static void main(String argv[]) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			File stylesheet = new File("removeDuplicates.xslt");
			File datafile = new File("catalog.xml");

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(datafile);

			TransformerFactory tFactory = TransformerFactory.newInstance();
			StreamSource stylesource = new StreamSource(stylesheet);
			Transformer transformer = tFactory.newTransformer(stylesource);

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(System.out);

			XSLTTransformer xsltTransformer = new XSLTTransformer();
			ErrorListenerImpl errorHandler = xsltTransformer.new ErrorListenerImpl();
			transformer.setErrorListener(errorHandler);
			transformer.transform(source, result);

			if (errorHandler.e != null) {
				System.out.println("Transformation Exception: "
						+ errorHandler.e.getMessage());
			}

		} catch (TransformerConfigurationException e) {

			System.out.println(e.getMessage());

		} catch (TransformerException e) {

			System.out.println(e.getMessage());
		} catch (SAXException e) {
			System.out.println(e.getMessage());

		} catch (ParserConfigurationException e) {

			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	private class ErrorListenerImpl implements ErrorListener {
		public TransformerException e = null;

		public void error(TransformerException exception) {
			this.e = exception;
		}

		public void fatalError(TransformerException exception) {
			this.e = exception;
		}

		public void warning(TransformerException exception) {
			this.e = exception;
		}
	}

}

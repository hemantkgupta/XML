package com.apress.validation.dom;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class DOMValidator {

	public void validateSchema(String SchemaUrl, String XmlDocumentUrl) {
		try {
                                      //Create DocumentBuilderFactory
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();

                                      //Set factory to be a validating factory.
			factory.setNamespaceAware(true);
			factory.setValidating(true);
                                       //Set schema attributes
			factory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
					"http://www.w3.org/2001/XMLSchema");
			factory.setAttribute(
				"http://java.sun.com/xml/jaxp/properties/schemaSource",
					SchemaUrl);

                                      //Create a DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();

                                      //Create a ErrorHandler and set ErrorHandler on DocumentBuilder 
                                         // parser 
			Validator handler = new Validator();
			builder.setErrorHandler(handler);

                                        //Parse XML Document
			builder.parse(XmlDocumentUrl);
                                       //Output Validation Errors
			if (handler.validationError == true)
				System.out.println("XML Document has Error:"
						+ handler.validationError + " "
						+ handler.saxParseException.getMessage());
			else
				System.out.println("XML Document is valid");
		} catch (java.io.IOException ioe) {
			System.out.println("IOException " + ioe.getMessage());
		} catch (SAXException e) {
			System.out.println("SAXException" + e.getMessage());
		} catch (ParserConfigurationException e) {
			System.out
					.println("ParserConfigurationException                    "
							+ e.getMessage());
		}
	}
             //ErrorHandler Class
	private class Validator extends DefaultHandler {
		public boolean validationError = false;

		public SAXParseException saxParseException = null;

		public void error(SAXParseException exception) throws SAXException {
			validationError = true;
			saxParseException = exception;
		}

		public void fatalError(SAXParseException exception) throws SAXException {
			validationError = true;
			saxParseException = exception;
		}

		public void warning(SAXParseException exception) throws SAXException {
		}
	}

	public static void main(String[] argv) {
		String SchemaUrl = "catalog.xsd";
		String XmlDocumentUrl = "catalog.xml";
		DOMValidator validator = new DOMValidator();
		validator.validateSchema(SchemaUrl, XmlDocumentUrl);
	}
}


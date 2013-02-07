package com.apress.validation.jaxp13;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.*;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

public class XMLSchemaValidator {
	public void validateXMLDocument(File schemaDocument, File xmlDocument) {
		try {      //Create SchemaFactory
			SchemaFactory factory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
                                      //Create Schema object
			Schema schema = factory.newSchema(schemaDocument);
                                     //  Create Validator and set ErrorHandler on Validator. 
			Validator validator = schema.newValidator();
			ErrorHandlerImpl errorHandler = new ErrorHandlerImpl();
			validator.setErrorHandler(errorHandler);
                                      //Validate XML Document
			StreamSource streamSource = new StreamSource(xmlDocument);
			validator.validate(streamSource);
                                      //Output Validation Errors
			if (errorHandler.validationError == true) {
				System.out.println("XML Document has Error:"
						+ errorHandler.validationError + " "
					+ errorHandler.saxParseException.getMessage());
			} else {
				System.out.println("XML Document is valid");
			}
		} catch (SAXException e) {
		} catch (IOException e) {
		}
	}

	public static void main(String[] argv) {
		File schema = new File("catalog.xsd");
		File xmlDocument = new File("catalog.xml");
		XMLSchemaValidator validator = new XMLSchemaValidator();
		validator.validateXMLDocument(schema, xmlDocument);
	}
                //ErrorHandler class
	private class ErrorHandlerImpl extends DefaultHandler {
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
}


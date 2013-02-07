package com.apress.jdomxpath;

import java.io.*;
import org.jdom.*;
import org.jdom.xpath.XPath;
import org.jdom.input.*;
import java.util.Iterator;

/**
 * This class illustrates executing of different type of XPath expressions,
 * using JDOM 1.0 XPath API.
 */
public class JDomXPath {

	public void parseDocument(File xmlDocument) {

		try {

			// Create a SAXBuilder parser
			SAXBuilder saxBuilder = new SAXBuilder(
					"org.apache.xerces.parsers.SAXParser");
			// Create a JDOM document object
			org.jdom.Document jdomDocument = saxBuilder.build(xmlDocument);

			// select level attribute in first article dated January-2004
			// in first journal
			org.jdom.Attribute levelNode = (org.jdom.Attribute) (XPath
					.selectSingleNode(jdomDocument,
							"/catalog//journal//article[@date='January-2004']/@level"));

			System.out.println(levelNode.getValue());

			// select title attribute in first article dated January-2004
			// in first journal
			org.jdom.Element titleNode = (org.jdom.Element) XPath
					.selectSingleNode(jdomDocument,
							"/catalog//journal//article[@date='January-2004']/title");

			System.out.println(titleNode.getText());

			// select title of all articles
			// in journal dated Java Technology
			java.util.List nodeList = XPath.selectNodes(jdomDocument,
					"/catalog/journal[@title='Java Technology']/article/title");

			Iterator iter = nodeList.iterator();

			while (iter.hasNext()) {
				org.jdom.Element element = (org.jdom.Element) iter.next();
				System.out.println(element.getText());

			}

			// Example of a xpath expression using namespace
			// Select level attribute in journal namespace
			// in first article in first journal in journal namespace
			XPath xpath = XPath
					.newInstance("/catalog/journal:journal/article/@journal:level");
			xpath.addNamespace("journal",
					"http://www.apress.com/catalog/journal");

			org.jdom.Attribute namespaceNode = (org.jdom.Attribute) xpath
					.selectSingleNode(jdomDocument);

			System.out.println(namespaceNode.getValue());

		} catch (IOException e) {
			e.printStackTrace();
		}

		catch (JDOMException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] argv) {
		JDomXPath parser = new JDomXPath();
		parser.parseDocument(new File("catalog.xml"));
	}

}

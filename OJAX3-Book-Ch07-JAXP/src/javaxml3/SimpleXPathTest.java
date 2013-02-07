package javaxml3;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SimpleXPathTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        System.out.println(XPathConstants.STRING.toString());
        System.out.println(XPathConstants.BOOLEAN.toString());
        System.out.println(XPathConstants.NUMBER.toString());
        System.out.println(XPathConstants.NODE.toString());
        System.out.println(XPathConstants.NODESET.toString());
        Document doc = createDocument();
        
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        System.out.println(xPath.evaluate("//child/text()", doc, XPathConstants.NODE).getClass());

    }

    private static Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document retval = dbf.newDocumentBuilder().newDocument();
        /*
        Document testDocument = new Document(new Element("parent").addContent(
                new Element("child").setText("child.text")).addContent(
                new Element("child").setText("child.text.2")));
        */
        Element parent = retval.createElement("parent");
        retval.appendChild(parent);
        
        Element child1 = retval.createElement("child");
        child1.setTextContent("child.text");
        parent.appendChild(child1);
        Element child2 = retval.createElement("child");
        child2.setTextContent("child.text.2");
        parent.appendChild(child2);
        
        return retval;
    }

}

package javaxml3;

import java.io.FileReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

public class GuestListCounter {

    public static void main(String[] args) throws Exception {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();

        Number shows = (Number) xPath.evaluate("count(/schedule/show)",
                new InputSource(new FileReader("tds.xml")),
                XPathConstants.NUMBER);
        System.out.println("Document has " + shows.intValue() + " shows.");
    }

}

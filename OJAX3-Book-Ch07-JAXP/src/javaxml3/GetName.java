package javaxml3;

import java.io.FileReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

public class GetName {

    public static void main(String[] args) throws Exception {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();

        String result = xPath.evaluate("/schedule/@name", new InputSource(
                new FileReader("tds.xml")));
        System.out.println(result);
    }
}

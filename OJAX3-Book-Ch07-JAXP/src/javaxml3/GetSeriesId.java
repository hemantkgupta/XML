package javaxml3;

import java.io.FileReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

public class GetSeriesId {

    public static void main(String[] args) throws Exception {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();

        Double result = (Double) xPath.evaluate("/schedule/@seriesId",
                new InputSource(new FileReader("tds.xml")),
                XPathConstants.NUMBER);
        System.out.println(result.intValue());
    }
}

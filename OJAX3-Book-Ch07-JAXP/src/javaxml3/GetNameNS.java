package javaxml3;

import java.io.FileReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

public class GetNameNS {
    public static void main(String[] args) throws Exception {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        SimpleNamespaceContext nsContext = new SimpleNamespaceContext();
        nsContext.addNamespace("s", "uri:comedy:schedule");
        xPath.setNamespaceContext(nsContext);

        String result = xPath.evaluate("/s:schedule/@name", new InputSource(
                new FileReader("tds_ns.xml")));
        System.out.println(result);
    }
}

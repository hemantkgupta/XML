package javaxml3;

import java.io.FileReader;
import java.io.StringWriter;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.xml.sax.InputSource;

import com.sun.org.apache.xml.internal.serialize.DOMSerializerImpl;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class GetNameAsAttr {

    public static void main(String[] args) throws Exception {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();

        Attr result = (Attr) xPath.evaluate("/schedule/@name", new InputSource(
                new FileReader("tds.xml")), XPathConstants.NODE);
        System.out.println(result.getValue());

        result.setValue("The Colbert Report");
        
    }
}

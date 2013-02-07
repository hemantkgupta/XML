package javaxml3;

import org.jdom.Document;
import org.jdom.UncheckedJDOMFactory;
import org.jdom.input.SAXBuilder;
import org.xml.sax.XMLReader;

import com.sun.org.apache.xerces.internal.parsers.XMLParser;

public class UncheckedFactorySample {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //Document doc = new Document();
        //doc.setRootElement(new Element("0bad"));
        
        SAXBuilder builder = new SAXBuilder();
        builder.setFeature("http://xml.org/sax/features/validation", false);
        builder.setFactory(new UncheckedJDOMFactory());
        Document doc = builder.build("bad.xml");
        System.out.println(doc.getRootElement().getName());

    }

}

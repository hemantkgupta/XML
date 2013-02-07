package javaxml3;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class AsXML {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader();
        Document doc = reader.read("tds.xml");
        
        XMLWriter writer = new XMLWriter(System.out);
        
        System.out.println("Document - asXML():");
        System.out.println(doc.asXML());
        System.out.println("Document - writer():");
        writer.write(doc);
        writer.flush();
        System.out.println();
        System.out.println("Element - asXML():");
        System.out.println(doc.getRootElement().element("show").asXML());
        System.out.println("Element - writer():");
        writer.write(doc.getRootElement().element("show"));
        writer.flush();
        System.out.println();
        System.out.println("Attribute - asXML()");
        System.out.println(doc.getRootElement().attribute(0).asXML());
        System.out.println("Attribute - writer()");
        writer.write(doc.getRootElement().attribute(0));
        writer.flush();
        System.out.println();
        System.out.println("Namespace - asXML()");
        System.out.println(doc.getRootElement().getNamespaceForPrefix("g").asXML());
        System.out.println("Namespace - writer()");
        writer.write(doc.getRootElement().getNamespaceForPrefix("g"));
        writer.flush();
        System.out.println();
    }

}

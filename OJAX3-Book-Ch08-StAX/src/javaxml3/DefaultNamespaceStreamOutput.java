package javaxml3;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class DefaultNamespaceStreamOutput {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(System.out);
        writer.setDefaultNamespace("http://www.example.com/ns1");
        writer.writeStartElement("http://www.example.com/ns1", "sample");
        writer.writeDefaultNamespace("http://www.example.com/ns1");
        writer.writeEmptyElement("inner");
        writer.setDefaultNamespace("http://www.example.com/ns2");
        writer.writeStartElement("http://www.example.com/ns2", "inner2");
        writer.writeDefaultNamespace("http://www.example.com/ns2");
        writer.writeNamespace("ns1", "http://www.example.com/ns1");
        writer.writeEmptyElement("http://www.example.com/ns1", "inner");
        
        writer.writeEndDocument();
        writer.flush();
    }

}

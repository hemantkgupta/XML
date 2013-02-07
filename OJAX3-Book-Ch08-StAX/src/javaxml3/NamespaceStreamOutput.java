package javaxml3;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class NamespaceStreamOutput {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(System.out);
        writer.writeStartDocument("1.0");
        writer.writeCharacters("\n");
        //writer.setPrefix("ns1", "http://www.example.com/ns1");
        //writer.writeStartElement("http://www.example.com/ns1", "sample");
        writer.writeStartElement("ns1", "sample", "http://www.example.com/ns1");
        writer.writeNamespace("ns1", "http://www.example.com/ns1");
        writer.writeNamespace("ns2", "http://www.example.com/ns2");
        //writer.setPrefix("ns2", "http://www.example.com/ns2");
        writer.writeAttribute("attribute", "true");
        writer.writeCharacters("\n");
        writer.writeEmptyElement("http://www.example.com/ns1", "inner");
        writer.writeCharacters("\n");
        writer.writeEmptyElement("http://www.example.com/ns2", "inner");
        writer.writeAttribute("otherAttribute", "true");
        writer.writeCharacters("\n");
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
        System.out.println();

    }

}

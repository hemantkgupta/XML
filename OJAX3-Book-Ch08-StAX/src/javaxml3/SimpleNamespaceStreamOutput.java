package javaxml3;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class SimpleNamespaceStreamOutput {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(System.out);
        writer.writeStartElement("ns1", "sample", "http://www.example.com/ns1");
        writer.writeNamespace("ns1", "http://www.example.com/ns1");
        writer.writeNamespace("ns2", "http://www.example.com/ns2");
        writer.writeAttribute("http://www.example.com/ns2", "attribute", "true");
        writer.writeCharacters("\n");
        writer.writeEmptyElement("http://www.example.com/ns1", "inner");
        writer.writeCharacters("\n");
        //writer.writeEmptyElement("http://www.example.com/ns2", "inner");
        writer.writeEmptyElement("ns2", "inner", "http://www.example.com/ns2");
        writer.writeCharacters("\n");
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
    }

}

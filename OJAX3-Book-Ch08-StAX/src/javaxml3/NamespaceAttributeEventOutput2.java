package javaxml3;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;

public class NamespaceAttributeEventOutput2 {

    public static void main(String[] args) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEventWriter writer = outputFactory.createXMLEventWriter(System.out);

        writer.add(eventFactory.createStartElement("ns1",
                "http://www.example.com/ns1", "sample", null, null));
        writer.add(eventFactory.createNamespace("ns1",
                "http://www.example.com/ns1"));
        writer.add(eventFactory.createNamespace("ns2",
                "http://www.example.com/ns2"));
        writer.add(eventFactory.createAttribute("ns2",
                "http://www.example.com/ns2", "attribute", "true"));
        writer.add(eventFactory.createEndDocument());
        writer.flush();
    }

}

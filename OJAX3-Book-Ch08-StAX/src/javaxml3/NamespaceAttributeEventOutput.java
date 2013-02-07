package javaxml3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Namespace;

public class NamespaceAttributeEventOutput {

    public static void main(String[] args) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEventWriter writer = outputFactory.createXMLEventWriter(System.out);

        Namespace ns1 = eventFactory.createNamespace("ns1",
                "http://www.example.com/ns1");
        Namespace ns2 = eventFactory.createNamespace("ns2",
                "http://www.example.com/ns2");
        List namespaceList = new ArrayList();
        namespaceList.add(ns1);
        namespaceList.add(ns2);

        Attribute attribute = eventFactory.createAttribute(ns2.getPrefix(), ns2
                .getNamespaceURI(), "attribute", "true");

        writer.add(eventFactory.createStartElement(ns1.getPrefix(), ns1
                .getNamespaceURI(), "sample", Collections.singletonList(
                attribute).iterator(), namespaceList.iterator()));
        writer.add(eventFactory.createEndDocument());
        writer.flush();
    }

}

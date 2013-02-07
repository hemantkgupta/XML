package javaxml3;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class AttributeStreamOutput {

    public static void main(String[] args) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(System.out);
        writer.writeStartDocument("1.0");
        writer.writeStartElement("addresses");
        writer.writeStartElement("address");
        writer.writeAttribute("type", "work");
        writer.writeStartElement("street");
        writer.writeCharacters("1515 Broadway");
        writer.writeComment("in the heart of Times Square");
        writer.writeEndElement();
        writer.writeEmptyElement("inner");
        writer.flush();
        System.out.println();
        writer.writeAttribute("otherAttribute", "true");
        writer.flush();
        System.out.println();
        writer.writeEndElement();
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
    }

}

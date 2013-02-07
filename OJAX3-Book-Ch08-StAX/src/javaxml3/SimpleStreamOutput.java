package javaxml3;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class SimpleStreamOutput {

    public static void main(String[] args) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(System.out);
        writer.writeStartDocument("1.0");
        writer.writeStartElement("person");
        writer.writeStartElement("name");
        writer.writeStartElement("first_name");
        writer.writeCharacters("Alan");
        /*writer.writeEndElement();
        writer.writeEndElement();
        writer.writeEndElement();*/
        writer.writeEndDocument();
        writer.flush();
    }

}

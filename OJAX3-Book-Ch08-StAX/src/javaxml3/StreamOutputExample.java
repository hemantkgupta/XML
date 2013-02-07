package javaxml3;

import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

public class StreamOutputExample {

    /**
     * @param args
     * @throws XMLStreamException 
     */
    public static void main(String[] args) throws XMLStreamException {
        System.out.println("starting");
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        outputFactory.setProperty(XMLOutputFactory.IS_REPAIRING_NAMESPACES, true);
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(System.out);
        writer.writeStartDocument("1.0");
        //writer.setPrefix("n1", "http://www.test.com/foo");
        writer.writeStartElement("http://www.test.com/foo", "sample");
        //writer.writeDefaultNamespace("http://default/");
        //writer.writeNamespace("n1", "http://www.test.com/foo");
        writer.writeAttribute("attribute", "true");
        writer.writeAttribute("http://www.test.com/foo", "attribute2", "false");
        writer.writeCharacters("some text");
        writer.writeCData("some more text <<blah>>");
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
        System.out.println();
        System.out.println("finishing");


    }

}

package javaxml3;

import java.io.FileInputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLResolver;
import javax.xml.stream.XMLStreamException;

public class InputObjectProperties {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();

        inputFactory.setXMLResolver(new XMLResolver() {

            public Object resolveEntity(String publicID, String systemID, String baseURI, String namespace) throws XMLStreamException {
                System.out.println(systemID);
                System.out.println("Re");
                return null;
            }});
        
        XMLEventReader reader = inputFactory
                .createXMLEventReader(new FileInputStream("sample/turing_partialdtd.xml"));
        XMLEventWriter writer = outputFactory.createXMLEventWriter(System.out);
        writer.add(reader);
        writer.flush();
        
    }

}

package javaxml3;

import java.io.FileInputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class EntityReferenceTest {

    public static void main(String[] args) throws Exception {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        inputFactory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
        inputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.TRUE);
        
        XMLStreamReader reader = inputFactory.createXMLStreamReader(new FileInputStream("sample/entity1.xml"));
        System.out.println(reader.getProperty(XMLInputFactory.IS_COALESCING));
        System.out.println(reader.getProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES));
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.CHARACTERS)
                System.out.println(reader.getText());
            else if (event == XMLStreamConstants.ENTITY_REFERENCE) {
                System.out.println("en: "+reader.getLocalName());
                System.out.println("er: "+reader.getText());
            }
        }

        System.out.println("---------------------");
        //XMLInputFactory inputFactory2 = XMLInputFactory.newInstance();        
        //inputFactory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.FALSE);
        inputFactory.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.FALSE);
        
        reader = inputFactory.createXMLStreamReader(new FileInputStream("sample/entity1.xml"));
        System.out.println(reader.getProperty(XMLInputFactory.IS_COALESCING));
        System.out.println(reader.getProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES));
        while (reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.CHARACTERS)
                System.out.println(reader.getText());
            else if (event == XMLStreamConstants.ENTITY_REFERENCE) {
                System.out.println("en: "+reader.getLocalName());
                System.out.println("er: "+reader.getText());
            }
        }
    }

}

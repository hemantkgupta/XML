package javaxml3;

import java.io.StringReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.XMLEventAllocator;
import javax.xml.stream.util.XMLEventConsumer;

import com.sun.xml.stream.events.XMLEventAllocatorImpl;

public class XMLEventAllocatorSample {

    /**
     * @param args
     * @throws XMLStreamException 
     * @throws IllegalArgumentException 
     */
    public static void main(String[] args) throws Exception {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = inputFactory.createXMLEventReader(new StringReader(""));
        XMLEventAllocator allocator = (XMLEventAllocator) reader.getProperty(XMLInputFactory.ALLOCATOR);
        System.out.println(allocator);

    }
    
    static class XEA extends XMLEventAllocatorImpl {

        public void allocate(XMLStreamReader reader, XMLEventConsumer consumer) throws XMLStreamException {
          //  if ("http://www.example.com/ns1".equals(reader.getNamespaceURI());
            super.allocate(reader, consumer);
        }

        public XMLEvent allocate(XMLStreamReader reader) throws XMLStreamException {
            // TODO Auto-generated method stub
            return super.allocate(reader);
        }


        
    }

}

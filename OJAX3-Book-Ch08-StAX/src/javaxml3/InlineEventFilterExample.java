package javaxml3;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class InlineEventFilterExample {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        File file = new File("sample/lola.xml");
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = inputFactory
                .createXMLEventReader(new FileInputStream(file));

        reader = inputFactory.createFilteredReader(reader, new EventFilter() {

            public boolean accept(XMLEvent event) {
                return false;
            }
        });
        reader = inputFactory.createFilteredReader(reader,
                new ElementOnlyFilter());

        System.out.println(reader.hasNext());
        System.out.println(reader.next());

    }

}

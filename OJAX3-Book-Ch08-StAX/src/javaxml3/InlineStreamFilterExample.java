package javaxml3;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class InlineStreamFilterExample {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        File file = new File("sample/lola.xml");
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory
                .createXMLStreamReader(new FileInputStream(file));
        
        /*reader = inputFactory.createFilteredReader(reader, new StreamFilter() {

            public boolean accept(XMLStreamReader reader) {
                return false;
            } });*/
        reader = inputFactory.createFilteredReader(reader, new ElementOnlyFilter());
        
        System.out.println(reader.getEventType());

    }

}

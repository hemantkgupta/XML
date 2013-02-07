package javaxml3;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StreamFilterExample {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java javaxml3.StreamFilterExample "
                    + "[XML Document]");
            return;
        }
        File file = new File(args[0]);

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory
                .createXMLStreamReader(new FileInputStream(file));

        int unfilteredCount = countEvents(reader);
        System.out.println("Unfiltered Count = " + unfilteredCount);

        // reinitialize the reader
        reader = inputFactory.createXMLStreamReader(new FileInputStream(file));

        // create the filter and filtered reader
        StreamFilter filter = new ElementOnlyFilter();
        reader = inputFactory.createFilteredReader(reader, filter);

        int filteredCount = countEvents(reader);
        System.out.println("Filtered Count = " + filteredCount);

        System.out.println("Filter removed "
                + (unfilteredCount - filteredCount) + " events");
    }

    private static int countEvents(XMLStreamReader reader)
            throws XMLStreamException {
        int counter = 1;
        while (reader.hasNext()) {
            reader.next();
            counter++;
        }
        return counter;

    }

}

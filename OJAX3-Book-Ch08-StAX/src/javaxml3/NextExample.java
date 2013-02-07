package javaxml3;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class NextExample {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java javaxml3.NextExample "
                    + "[XML Document]");
            return;
        }
        File file = new File(args[0]);

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory
                .createXMLStreamReader(new FileInputStream(file));

        int eventTypeID = reader.next();

        // skip past any initial whitespace
        while (reader.getEventType() == 6)
            reader.next();

        // the cursor is now at the person start element

        eventTypeID = reader.next();
        // the cursor is now at the whitespace between contact and name

        eventTypeID = reader.next();
        // the cursor is now at the name start element

        eventTypeID = reader.next();
        // the cursor is now at the whitespace between name and first_name

        eventTypeID = reader.next();
        // the cursor is now at the first_name start element

        eventTypeID = reader.next();
        // the cursor should now be at the text within the first_name element

        System.out.println("Hello " + reader.getText());
    }
}

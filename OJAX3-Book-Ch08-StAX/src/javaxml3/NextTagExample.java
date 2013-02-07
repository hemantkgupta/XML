package javaxml3;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class NextTagExample {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out
                    .println("Usage: java NextTagExample " + "[XML Document]");
            return;
        }
        File file = new File(args[0]);

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory
                .createXMLStreamReader(new FileInputStream(file));

        int eventTypeID = reader.nextTag();
        // the cursor is now at the person start element

        eventTypeID = reader.nextTag();
        // the cursor is now at the contact start element

        eventTypeID = reader.nextTag();
        // the cursor is now at the first_name start element

        eventTypeID = reader.next();
        // the cursor should now be at the text within the first_name elemnt
        System.out.println("Hello " + reader.getText());
    }
}

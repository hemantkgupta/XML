package javaxml3;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class RequireExample {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out
                    .println("Usage: java RequireExample " + "[XML Document]");
            return;
        }
        File file = new File(args[0]);

        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory
                .createXMLStreamReader(new FileInputStream(file));

        int eventTypeID = reader.nextTag();
        // the cursor is now at the person start element
        reader.require(XMLStreamConstants.START_ELEMENT, null, "person");

        eventTypeID = reader.nextTag();
        // the cursor is now at the name start element
        reader.require(XMLStreamConstants.START_ELEMENT, null, "name");

        eventTypeID = reader.nextTag();
        // the cursor is now at the first_name start element
        //reader.require(XMLStreamConstants.START_ELEMENT, null, "first_name");

        //eventTypeID = reader.next();
        // the cursor should now be at the text within the first_name elemnt
        //System.out.println("Hello " + reader.getText());
        boolean outputElementText = false;
        try {
            reader.require(XMLStreamConstants.START_ELEMENT, null, "first_name");
            outputElementText = true;
        } catch (XMLStreamException e) {
            System.out.println("Assertion failed. " + e.getMessage()
                    + " at " + reader.getLocation().getLineNumber() + ":"
                    + reader.getLocation().getColumnNumber());
        }

        if (outputElementText)
            System.out.println(reader.getElementText());

    }
}

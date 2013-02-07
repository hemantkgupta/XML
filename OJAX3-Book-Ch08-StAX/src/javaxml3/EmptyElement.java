package javaxml3;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

public class EmptyElement {

    public static void main(String[] args) throws Exception {
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = outputFactory.createXMLStreamWriter(System.out);
        writer.writeEmptyElement("empty");
        //writer.flush(); // write '<empty' to the console
        //System.out.println("\n");
        writer.writeAttribute("attribute", "true");
        //writer.flush(); // write ' attribute="true"' to the console
        //System.out.println("\n");
        writer.writeEndDocument();
        //writer.writeEndElement();
        writer.flush(); // write /> to the console
    }

}

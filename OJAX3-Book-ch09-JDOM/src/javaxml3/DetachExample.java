package javaxml3;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class DetachExample {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        SAXBuilder builder = new SAXBuilder();
        Document books = builder.build("books.xml");
        Document onebook = builder.build("onebook.xml");
        Element bookToAdd = onebook.getRootElement().getChild("book");
        //bookToAdd.detach();
        books.getRootElement().addContent(bookToAdd);
        
        new XMLOutputter(Format.getPrettyFormat()).output(books, System.out);
    }

}

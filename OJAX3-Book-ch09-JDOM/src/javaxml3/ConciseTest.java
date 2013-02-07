package javaxml3;

import java.io.IOException;

import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class ConciseTest {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Document doc = new Document(new Element("root").setAttribute("attribute",
                "value").addContent(new Element("inner")).addContent(
                new Comment("comment text")).addContent("some inline text")
                .addContent(new Element("inner2")));

        new XMLOutputter(Format.getPrettyFormat()).output(doc, System.out);

    }

}

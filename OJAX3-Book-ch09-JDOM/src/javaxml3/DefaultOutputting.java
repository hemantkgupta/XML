package javaxml3;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

public class DefaultOutputting {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Element rootElement = new Element("root");
        Document document = new Document(rootElement);
        for (int i = 0; i < 10000; i++) {
        rootElement.addContent(new Element("blah"));
        rootElement.addContent(new Element("blah"));
        rootElement.addContent(new Element("blah").setText("foo"));
        rootElement.addContent(new Element("blah"));
        }
        
        new XMLOutputter().output(document, System.out);
    }

}

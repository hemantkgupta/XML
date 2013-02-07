package javaxml3;

import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class ChildTextSample {
    
    public static void main(String[] args) throws Exception {
        Document doc = new SAXBuilder().build("books.xml");
        Element books = doc.getRootElement();
        
        Iterator it = books.getChildren().iterator();
        while (it.hasNext()) {
            Element book = (Element) it.next();
            System.out.println(book.getChildText("name") +
                " was published in " + book.getChildText("pubDate"));
        }


    }

}

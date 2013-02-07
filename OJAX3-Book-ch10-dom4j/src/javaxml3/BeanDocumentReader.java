package javaxml3;

import java.util.Collections;
import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.bean.BeanDocumentFactory;
import org.dom4j.io.SAXReader;

public class BeanDocumentReader {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader(BeanDocumentFactory.getInstance());
        Document doc = reader.read("beans.xml");
        Iterator it = doc.getRootElement().elementIterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            System.out.println(element.getData());
        }
        
        Element bookElement = doc.getRootElement().element("book");
        BookBean book = (BookBean) bookElement.getData();
        book.setTitle("unknown");
        System.out.println(bookElement.asXML());


    }

}

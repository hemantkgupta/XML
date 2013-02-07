package javaxml3;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;

public class SortingXPath {

    public static void main(String[] args) throws Exception {
        Document doc = new SAXReader().read(new File("books.xml"));

        XPath bookPath = DocumentHelper.createXPath("//book");
        XPath sortPath = DocumentHelper.createXPath("pubDate");

        List books = bookPath.selectNodes(doc, sortPath, true);

        for (Iterator it = books.iterator(); it.hasNext();) {
            Element book = (Element) it.next();
            System.out.println(book.getPath());
            System.out.println(book.getUniquePath());
            System.out.println(book.getPath(doc.getRootElement()));
            System.out.println(book.getUniquePath(doc.getRootElement()));
            System.out.println(book.elementText("title"));
        }
    }

}

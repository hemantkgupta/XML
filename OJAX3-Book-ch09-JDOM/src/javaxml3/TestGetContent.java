package javaxml3;

import java.util.Iterator;

import org.jdom.Content;
import org.jdom.Document;
import org.jdom.Text;
import org.jdom.input.SAXBuilder;

public class TestGetContent {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
       SAXBuilder builder = new SAXBuilder();
       Document doc = builder.build("mixed.xml");

       for (Iterator it = doc.getRootElement().getContent().iterator(); it.hasNext();) {
           Content content = (Content) it.next();
           if (content instanceof Text) {
               System.out.println("[Text: "+((Text)content).getTextNormalize());
           } else {
               System.out.println(content.toString());
           }
       }
    }

}

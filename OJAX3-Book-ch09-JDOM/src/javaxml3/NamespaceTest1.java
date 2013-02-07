package javaxml3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class NamespaceTest1 {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Namespace nsShort = Namespace.getNamespace("short");
        Namespace nsWeird = Namespace.getNamespace("e", "weird");
        
        Document doc = new Document();
        Element easy = new Element("easy", nsShort);
        doc.setRootElement(easy);
        Element b = new Element("b", nsShort);
        Element c = new Element("c", nsShort);
        easy.addContent(b);
        b.addContent(c);
        
        Element d = new Element("d", nsShort);
        Element f = new Element("f", nsWeird);
        f.addContent(new Element("g", nsShort));
        d.addContent(f);
        easy.addContent(d);
        
        try {
            new XMLOutputter(Format.getPrettyFormat()).output(doc, new FileOutputStream("short.xml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        SAXBuilder sb = new SAXBuilder();
        sb.setFeature("http://xml.org/sax/features/namespaces", false);
        sb.setFeature("http://xml.org/sax/features/namespace-prefixes", false);
        try {
        Document doc2 = sb.build(new File("short.xml"));
        new XMLOutputter(Format.getPrettyFormat()).output(doc2, System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        for (Iterator it = doc.getDescendants(); it.hasNext();) {
            Object o = it.next();
            if (o instanceof Element) {
                Element el = (Element) o;
                if ("short".equals(el.getNamespaceURI())) {
                    if ("easy".equals(el.getName())) {
                        el.setNamespace(Namespace.getNamespace("a", "ReallyLongNamespace"));
                    } 
                }
            }
        }
        
        try {
            new XMLOutputter(Format.getPrettyFormat()).output(doc, System.out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
*/
    }

}

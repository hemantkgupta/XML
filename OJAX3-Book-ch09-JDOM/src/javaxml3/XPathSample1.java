package javaxml3;

import java.io.File;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

public class XPathSample1 {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new File("tds.xml"));
        Attribute attr = (Attribute) XPath.selectSingleNode(doc, "/schedule/@name");
        System.out.println(attr.getValue());
        
        Element el = new Element("parent").addContent(new Element("child").setAttribute("name", "value"));
        System.out.println(XPath.selectSingleNode(new Attribute("foo", "bar"), "position()"));
        
        XPath path = XPath.newInstance("/schedule/@name");
        System.out.println(path.valueOf(doc));

    }

}

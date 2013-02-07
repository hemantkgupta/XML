package javaxml3;

import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class PrettyFormat {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader();
        Document doc = reader.read("tds.xml");
        
        StringWriter sw = new StringWriter();
        
        XMLWriter writer = new XMLWriter(sw, OutputFormat.createPrettyPrint());
        writer.write(doc);
        System.out.println(sw.toString());
    }

}

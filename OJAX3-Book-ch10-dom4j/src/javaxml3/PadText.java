package javaxml3;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class PadText {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");
        root.addText(" hello ");
        root.addElement("i").addText(" and ").addElement("b").addText(" foo ");
        root.addText(" goodbye ");
        
        OutputFormat format = OutputFormat.createCompactFormat();
        format.setPadText(true);
        XMLWriter writer = new XMLWriter(System.out, format);
        writer.write(doc);
        writer.flush();

    }

}

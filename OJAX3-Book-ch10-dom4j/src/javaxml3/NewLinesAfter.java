package javaxml3;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.HTMLWriter;
import org.dom4j.io.OutputFormat;

public class NewLinesAfter {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("root");
        
        for (int i = 0; i < 1000; i++) {
            root.addElement("element"+i).addText("foo");
        }
        
        OutputFormat format = new OutputFormat();
        
        format.setXHTML(true);
        format.setNewLineAfterNTags(30);
        HTMLWriter writer = new HTMLWriter(System.out, format);
        writer.write(doc);
        writer.flush();

    }

}

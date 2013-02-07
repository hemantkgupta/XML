package javaxml3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.JDOMFactory;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class ElementChanger {

    public void change(String inputFilename, String outputFilename)
        throws IOException, JDOMException {

        // Create builder and set up factory
        SAXBuilder builder = new SAXBuilder();
        JDOMFactory factory = new CustomJDOMFactory();
        builder.setFactory(factory);
        
        // Build document 
        Document doc = builder.build(inputFilename);

        // Output document
        XMLOutputter outputter = new XMLOutputter(); 
        outputter.output(doc, new FileWriter(new File(outputFilename)));
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: javaxml2.ElementChanger " +
                "[XML Input Filename] [XML Output Filename]");
            return;
        }

        try {
            ElementChanger changer = new ElementChanger();
            changer.change(args[0], args[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

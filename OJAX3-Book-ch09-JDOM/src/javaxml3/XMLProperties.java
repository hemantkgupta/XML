package javaxml3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.jdom.Attribute;
import org.jdom.Comment;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class XMLProperties extends Properties {

    public void load(Reader reader) 
    throws IOException {
    
    try { 
        // Load XML into JDOM Document
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(reader);
        
        // Turn into properties objects
        loadFromElements(doc.getRootElement().getChildren(), 
            new StringBuffer(""));
        
    } catch (JDOMException e) {
        throw new IOException(e.getMessage());
    }        
}

private void loadFromElements(List elements, StringBuffer baseName) {
    // Iterate through each element
    for (Iterator i = elements.iterator(); i.hasNext(); ) {
        Element current = (Element)i.next();
        String name = current.getName();
        String text = current.getTextNormalize();
        
        // Don't add "." if no baseName
        if (baseName.length() > 0) {
            baseName.append(".");
        }            
        baseName.append(name);
        
        // See if we have an element value
        if ((text == null) || (text.equals(""))) {
            // If no text, recurse on children
            loadFromElements(current.getChildren(),
                             baseName);
        } else {                
            // If text, this is a property
            setProperty(baseName.toString(), 
                        text);
        }            
        
        // On unwind from recursion, remove last name
        if (baseName.length() == name.length()) {
            baseName.setLength(0);
        } else {                
            baseName.setLength(baseName.length() - 
                (name.length() + 1));
        }            
    }        
}


    public void load(InputStream inputStream) throws IOException {

        load(new InputStreamReader(inputStream));
    }

    public void load(File xmlDocument) throws IOException {

        load(new FileReader(xmlDocument));
    }

    public void save(OutputStream out, String header) {
        try {
            store(out, header);
        } catch (IOException ignored) {
            // Deprecated version doesn't pass errors
        }
    }

    public void store(Writer writer, String header) throws IOException {

        // Create a new JDOM Document with a root element "properties"
        Document doc = new Document();
        Element root = new Element("properties");
        doc.setRootElement(root);
        
        // Add in header information
        Comment comment = new Comment(header);
        doc.getContent().add(0, comment);

        // Get the property names
        Enumeration propertyNames = propertyNames();
        while (propertyNames.hasMoreElements()) {
            String propertyName = (String) propertyNames.nextElement();
            String propertyValue = getProperty(propertyName);
            createXMLRepresentation(root, propertyName, propertyValue);
        }

        // Output document to supplied filename
        XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
        outputter.output(doc, writer);
    }

    private void createXMLRepresentation(Element root, String propertyName,
            String propertyValue) {

        int split;
        String name = propertyName;
        Element current = root;
        Element test = null;

        while ((split = name.indexOf(".")) != -1) {
            String subName = name.substring(0, split);
            name = name.substring(split + 1);

            // Check for existing element
            if ((test = current.getChild(subName)) == null) {
                Element subElement = new Element(subName);
                current.addContent(subElement);
                current = subElement;
            } else {
                current = test;
            }
        }

        // When out of loop, what's left is the final element's name
        Element last = new Element(name);
        last.setText(propertyValue);
        /** Uncomment this for Attribute usage */
        /*
         * last.setAttribute("value", propertyValue);
         */
        current.addContent(last);
    }

    public void store(OutputStream out, String header) throws IOException {

        store(new OutputStreamWriter(out), header);
    }

    public void store(File xmlDocument, String header) throws IOException {

        store(new FileWriter(xmlDocument), header);
    }
}

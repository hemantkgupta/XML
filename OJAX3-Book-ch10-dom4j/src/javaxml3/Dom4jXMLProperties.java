package javaxml3;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.Format;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class Dom4jXMLProperties extends Properties {
    
    private DocumentFactory factory;

    public Dom4jXMLProperties() {
        this(new DocumentFactory());
    }
    
    public Dom4jXMLProperties(Properties defaults) {
        this(new DocumentFactory(), defaults);
        }
    
    public Dom4jXMLProperties(DocumentFactory factory) {
        this(factory, null);
    }
    
    public Dom4jXMLProperties(DocumentFactory factory, Properties defaults) {
        super(defaults);
        this.factory= factory;        
    }

    public void load(Reader reader) throws IOException {
        try {
            // Load XML into dom4j Document
            SAXReader saxReader = new SAXReader(factory);
            Document doc = saxReader.read(reader);

            // Turn into properties objects
            loadFromElements(doc.getRootElement().elements(), new StringBuffer(
                    ""));
        } catch (DocumentException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void loadFromElements(List elements, StringBuffer baseName) {
        // Iterate through each element
        for (Iterator i = elements.iterator(); i.hasNext();) {
            Element current = (Element) i.next();
            String name = current.getName();
            String text = current.getTextTrim();

            // Don't add "." if no baseName
            if (baseName.length() > 0) {
                baseName.append(".");
            }
            baseName.append(name);

            // See if we have an element value
            if ((text == null) || (text.equals(""))) {
                // If no text, recurse on children
                loadFromElements(current.elements(), baseName);
            } else {
                // If text, this is a property
                setProperty(baseName.toString(), text);
            }

            // On unwind from recursion, remove last name
            if (baseName.length() == name.length()) {
                baseName.setLength(0);
            } else {
                baseName.setLength(baseName.length() - (name.length() + 1));
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
        Document doc = factory.createDocument();
        Element root = doc.addElement("properties");

        // Add in header information
        Comment comment = factory.createComment(header);
        doc.content().add(0, comment);

        // Get the property names
        Enumeration propertyNames = propertyNames();
        while (propertyNames.hasMoreElements()) {
            String propertyName = (String) propertyNames.nextElement();
            String propertyValue = getProperty(propertyName);
            createXMLRepresentation(root, propertyName, propertyValue);
        }

        // Output document to supplied filename
        XMLWriter xmlWriter = new XMLWriter(writer);
        xmlWriter.write(doc);
        xmlWriter.close();
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
            if ((test = current.element(subName)) == null) {
                Element subElement = current.addElement(subName);                
                current = subElement;
            } else {
                current = test;
            }
        }

        // When out of loop, what's left is the final element's name
        Element last = factory.createElement(name);
        last.setText(propertyValue);
        /** Uncomment this for Attribute usage */
        /*
         * last.setAttribute("value", propertyValue);
         */
        current.add(last);
    }

    public void store(OutputStream out, String header) throws IOException {

        store(new OutputStreamWriter(out), header);
    }

    public void store(File xmlDocument, String header) throws IOException {

        store(new FileWriter(xmlDocument), header);
    }
}

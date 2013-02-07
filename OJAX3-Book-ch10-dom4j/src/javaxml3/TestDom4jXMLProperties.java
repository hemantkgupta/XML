package javaxml3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Enumeration;

public class TestDom4jXMLProperties {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java javaxml3.TestDom4jXMLProperties " +
                "[XML input document] [XML output document]");
            System.exit(0);
        }
    
        try {
            // Create and load properties
            System.out.println("Reading XML properties from " + args[0]);
            Dom4jXMLProperties props = new Dom4jXMLProperties();
            props.load(new FileInputStream(args[0]));
            
            // Print out properties and values
            System.out.println("\n\n---- Property Values ----");
            Enumeration names = props.propertyNames();
            while (names.hasMoreElements()) {
                String name = (String)names.nextElement();
                String value = props.getProperty(name);
                System.out.println("Property Name: " + name + 
                                   " has value " + value);
            }            
            
            // Store properties
            System.out.println("\n\nWriting XML properies to " + args[1]);
            props.store(new FileOutputStream(args[1]),
                "Testing XMLProperties class");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

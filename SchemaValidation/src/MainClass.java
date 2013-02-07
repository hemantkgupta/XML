import java.io.StringReader;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class MainClass {
  public static void main(String args[])throws Exception {
    SAXParserFactory spf = SAXParserFactory.newInstance();
    SAXParser parser = null;
    spf.setNamespaceAware(true);
    try {
     SchemaFactory sf =
                     SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI); 
     spf.setSchema(sf.newSchema(new SAXSource(new InputSource(new StringReader(schemaString)))));

     parser = spf.newSAXParser();
    }
    catch(SAXException e) {
      e.printStackTrace(System.err);
      System.exit(1);    
    } 
    catch(ParserConfigurationException e) {
      e.printStackTrace(System.err);
      System.exit(1);    
    }
    MySAXHandler handler = new MySAXHandler(); 
    System.out.println(schemaString);
    parser.parse(new InputSource(new StringReader(xmlString)), handler);
  }
  static String xmlString = "<?xml version=\"1.0\"?>" +
      "<note>" +
      "<to>rtoName</to>" +
      "<from>FromName</from>" +
      "<heading>Info</heading>" +
      "<body>Message Body</body>" +
      "</note>";
  
  static String schemaString ="<?xml version=\"1.0\"?>" +
      "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"" +
      " targetNamespace=\"http://www.java2s.com\"" +
      " xmlns=\"http://www.java2s.com\"" +
      " elementFormDefault=\"qualified\">" +
      "<xs:element name=\"note\">" +
      "<xs:complexType>" +
      "<xs:sequence>" +
      "<xs:element name=\"to\" type=\"xs:string\"/>" +
      "<xs:element name=\"from\" type=\"xs:string\"/>" +
      "<xs:element name=\"heading\" type=\"xs:string\"/>" +
      "<xs:element name=\"body\" type=\"xs:string\"/>" +
      "</xs:sequence>" +
                      "</xs:complexType>" +
                      "</xs:element>" +
                      "</xs:schema>";
}

class MySAXHandler extends DefaultHandler {
  public void startDocument() {
    System.out.println("Start document: ");
  }    
    public void endDocument()  {
    System.out.println("End document: ");
  }
  
  public void startElement(String uri, String localName, String qname, 
                                                               Attributes attr)
  {
    System.out.println("Start element: local name: " + localName + " qname: " 
                                                        + qname + " uri: "+uri);
    int attrCount = attr.getLength();
    if(attrCount>0) {
      System.out.println("Attributes:"); 
      for(int i = 0 ; i<attrCount ; i++) {
        System.out.println("  Name : " + attr.getQName(i)); 
        System.out.println("  Type : " + attr.getType(i)); 
        System.out.println("  Value: " + attr.getValue(i)); 
      }
    } 
  }
  
  public void endElement(String uri, String localName, String qname) {
    System.out.println("End element: local name: " + localName + " qname: "
                                                         + qname + " uri: "+uri);
  }
  
  public void characters(char[] ch, int start, int length) {
    System.out.println("Characters: " + new String(ch, start, length));
  }

  public void ignorableWhitespace(char[] ch, int start, int length) {
    System.out.println("Ignorable whitespace: " + new String(ch, start, length));
  }

  public void startPrefixMapping(String prefix, String uri) {
    System.out.println("Start \"" + prefix + "\" namespace scope. URI: " + uri); 
  }

  public void endPrefixMapping(String prefix) {
    System.out.println("End \"" + prefix + "\" namespace scope."); 
  }

  public void warning(SAXParseException spe) {
    System.out.println("Warning at line "+spe.getLineNumber());
    System.out.println(spe.getMessage());
  }

  public void fatalError(SAXParseException spe) throws SAXException {
    System.out.println("Fatal error at line "+spe.getLineNumber());
    System.out.println(spe.getMessage());
    throw spe;
  }
}

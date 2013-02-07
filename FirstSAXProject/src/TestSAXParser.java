import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class TestSAXParser  extends DefaultHandler {
	

	boolean bfname = false;
    boolean blname = false;
    
	/**
	 * @param args
	 */	
	
	public static void main(String[] args)throws Exception {
		TestSAXParser ts = new TestSAXParser();
        ts.parseData();
	}
	
	
	public void parseData() throws ParserConfigurationException, SAXException{
						 
			     SAXParserFactory factory = SAXParserFactory.newInstance();
			     SAXParser saxParser = factory.newSAXParser();
			     try {
					saxParser.parse("test.xml",this);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					 System.out.println("Not read file");
					e.printStackTrace();
				}
			     
			    
			    	 
		}
		
	
		
   public void endElement(String uri, String localName,String qName)
		          throws SAXException {
		 
		          System.out.println("End Element :" + qName);
		 
 }
		 
   public void startElement(String uri, String localName,String qName, Attributes attributes)
	    	        throws SAXException {
	    	 
	    	        System.out.println("Start Element :" + qName);
	    	 
	    	        if (qName.equalsIgnoreCase("FIRSTNAME")) {
	    	           bfname = true;
	    	        }
	    	 
	    	        if (qName.equalsIgnoreCase("LASTNAME")) {
	    	           blname = true;
	    	        }
	 }
	    	        
 public void characters(char ch[], int start, int length)
	    	         throws SAXException {
	    	 
	    	         if (bfname) {
	    	            System.out.println("First Name : "
	    	                + new String(ch, start, length));
	    	            bfname = false;
	    	          }
	    	 
	    	          if (blname) {
	    	              System.out.println("Last Name : "
	    	                  + new String(ch, start, length));
	    	              blname = false;
	    	           }
 }
}

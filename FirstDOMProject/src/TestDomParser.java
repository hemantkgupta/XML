import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;


public class TestDomParser {
	
	public static void main(String[] args){
		TestDomParser t = new TestDomParser();
		t.parseData();
	}
   public void parseData(){
	   
	   DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try {
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse("test.xml");
		System.out.println("Factory Class: "+ factory.getClass().getName());
		      System.out.println("Builder Class: "+ builder.getClass().getName());
		System.out.println("Root Node Name: "+ doc.getChildNodes().item(0).getNodeName());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
   }
}

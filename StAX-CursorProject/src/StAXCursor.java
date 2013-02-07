import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;


public class StAXCursor {
	public static void main(String[] args)throws Exception {
		XMLInputFactory inputFactory=XMLInputFactory.newInstance();
		InputStream input=new FileInputStream(new File("test.xml"));
		XMLStreamReader xmlStreamReader = inputFactory.createXMLStreamReader(input);
		while (xmlStreamReader.hasNext()) {
			int event = xmlStreamReader.next();
			if (event == XMLStreamConstants.START_ELEMENT) {
				System.out.println("Element Local Name:"+ xmlStreamReader.getLocalName());
				}
			}
	
	}

}

package javaxml3;

import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class FISTest {

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception{
        XMLInputFactory factory = XMLInputFactory.newInstance();
        FileInputStream fis = new FileInputStream("sample/contact1.xml");
        XMLStreamReader reader = factory.createXMLStreamReader(fis);
        reader.close();
        
        fis.close();

    }

}

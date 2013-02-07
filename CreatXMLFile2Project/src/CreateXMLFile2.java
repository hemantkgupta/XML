import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
 
public class CreateXMLFile2 {
  public static void main(String[] args) throws Exception {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    System.out.print("Enter number to add elements in your XML file: ");
    String str = bf.readLine();
    int no = Integer.parseInt(str);
    System.out.print("Enter root: ");
    String root = bf.readLine();
    DocumentBuilderFactory documentBuilderFactory =DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder =documentBuilderFactory.newDocumentBuilder();
    // In place of parse method newDocument() is used to create xml file
    Document document = documentBuilder.newDocument();
    Element rootElement = document.createElement(root);
        document.appendChild(rootElement);
    for (int i = 1; i <= no; i++){
      System.out.print("Enter the element: ");
      String element = bf.readLine();
      System.out.print("Enter the data: ");
      String data = bf.readLine();
      Element em = document.createElement(element);
      em.appendChild(document.createTextNode(data));
      rootElement.appendChild(em);
    }
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        // Use System.out to print on console inplace of file
        StreamResult result =  new StreamResult(new File("result.xml"));
        transformer.transform(source, result);
  }
}

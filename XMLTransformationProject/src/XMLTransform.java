import java.io.File;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XMLTransform {

  public static void main(String args[]) {

//    if (args.length != 2) {
//      System.err.println("Usage: java XMLTransform xmlfile.xml stylesheet.xsl");
//      System.exit(-1);
//    }

    try {
      StreamSource source = new StreamSource("phonebook.xml");
      StreamSource stylesource = new StreamSource("directory.xsl");

      TransformerFactory factory = TransformerFactory.newInstance();
      Transformer transformer = factory.newTransformer(stylesource);

     // StreamResult result = new StreamResult(System.out);
      StreamResult result = new StreamResult(new File("result.html"));
      transformer.transform(source, result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

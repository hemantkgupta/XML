import java.io.FileReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class XMLStreamReaderDemo {
  public static void main(String args[]) throws Exception {
    XMLInputFactory xmlif = XMLInputFactory.newInstance();
    XMLStreamReader xmlsr = xmlif.createXMLStreamReader(new FileReader("points.xml"));
    int eventType;
    while (xmlsr.hasNext()) {
      eventType = xmlsr.next();
      switch (eventType) {
      case XMLEvent.START_ELEMENT:
        System.out.println(xmlsr.getName());
        break;
      case XMLEvent.CHARACTERS:
        System.out.println(xmlsr.getText());
        break;
      default:
        break;
      }
    }
  }
}

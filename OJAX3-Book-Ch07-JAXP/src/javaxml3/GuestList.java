package javaxml3;

import java.io.FileReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GuestList {

    public static void main(String[] args) throws Exception {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();

        NodeList shows = (NodeList) xPath.evaluate("/schedule/show",
                new InputSource(new FileReader("tds.xml")),
                XPathConstants.NODESET);
        System.out.println("Document has " + shows.getLength() + " shows.");
        for (int i = 0; i < shows.getLength(); i++) {
            Element show = (Element) shows.item(i);
            String guestName = xPath.evaluate("guest/name", show);
            String guestCredit = xPath.evaluate("guest/credit", show);

            System.out.println(show.getAttribute("weekday") + ", "
                    + show.getAttribute("date") + " - " + guestName + " ("
                    + guestCredit + ")");
        }

    }

}

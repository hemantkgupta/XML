package javaxml3;

import java.io.FileReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GuestListNS {

    public static void main(String[] args) throws Exception {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        SimpleNamespaceContext nsContext = new SimpleNamespaceContext();
        xPath.setNamespaceContext(nsContext);
        nsContext.addNamespace("s", "uri:comedy:schedule");
        nsContext.addNamespace("g", "uri:comedy:guest");

        NodeList shows = (NodeList) xPath.evaluate("/s:schedule/s:show",
                new InputSource(new FileReader("tds_ns.xml")),
                XPathConstants.NODESET);
        System.out.println("Document has " + shows.getLength() + " shows.");
        for (int i = 0; i < shows.getLength(); i++) {
            Element show = (Element) shows.item(i);
            String guestName = xPath.evaluate("g:guest/g:name", show);
            String guestCredit = xPath.evaluate("g:guest/g:credit", show);

            System.out.println(show.getAttribute("weekday") + ", "
                    + show.getAttribute("date") + " - " + guestName + " ("
                    + guestCredit + ")");
        }
    }

}

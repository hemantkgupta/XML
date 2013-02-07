package javaxml3;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class GuestManager {
    private Document document;

    private XPathExpression expression;

    private MapVariableResolver resolver = new MapVariableResolver();

    private SimpleDateFormat xmlDateFormat = new SimpleDateFormat("MM.dd.yy");

    public GuestManager(String fileName) throws ParserConfigurationException,
            SAXException, IOException, XPathExpressionException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        document = builder.parse(new File(fileName));

        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        xPath.setXPathVariableResolver(resolver);
        expression = xPath.compile("/schedule/show[@date=$date]/guest");
    }

    public synchronized Element getGuest(Date guestDate)
            throws XPathExpressionException {
        String formattedDate = xmlDateFormat.format(guestDate);
        resolver.addVariable(null, "date", formattedDate);
        return (Element) expression.evaluate(document, XPathConstants.NODE);
    }

    public static void main(String[] args) throws Exception {
        GuestManager gm = new GuestManager("tds.xml");
        Element guest = gm.getGuest(new Date(2006, 5, 14));
        System.out.println(guest.getElementsByTagName("name").item(0)
                .getTextContent());
    }

}

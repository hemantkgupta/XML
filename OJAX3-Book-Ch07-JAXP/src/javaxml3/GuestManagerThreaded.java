package javaxml3;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

public class GuestManagerThreaded {

    private Document document;

    private XPathExpression expression;

    private MapVariableResolver resolver = new MapVariableResolver();

    public GuestManagerThreaded(String fileName)
            throws ParserConfigurationException, SAXException, IOException,
            XPathExpressionException {
        resolver = new MapVariableResolver();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        document = builder.parse(new File(fileName));

        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        xPath.setXPathVariableResolver(resolver);
        expression = xPath.compile("/schedule/show[@date=$date]/guest");
    }

    public Element getGuest(Date guestDate) throws XPathExpressionException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yy");
        String formattedDate = dateFormat.format(guestDate);
        //synchronized (resolver) {
            resolver.addVariable(null, "date", formattedDate);
            return (Element) expression.evaluate(document, XPathConstants.NODE);
        //}
    }

    public static void main(String[] args) throws Exception {
        final GuestManagerThreaded gm = new GuestManagerThreaded("tds.xml");
        final Random random = new Random();
        class Call implements Callable {

            int id = 0;

            Call(int id) {
                this.id = id;
            }

            public Object call() {
                Date dt = new Date(2006, 5, random.nextInt(4) + 12);
                Element guest = null;
                try {
                    guest = gm.getGuest(dt);
                    System.out.println(id
                            + ". "
                            + Thread.currentThread().getName()
                            + ": "
                            + dt
                            + ": "
                            + guest.getElementsByTagName("name").item(0)
                                    .getTextContent());
                } catch (XPathExpressionException e) {
                    e.printStackTrace();
                }
                return null;
            }

        }
        ;
        ExecutorService executor = Executors.newFixedThreadPool(20);
        List l = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            l.add(new Call(i));
        }
        executor.invokeAll(l);
    }

}

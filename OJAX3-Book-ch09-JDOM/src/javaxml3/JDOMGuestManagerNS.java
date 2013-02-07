package javaxml3;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

public class JDOMGuestManagerNS {

    private static Namespace NS_GUEST = Namespace.getNamespace("g",
            "uri:comedy:guest");

    private static Namespace NS_SCHEDULE = Namespace.getNamespace("s",
            "uri:comedy:schedule");

    private Document document;

    private SimpleDateFormat xmlDateFormat = new SimpleDateFormat("MM.dd.yy");

    private XPath xPath;

    public JDOMGuestManagerNS(String fileName) throws JDOMException,
            IOException {
        SAXBuilder builder = new SAXBuilder();
        document = builder.build(new File(fileName));

        xPath = XPath.newInstance("/s:schedule/s:show[@date=$date]/g:guest");
        xPath.addNamespace(NS_SCHEDULE);
        xPath.addNamespace(NS_GUEST);
    }

    public synchronized Element getGuest(Date guestDate) throws JDOMException {
        String formattedDate = xmlDateFormat.format(guestDate);
        xPath.setVariable("date", formattedDate);
        return (Element) xPath.selectSingleNode(document);
    }

    public static void main(String[] args) throws Exception {
        JDOMGuestManagerNS gm = new JDOMGuestManagerNS("tds_ns.xml");
        Element guest = gm.getGuest(new Date(2006, 5, 14));
        System.out.println(guest.getChildText("name", NS_GUEST));
    }

}

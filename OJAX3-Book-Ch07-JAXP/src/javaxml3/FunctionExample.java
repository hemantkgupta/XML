package javaxml3;

import java.io.FileReader;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFunction;
import javax.xml.xpath.XPathFunctionException;
import javax.xml.xpath.XPathFunctionResolver;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class FunctionExample {

    public static void main(String[] args) throws Exception {
        XPathFactory factory = XPathFactory.newInstance();
        XPath xPath = factory.newXPath();
        SimpleNamespaceContext nsContext = new SimpleNamespaceContext();
        xPath.setNamespaceContext(nsContext);
        nsContext.addNamespace("s", "uri:comedy:schedule");
        nsContext.addNamespace("g", "uri:comedy:guest");
        xPath.setXPathFunctionResolver(new SampleFunctionResolver());

        NodeList shows = (NodeList) xPath.evaluate("/s:schedule/s:show",
                new InputSource(new FileReader("tds_ns.xml")),
                XPathConstants.NODESET);
        for (int i = 0; i < shows.getLength(); i++) {
            Element show = (Element) shows.item(i);

            String guestJoke = xPath.evaluate("g:joke(g:guest)", show);
            System.out
                    .println(show.getAttribute("weekday") + " - " + guestJoke);
        }
    }

}

class SampleFunction implements XPathFunction {

    public Object evaluate(List args) throws XPathFunctionException {
        if (args.size() != 1)
            throw new XPathFunctionException("I need exactly one arguments");

        // args is a single guest node
        NodeList guestNodes = (NodeList) args.get(0);
        Element guest = (Element) guestNodes.item(0);
        NodeList nameNodes = guest.getElementsByTagNameNS("uri:comedy:guest",
                "name");
        NodeList creditNodes = guest.getElementsByTagNameNS("uri:comedy:guest",
                "credit");

        return evaluate(nameNodes, creditNodes);

    }

    private String evaluate(NodeList nameNodes, NodeList creditNodes) {
        return new String("I hope " + nameNodes.item(0).getTextContent()
                + " makes a good joke about being "
                + creditNodes.item(0).getTextContent());
    }

}

class SampleFunctionResolver implements XPathFunctionResolver {

    public XPathFunction resolveFunction(QName functionName, int arity) {
        if ("uri:comedy:guest".equals(functionName.getNamespaceURI())
                && "joke".equals(functionName.getLocalPart()) && (arity == 1)) {
            return new SampleFunction();
        } else
            return null;
    }

}

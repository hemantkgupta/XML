package javaxml3;

import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Text;
import org.jdom.contrib.helpers.XPathHelper;

public class XPathDisplayNode {

    /** The JDOMNode this xpath is based on */
    JDOMNode node;

    public XPathDisplayNode(JDOMNode node) {
        this.node = node;
    }

    public String getXPath() throws JDOMException {
        // Handle elements
        if (node.getNode() instanceof Element) {
            return XPathHelper.getPathString((Element) node.getNode());
        }

        // Handle attributes
        if (node.getNode() instanceof Attribute) {
            return XPathHelper.getPathString((Attribute) node.getNode());
        }

        // Handle text
        if (node.getNode() instanceof Text) {
            return XPathHelper.getPathString((Text) node.getNode());
        }

        // Other node types could follow here
        return "Node type not supported yet.";

    }
}

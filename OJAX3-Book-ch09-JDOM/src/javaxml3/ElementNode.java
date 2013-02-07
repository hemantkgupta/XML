package javaxml3;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

// JDOM imports
import org.jdom.Element;

public class ElementNode implements JDOMNode {

    /** the decorated Element */
    protected Element decorated;

    public ElementNode(Element element) {
        this.decorated = element;
    }

    public Object getNode() {
        return decorated;
    }

    public String getNodeName() {
        if (decorated != null) {
            return decorated.getName();
        }
        return "";
    }

    public JDOMNode getParentNode() {
        if (decorated.getParent() != null
                && decorated.getParent() instanceof Element) {
            return new ElementNode((Element) decorated.getParent());
        }
        return null;
    }

    public String getQName() {
        if (decorated.getNamespacePrefix().equals("")) {
            return decorated.getName();
        } else {
            return new StringBuffer(decorated.getNamespacePrefix()).append(":")
                    .append(decorated.getName()).toString();
        }
    }

    public Iterator iterator() {
        List list = decorated.getAttributes();
        ArrayList content = new ArrayList(list);

        // put the element's content in the list in order
        Iterator i = decorated.getContent().iterator();
        while (i.hasNext()) {
            content.add(i.next());
        }
        return content.iterator();
    }

    public String toString() {
        return decorated.toString();
    }
}

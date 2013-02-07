package javaxml3;

import java.util.Iterator;
import java.util.Collections;

// JDOM imports
import org.jdom.Attribute;

public class AttributeNode implements JDOMNode {

    /** The decorated attribute */
    protected Attribute decorated;

    public AttributeNode(Attribute attribute) {
        this.decorated = attribute;
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
        if (decorated.getParent() != null) {
            return new ElementNode(decorated.getParent());
        }
        return null;
    }

    public String getQName() {
        if (decorated.getNamespacePrefix().equals("")) { 
            return decorated.getName();
        } else {
            return new StringBuffer(decorated.getNamespacePrefix())
                           .append(":")
                           .append(decorated.getName()).toString();
        }
    }

    public Iterator iterator() {
        return Collections.EMPTY_LIST.iterator();
    }

    public String toString() {
        return decorated.toString();
    }
}

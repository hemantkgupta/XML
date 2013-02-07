package javaxml3;

import java.util.Collections;
import java.util.Iterator;

// JDOM imports
import org.jdom.Element;
import org.jdom.Text;

public class TextNode implements JDOMNode {

    /** The decorated Text */
    protected Text decorated;

    /** The manually set parent of this string content */
    private Element parent = null;
    
    public TextNode(Text text) {
        decorated = text;
    }

    public Object getNode() {
        return decorated;
    }

    public String getNodeName() {
        return "";
    }

    public JDOMNode getParentNode() {
        if (parent == null) {
            throw new RuntimeException(
                    "The parent of this String content has not been set!");
        }
        return new ElementNode(parent);
    }

    public String getQName() {
        // text nodes have no name
        return "";
    }

    public Iterator iterator() {
        return Collections.EMPTY_LIST.iterator();
    }

    public TextNode setParent(Element parent) {
        this.parent = parent;
        return this;
    }

    public String toString() {
        return decorated.getTextNormalize();
    }
}

package javaxml3;

import java.util.Iterator;

public interface JDOMNode {

    public Object getNode();

    public String getNodeName();

    public JDOMNode getParentNode();

    public String getQName();

    public Iterator iterator();

    public String toString();
}

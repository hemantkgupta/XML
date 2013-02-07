package javaxml3;

import org.jdom.DefaultJDOMFactory;
import org.jdom.Element;
import org.jdom.JDOMFactory;
import org.jdom.Namespace;

class CustomJDOMFactory extends DefaultJDOMFactory implements JDOMFactory {

    public Element element(String name) {
        return new ORAElement(name);
    }

    public Element element(String name, Namespace ns) {
        return new ORAElement(name, ns);
    }

    public Element element(String name, String uri) {
        return new ORAElement(name, uri);
    }

    public Element element(String name, String prefix, String uri) {
        return new ORAElement(name, prefix, uri);
    }
}

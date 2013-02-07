package javaxml3;

import org.jdom.Element;
import org.jdom.Namespace;

public class ORAElement extends Element {

    private static final Namespace ORA_NAMESPACE =
        Namespace.getNamespace("ora", "http://www.oreilly.com");

    public ORAElement(String name) {
        super(name, ORA_NAMESPACE);
    }

    public ORAElement(String name, Namespace ns) {
        super(name, ORA_NAMESPACE);
    }

    public ORAElement(String name, String uri) {
        super(name, ORA_NAMESPACE);
    }

    public ORAElement(String name, String prefix, String uri) {
        super(name, ORA_NAMESPACE);
    }
}

package javaxml3;

import java.util.HashMap;
import java.util.Hashtable;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPathVariableResolver;

public class MapVariableResolver implements XPathVariableResolver {

    private Hashtable variables = new Hashtable();

    public void addVariable(String namespaceURI, String localName, Object value) {
       addVariable(new QName(namespaceURI, localName), value);
    }
    
    public void addVariable(QName name, Object value) {
        variables.put(name, value);
    }

    public Object resolveVariable(QName name) {
        Object retval = variables.get(name);
        return retval;
    }
}
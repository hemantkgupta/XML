package javaxml3;

import org.jdom.Element;
import org.jdom.filter.Filter;

public class ElementOnlyFilter implements Filter {

    public boolean matches(Object obj) {
        return obj instanceof Element;
    }

}

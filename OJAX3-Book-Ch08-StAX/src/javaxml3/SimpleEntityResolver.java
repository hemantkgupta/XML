package javaxml3;

import java.io.IOException;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SimpleEntityResolver implements EntityResolver {

    private static final String USAGE_TERMS_ID = "http://www.newInstance.com/entities/usage-terms.xml";

    private static final String USAGE_TERMS_LOCAL_URI = "C:\\JavaAndXML3rdEdition\\workspace\\ch04\\usage-terms.xml";

    public InputSource resolveEntity(String publicID, String systemID)
            throws IOException, SAXException {

        if (systemID.equals(USAGE_TERMS_ID)) {
            return new InputSource(USAGE_TERMS_LOCAL_URI);
        }

        // In the default case, return null
        return null;
    }

}

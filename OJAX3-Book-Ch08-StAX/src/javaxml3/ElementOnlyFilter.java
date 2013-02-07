package javaxml3;

import javax.xml.stream.EventFilter;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

public class ElementOnlyFilter implements EventFilter, StreamFilter {

	/* implementation of EventFilter interface */
	public boolean accept(XMLEvent event) {
		return accept(event.getEventType());
	}

	/* implementation of StreamFilter interface */
	public boolean accept(XMLStreamReader reader) {
		return accept(reader.getEventType());
	}

    /* internal utility method */
    private boolean accept(int eventType) {
        return eventType == XMLStreamConstants.START_ELEMENT
                || eventType == XMLStreamConstants.END_ELEMENT;
    }

}

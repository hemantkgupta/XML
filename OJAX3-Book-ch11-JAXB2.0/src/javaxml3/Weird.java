package javaxml3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Weird {
    
    private String value;
    
    @XmlAttribute(name="value")
    //@XmlElement(name="value2")
    private String svalue;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @param args
     * @throws JAXBException 
     */
    public static void main(String[] args) throws JAXBException {
        Weird w = new  Weird();
        w.value="foo";
        w.svalue="bar";
        JAXBContext context = JAXBContext.newInstance(Weird.class);
        context.createMarshaller().marshal(w, System.out);

    }

}

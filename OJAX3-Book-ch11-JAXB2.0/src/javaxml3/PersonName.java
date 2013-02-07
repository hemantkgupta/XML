package javaxml3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class PersonName {
    
    @XmlValue
    private String value;

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
   /* public static void main(String[] args) throws JAXBException {
       PersonName pn = new PersonName();
       pn.value="foo";
       JAXBContext context = JAXBContext.newInstance(PersonName.class);
       context.createMarshaller().marshal(pn, System.out);

    }*/

}

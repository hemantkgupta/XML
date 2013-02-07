package javaxml3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

// @XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "person", namespace="http://www.example.com/myperson")
public class MyPerson {
    
    private String first;
    private String last;
    

    /**
     * @param args
     * @throws JAXBException 
     */
    public static void main(String[] args) throws JAXBException {
        MyPerson p = new MyPerson();
        p.first = "lola";
        p.last = "hires";
        
        JAXBContext context = JAXBContext.newInstance(MyPerson.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(p, System.out);
    }


    public String getFirst() {
        return first;
    }


    public void setFirst(String first) {
        this.first = first;
    }


    public String getLast() {
        return last;
    }


    public void setLast(String last) {
        this.last = last;
    }

}

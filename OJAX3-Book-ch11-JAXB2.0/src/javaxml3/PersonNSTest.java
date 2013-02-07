package javaxml3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.example.personns.ObjectFactory;
import com.example.personns.Person;

public class PersonNSTest {

    /**
     * @param args
     * @throws JAXBException 
     */
    public static void main(String[] args) throws JAXBException {
        ObjectFactory factory = new ObjectFactory();
        
        Person person = new Person();
        person.setFirstName("Lola");
        person.setLastName("Hires");
        
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(person, System.out);

    }

}

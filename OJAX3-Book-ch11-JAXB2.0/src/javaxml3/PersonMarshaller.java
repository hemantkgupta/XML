package javaxml3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.example.person.ObjectFactory;
import com.example.person.Person;

public class PersonMarshaller {

    public static void main(String[] args) throws JAXBException {
        ObjectFactory factory = new ObjectFactory();
        Person person = factory.createPerson();
        person.setFirstName("Alan");
        person.setLastName("Turing");

        JAXBContext context = JAXBContext.newInstance("com.example.person");
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(person, System.out);
    }

}

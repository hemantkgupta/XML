package javaxml3;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.example.person.Person;

public class PersonUnmarshaller {

    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.example.person");
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Person person = (Person) unmarshaller.unmarshal(new File("person.xml"));
        System.out.println(person.getFirstName());

    }

}

package javaxml3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import com.example.person.Person;

public class MisspelledPersonUnmarshaller {

    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("com.example.person");
        Unmarshaller unmarshaller = context.createUnmarshaller();

        JAXBElement<Person> element = unmarshaller.unmarshal(new StreamSource(
                "james_misspelled.xml"), Person.class);
        Person person = element.getValue();
        System.out.println(person.getFirstName());

    }
}

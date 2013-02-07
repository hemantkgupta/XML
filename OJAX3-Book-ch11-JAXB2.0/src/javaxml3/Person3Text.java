package javaxml3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.example.person3.NamePart;
import com.example.person3.Person;

public class Person3Text {

    /**
     * @param args
     * @throws JAXBException 
     */
    public static void main(String[] args) throws JAXBException {
        Person person = new Person();
        person.setFirstName(new NamePart("Lola"));
        person.setLastName(new NamePart("Hires"));
        
        JAXBContext context = JAXBContext.newInstance(Person.class);
        context.createMarshaller().marshal(person, System.out);
    }

}

package javaxml3;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.example.person.Person;

public class MarshalValidation {

    public static void main(String[] args) throws Exception {
        // create an incomplete Person object
        Person p = new Person();
        p.setFirstName("Burt");
        p.setLastName("Hires");

        // create our marshaller
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();

        // create a schema
        SchemaFactory sf = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(new File("person.xsd"));

        // assign the schema to the marshaller
        marshaller.setSchema(schema);
        marshaller.setEventHandler(new ValidationEventHandler() {

            public boolean handleEvent(ValidationEvent event) {
                System.out.println(event);
                return false;
            }});

        // this will throw a javax.xml.bind.MarshalException
        marshaller.marshal(p, System.out);
    }

}

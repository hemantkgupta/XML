package javaxml3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.example.person.ObjectFactory;
import com.example.person.Person;

public class DOMMarshaller {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document doc = builder.newDocument();
        Element people = doc.createElement("people");
        doc.appendChild(people);

        ObjectFactory factory = new ObjectFactory();
        Person person = factory.createPerson();
        person.setFirstName("Alan");
        person.setLastName("Turing");

        JAXBContext context = JAXBContext.newInstance("com.example.person");
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(person, people);

        TransformerFactory tf = TransformerFactory.newInstance();
        tf.newTransformer().transform(new DOMSource(doc),
                new StreamResult(System.out));

    }

}

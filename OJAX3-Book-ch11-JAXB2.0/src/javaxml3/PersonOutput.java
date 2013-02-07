package javaxml3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class PersonOutput {

    /**
     * @param args
     * @throws JAXBException 
     */
    public static void main(String[] args) throws JAXBException {
        Person p = new Person();
        p.setFirstName("Burt");
        p.setLastName("Hires");
        PersonName pn = new PersonName();
        pn.setValue("Lola");
        p.setFriend(pn);
        JAXBContext context = JAXBContext.newInstance(Person.class);
        context.createMarshaller().marshal(p, System.out);

    }

}

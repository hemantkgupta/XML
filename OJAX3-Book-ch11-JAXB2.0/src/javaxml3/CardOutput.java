package javaxml3;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class CardOutput {

    /**
     * @param args
     * @throws JAXBException 
     */
    public static void main(String[] args) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Card.class);
        context.createMarshaller().marshal(Card.CLUBS, System.out);
    }

}

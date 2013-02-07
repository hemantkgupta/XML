package javaxml3;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement()
@XmlType(name = "")
// @XmlAccessorType(XmlAccessType.FIELD)
public class Person {
    
    //@XmlAttribute()
    private String firstName;
    
    private PersonName friend;    
    
    private String lastName;

    public String getFirstName() {
        return firstName;
    }
    
    @XmlElement(nillable=true)
    //@XmlElement(required=true)
    public PersonName getFriend() {
        return friend;
    }
    
    //@XmlValue()
   // @XmlTransient
    public String getLastName() {
        return lastName;
    }
    
    public void setFirstName(String s) {
        firstName = s;
    }

    public void setFriend(PersonName friend) {
        this.friend = friend;
    }
    
   //r @XmlTransient
    public void setLastName(String s) {
        lastName = s;
    }

}

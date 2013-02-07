@javax.xml.bind.annotation.XmlSchema(
    namespace = "http://www.example.com/person",
    elementFormDefault = javax.xml.bind.annotation.XmlNsForm.UNQUALIFIED,
    xmlns = { 
        @javax.xml.bind.annotation.XmlNs(prefix = "p",
            namespaceURI="http://www.example.com/person")
    }
)
package javaxml3;

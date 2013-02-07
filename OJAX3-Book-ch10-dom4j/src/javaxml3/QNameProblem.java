package javaxml3;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class QNameProblem {

    public static void main(String[] args) {
       Namespace ns1 = Namespace.get("ns1", "http://www.example.com/ns1");
       Element root = DocumentHelper.createElement(new QName("test", ns1, "foo:test"));
       System.out.println(root.getNamespacePrefix());
       System.out.println(root.getQualifiedName());
       System.out.println(root.asXML());

       root = DocumentHelper.createElement(QName.get("test", ns1, "foo:test"));
       System.out.println(root.asXML());

    }

}

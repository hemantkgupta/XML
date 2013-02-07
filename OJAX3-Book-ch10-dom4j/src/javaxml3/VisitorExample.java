package javaxml3;

import java.util.ListIterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.Visitor;
import org.dom4j.VisitorSupport;
import org.dom4j.io.SAXReader;

public class VisitorExample {

    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
            System.err
                    .println("Usage: javaxml3.VisitorExample [doc] [old ns] [new prefix] [new ns]");
            System.exit(0);
        }

        Document doc = new SAXReader().read(args[0]);
        Namespace oldNs = Namespace.get(args[1]);
        Namespace newNs;
        if (args[2].equals("-")) {
            newNs = Namespace.get(args[3]);
        } else {
            newNs = Namespace.get(args[2], args[3]);
        }
        Visitor visitor = new NamesapceChangingVisitor(oldNs, newNs);
        doc.accept(visitor);
        System.out.println(doc.asXML());
    }

}

class NamesapceChangingVisitor extends VisitorSupport {

    private Namespace from;

    private Namespace to;

    public NamesapceChangingVisitor(Namespace from, Namespace to) {
        this.from = from;
        this.to = to;
    }

    public void visit(Element node) {
        Namespace ns = node.getNamespace();

        if (ns.getURI().equals(from.getURI())) {
            QName newQName = new QName(node.getName(), to);
            node.setQName(newQName);
        }
        
        // we also need to remove the namespace declaration
        ListIterator namespaces = node.additionalNamespaces().listIterator();
        while (namespaces.hasNext()) {
            Namespace additionalNamespace = (Namespace) namespaces.next();
            if (additionalNamespace.getURI().equals(from.getURI())) {
                namespaces.remove();
            }
        }
    }

}

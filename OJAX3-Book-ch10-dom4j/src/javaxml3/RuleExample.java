package javaxml3;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.dom4j.rule.Action;
import org.dom4j.rule.Rule;
import org.dom4j.rule.Stylesheet;

public class RuleExample {

    class BookAction implements Action {

        public void run(Node node) throws Exception {
            if (node instanceof Element) {
                Element element = (Element) node;
                Element newElement = element.createCopy();
                // make pubDate an attribute
                Element pubDateElement = newElement.element("pubDate");

                // remove the pubDate element from the current node
                newElement.remove(pubDateElement);
                Attribute attr = DocumentHelper.createAttribute(newElement,
                        "pubDate", pubDateElement.getTextTrim());
                newElement.add(attr);

                // add our new element to the result document's root element
                rootElement.add(newElement);
            }
        }

    }

    private Element rootElement;

    public Document transform(Document input) throws DocumentException {

        // must be final because we're using it in an inner class
        final Document result = DocumentHelper.createDocument();

        final Stylesheet style = new Stylesheet();

        Rule booksRule = new Rule(DocumentHelper.createPattern("books"),
                new Action() {
                    public void run(Node node) throws Exception {
                        rootElement = result.addElement("books");
                        style.applyTemplates(node);
                    }
                });

        Rule bookRule = new Rule(DocumentHelper.createPattern("book"),
                new BookAction());
        style.addRule(booksRule);
        style.addRule(bookRule);

        try {
            style.run(input);
        } catch (Exception e) {
            System.err.println("Unable to transform: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java javaxml3.RuleExample "
                    + "[XML input document]");
            System.exit(0);
        }
        Document doc = new SAXReader().read(args[0]);

        RuleExample example = new RuleExample();
        Document result = example.transform(doc);
        // new XMLWriter(System.out,
        // OutputFormat.createPrettyPrint()).write(result);
        System.out.println(result.asXML());
    }

}

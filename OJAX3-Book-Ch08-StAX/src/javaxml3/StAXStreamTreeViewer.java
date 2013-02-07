package javaxml3;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StAXStreamTreeViewer extends JFrame {
    /** The base tree to render */
    private JTree jTree;

    /** Tree model to use */
    DefaultTreeModel defaultTreeModel;

    public StAXStreamTreeViewer() {
        // Handle Swing setup
        super("StAX Tree Viewer");
        setSize(800, 450);
        // setSize(600, 200);
    }

    public void init(File file) throws XMLStreamException,
            FileNotFoundException {
        DefaultMutableTreeNode base = new DefaultMutableTreeNode(
                "XML Document: " + file.getAbsolutePath());

        // Build the tree model
        defaultTreeModel = new DefaultTreeModel(base);
        jTree = new JTree(defaultTreeModel);

        // Construct the tree hierarchy
        buildTree(defaultTreeModel, base, file);

        // Display the results
        getContentPane().add(new JScrollPane(jTree), BorderLayout.CENTER);
    }

    // Swing-related variables and methods, including
    // setting up a JTree and basic content pane

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.out.println("Usage: java javaxml3.StAXStreamTreeViewer "
                        + "[XML Document]");
                return;
            }
            StAXStreamTreeViewer viewer = new StAXStreamTreeViewer();
            File f = new File(args[0]);

            viewer.init(f);
            viewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildTree(DefaultTreeModel treeModel,
            DefaultMutableTreeNode current, File file)
            throws XMLStreamException, FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(file);
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = inputFactory
                .createXMLStreamReader(inputStream);

        addStartDocumentNodes(reader, current);

        parseRestOfDocument(reader, current);
    }

    private void addStartDocumentNodes(XMLStreamReader reader,
            DefaultMutableTreeNode current) {
        DefaultMutableTreeNode version = new DefaultMutableTreeNode(
                "XML Version: " + reader.getVersion());
        current.add(version);

        DefaultMutableTreeNode standalone = new DefaultMutableTreeNode(
                "Standalone? " + reader.isStandalone());
        current.add(standalone);

        DefaultMutableTreeNode standaloneSet = new DefaultMutableTreeNode(
                "Was Standalone Set? " + reader.standaloneSet());
        current.add(standaloneSet);

        DefaultMutableTreeNode encoding = new DefaultMutableTreeNode(
                "Encoding: " + reader.getEncoding());
        current.add(encoding);

        DefaultMutableTreeNode declaredEncoding = new DefaultMutableTreeNode(
                "Declared Encoding: " + reader.getCharacterEncodingScheme());
        current.add(declaredEncoding);
    }

    private void parseRestOfDocument(XMLStreamReader reader,
            DefaultMutableTreeNode current) throws XMLStreamException {

        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
            case XMLStreamConstants.START_ELEMENT:

                DefaultMutableTreeNode element = new DefaultMutableTreeNode(
                        "Element: " + reader.getLocalName());
                current.add(element);
                current = element;

                // Determine namespace
                if (reader.getNamespaceURI() != null) {
                    String prefix = reader.getPrefix();
                    if (prefix == null) {
                        prefix = "[None]";
                    }
                    DefaultMutableTreeNode namespace = new DefaultMutableTreeNode(
                            "Namespace: prefix = '" + prefix + "', URI = '"
                                    + reader.getNamespaceURI() + "'");
                    current.add(namespace);
                }

                if (reader.getAttributeCount() > 0) {
                    for (int i = 0; i < reader.getAttributeCount(); i++) {
                        DefaultMutableTreeNode attribute = new DefaultMutableTreeNode(
                                "Attribute (name = '"
                                        + reader.getAttributeLocalName(i)
                                        + "', value = '"
                                        + reader.getAttributeValue(i) + "')");
                        String attURI = reader.getAttributeNamespace(i);
                        if (attURI != null) {
                            String attPrefix = reader.getAttributePrefix(i);
                            if (attPrefix == null || attPrefix.equals("")) {
                                attPrefix = "[None]";
                            }
                            DefaultMutableTreeNode attNamespace = new DefaultMutableTreeNode(
                                    "Namespace: prefix = '" + attPrefix
                                            + "', URI = '" + attURI + "'");
                            attribute.add(attNamespace);
                        }
                        current.add(attribute);
                    }
                }

                break;
            case XMLStreamConstants.END_ELEMENT:
                current = (DefaultMutableTreeNode) current.getParent();
                break;
            case XMLStreamConstants.CHARACTERS:
                if (!reader.isWhiteSpace()) {
                    DefaultMutableTreeNode data = new DefaultMutableTreeNode(
                            "Character Data: '" + reader.getText() + "'");
                    current.add(data);
                }
                break;
            case XMLStreamConstants.DTD:
                DefaultMutableTreeNode dtd = new DefaultMutableTreeNode(
                        "DTD: '" + reader.getText() + "'");
                current.add(dtd);
                break;
            case XMLStreamConstants.SPACE:
                // let's ignore this
                break;
            case XMLStreamConstants.COMMENT:
                DefaultMutableTreeNode comment = new DefaultMutableTreeNode(
                        "Comment: '" + reader.getText() + "'");
                current.add(comment);
                break;
            default:
                System.out.println(type);
            }
        }
    }

}

package javaxml3;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import java.util.Iterator;

// JDOM imports
import org.jdom.*;
import org.jdom.input.SAXBuilder;

public class SimpleXPathViewer extends JFrame {

    /** The event handler inner class */
    EventHandler eventHandler = new EventHandler();

    /** A text field for displaying the XPath for the selectected node */
    private JTextField statusText;

    /** The JTree used to display the nodes of the xml document */
    private JTree jdomTree;

    /** The selection model used to determine which node was clicked */
    private DefaultTreeSelectionModel selectionModel;

    /** The filename containing the xml file to view */
    private String filename;

    /** Temporary hack to get around the lack of a text node */
    private static Element lastElement;

    class EventHandler implements TreeSelectionListener {

        public void valueChanged(TreeSelectionEvent e) {
            TreePath path= selectionModel.getLeadSelectionPath();

            // If you are just collapsing the tree, you may not have a new path
            if (path != null) {
                JDOMNode selection=
                    (JDOMNode)((DefaultMutableTreeNode)path.getLastPathComponent())
                        .getUserObject();
                buildXPath(selection);
            }
        };
    };

    public SimpleXPathViewer(String fileName) throws Exception {
        super();
        this.filename = fileName;
        setSize(600, 450);
        initialize();
    }

    private void initialize() throws Exception {
        setTitle("Simple XPath Viewer");

        // Setup the UI
        initConnections();

        // Load the JDOM Document
        Document doc = loadDocument(filename);

        // Create the initial JDOMNode from the Factory method
        JDOMNode root = createNode(doc.getRootElement());

        // Create the root node of the JTree and build it from the JDOM Document
        DefaultMutableTreeNode treeNode = 
            new DefaultMutableTreeNode("Document: " + filename);
        buildTree(root, treeNode);

        // Add the node to the tree's model
        ((DefaultTreeModel)jdomTree.getModel()).setRoot(treeNode);
    }

    private void initConnections() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        // Setup the JTree and a pane to display it in
        jdomTree = new JTree();
        jdomTree.setName("JDOM Tree");
        jdomTree.addTreeSelectionListener(eventHandler);
        selectionModel = (DefaultTreeSelectionModel)jdomTree.getSelectionModel();
        getContentPane().add(new JScrollPane(jdomTree), BorderLayout.CENTER);

        // Setup a text box for use in a status bar
        statusText = new JTextField("Click on an element to view xpath");
        JPanel statusBarPane= new JPanel();
        statusBarPane.setLayout(new BorderLayout());
        statusBarPane.add(statusText, BorderLayout.CENTER );
        getContentPane().add(statusBarPane, BorderLayout.SOUTH);
    }

    private Document loadDocument(String filename) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        builder.setIgnoringElementContentWhitespace(true);
        return builder.build(new File(filename));
    }

    private JDOMNode createNode(Object node) {
        if (node instanceof Element) {
            lastElement = (Element)node;
            return new ElementNode((Element)node);
        }

        if (node instanceof Attribute) {
            return new AttributeNode((Attribute)node);
        }

        if (node instanceof Text) {
            return new TextNode((Text)node).setParent(lastElement);
        }

        // All other nodes are not implemented
        return null;
    }

    private void buildTree(JDOMNode node, DefaultMutableTreeNode treeNode) {
        // If this is a whitespace node or unhandled node, ignore it
        if ((node == null) || (node.toString().trim().equals(""))) {
            return;
        }

        DefaultMutableTreeNode newTreeNode = new DefaultMutableTreeNode(node);

        // Walk over the children of the node 
        Iterator i = node.iterator();
        while (i.hasNext()) {
            // Create JDOMNodes on the children and add to the tree
            JDOMNode newNode = createNode(i.next());
            buildTree(newNode, newTreeNode);
        }

        // After all the children have been added, connect to the tree
        treeNode.add(newTreeNode);
    }

    private void buildXPath(JDOMNode node) {
        try {
            statusText.setText(new XPathDisplayNode(node).getXPath());
        } catch (JDOMException e) {
            statusText.setText("Unable to build XPath");
            e.printStackTrace();
        }
    }

    public static void main(java.lang.String[] args) {
        try {
            if (args.length != 1) {
                System.out.println("Usage: java javaxml3.SimpleXPathViewer " + 
                    "[XML Document filename]");
                return;
            }

            /* Create the frame */
            SimpleXPathViewer viewer= new SimpleXPathViewer(args[0]);

            /* Add a windowListener for the windowClosedEvent */
            viewer.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    };
                });
            viewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

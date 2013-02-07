package javaxml3;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class XmlPullTreeViewer extends JFrame {
    /** The base tree to render */
    private JTree jTree;

    /** Tree model to use */
    DefaultTreeModel defaultTreeModel;

    public XmlPullTreeViewer() {
        // Handle Swing setup
        super("XmlPull Tree Viewer");
        setSize(800, 450);
        // setSize(600, 200);
    }

    public void init(File file) throws XmlPullParserException, IOException {
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
                System.out.println("Usage: java javaxml3.XmlPullTreeViewer "
                        + "[XML Document]");
                return;
            }
            XmlPullTreeViewer viewer = new XmlPullTreeViewer();
            File f = new File(args[0]);

            viewer.init(f);
            viewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildTree(DefaultTreeModel treeModel,
            DefaultMutableTreeNode current, File file)
            throws XmlPullParserException, IOException {
        FileInputStream inputStream = new FileInputStream(file);
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, true);
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(inputStream, null);

        parseRestOfDocument(parser, current);
    }

    private void parseRestOfDocument(XmlPullParser parser,
            DefaultMutableTreeNode current) throws XmlPullParserException,
            IOException {

        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
            case XmlPullParser.START_TAG:

                DefaultMutableTreeNode element = new DefaultMutableTreeNode(
                        "Element: " + parser.getName());
                current.add(element);
                current = element;

                // Determine namespace
                if (parser.getNamespace() != null) {
                    String prefix = parser.getPrefix();
                    if (!"".equals(prefix)) {
                        prefix = "[None]";
                    }
                    DefaultMutableTreeNode namespace = new DefaultMutableTreeNode(
                            "Namespace: prefix = '" + prefix + "', URI = '"
                                    + parser.getNamespace() + "'");
                    current.add(namespace);
                }

                if (parser.getAttributeCount() > 0) {
                    for (int i = 0; i < parser.getAttributeCount(); i++) {
                        DefaultMutableTreeNode attrib = new DefaultMutableTreeNode(
                                "Attribute (name = '"
                                        + parser.getAttributeName(i)
                                        + "', value = '"
                                        + parser.getAttributeValue(i) + "')");
                        String attURI = parser.getAttributeNamespace(i);
                        if (!"".equals(attURI)) {
                            String attPrefix = parser.getAttributePrefix(i);
                            if (attPrefix == null || attPrefix.equals("")) {
                                attPrefix = "[None]";
                            }
                            DefaultMutableTreeNode an = new DefaultMutableTreeNode(
                                    "Namespace: prefix = '" + attPrefix
                                            + "', URI = '" + attURI + "'");
                            attrib.add(an);
                        }
                        current.add(attrib);
                    }
                }

                break;
            case XmlPullParser.END_TAG:
                current = (DefaultMutableTreeNode) current.getParent();
                break;
            case XmlPullParser.TEXT:
                if (!parser.isWhitespace()) {
                    DefaultMutableTreeNode data = new DefaultMutableTreeNode(
                            "Character Data: '" + parser.getText() + "'");
                    current.add(data);
                }
                break;
            case XmlPullParser.IGNORABLE_WHITESPACE:
                // let's ignore this
                break;
            case XmlPullParser.COMMENT:
                DefaultMutableTreeNode comment = new DefaultMutableTreeNode(
                        "Comment: '" + parser.getText() + "'");
                current.add(comment);
                break;
            default:
                System.out.println(type);
            }
            type = parser.next();
        }
    }
}

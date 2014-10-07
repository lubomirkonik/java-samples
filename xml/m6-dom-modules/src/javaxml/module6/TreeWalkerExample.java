/* 
 * TreeWalkerExample.java
 * This example uses TreeWalker interface to traverse through all the nodes
 * of a DOM document specified on the command line.
 * The project contains two XML files for testing.
 */

package javaxml.module6;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.TreeWalker;

public class TreeWalkerExample {
    
    public static void main(String[] args) {
        try {
            //Obtain a factory instance
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Obtain a builder instance
            DocumentBuilder loader = factory.newDocumentBuilder();
            // Parse the content of the string and return a document object
            Document document = loader.parse("Sample.xml");
            // Obtain an instance of DocumentTraversal
            DocumentTraversal traversal = (DocumentTraversal) document;
            //Obtain an instance of TreeWalker
            TreeWalker walker = traversal.createTreeWalker
                    (document.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);
            // Traverse through the document
            traverseNodes(walker, "");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // Call the traverseNodes() method
    private static final void traverseNodes(TreeWalker walker, String indent) {
        // Obtain an instance of the current node
        Node node = walker.getCurrentNode();
        //Print the node
        if (((Element) node).getTagName().contains("name")) {
        	System.out.println(indent + "- " + ((Element) node).getTagName() + ": " + node.getTextContent());
        } 
        else {
        	System.out.println(indent + "- " + ((Element) node).getTagName());
        }
        // To create a loop for traversing throughout the document
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            //Traverse the document through walker
            traverseNodes(walker, indent + '\t');
        }
        //Return position to the original level
        walker.setCurrentNode(node);
    }
}

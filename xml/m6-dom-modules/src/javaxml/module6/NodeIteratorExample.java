/* 
 * NodeIteratorExample.java
 * The program uses NodeIterator interface to travese a XML document
 * specified on the command line. The program may be tested using the sample file
 * catalog.xml provided in the project. The program prints the names of all the artists currently
 * listed in the catalog.
 */

package javaxml.module6;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.traversal.*;

public class NodeIteratorExample {
    
    public static void main(String[] args) {
        
        try {
            //Obtain a factory instance
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Obtain a builder instance
            DocumentBuilder parser = factory.newDocumentBuilder();
            //Obtain a DOM implementation instance
            DOMImplementation impl = parser.getDOMImplementation();
            // Check for the traversal module
            if (!impl.hasFeature("traversal", "2.0")) {
                System.out.println("A DOM implementation that supports" +
                        " traversal is required.");
                return;
            }
            // Parse the content of the string and return a document object
            Document doc = parser.parse("catalog.xml");
            // Obtain an instance of DocumentTraversal
            DocumentTraversal traversal = (DocumentTraversal) doc;
            //Obtain an instance of NodeIterator
            NodeIterator iterator = traversal.createNodeIterator
                    (doc, NodeFilter.SHOW_ALL, null, true);
            System.out.println("Artists listed in the current catalog\n");
            Node node;
            while ((node = iterator.nextNode()) != null) {
                if ((node.getNodeName()).equals("ARTIST")) {
                    //Get the textual content and print
                    System.out.println("\t" + node.getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

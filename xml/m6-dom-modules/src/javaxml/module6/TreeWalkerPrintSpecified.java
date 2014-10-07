/* 
 * @Author Dano
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

public class TreeWalkerPrintSpecified {
	
    static boolean correctRoom = false;
    
    public static void main(String[] args) {
        try {
            //Obtain a factory instance
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //Obtain a builder instance
            DocumentBuilder loader = factory.newDocumentBuilder();
            // Parse the content of the string and return a document object
            Document document = loader.parse("internat.xml");
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
        // Find specified node
        if(node.hasAttributes() && node.getNodeName().equals("izba")){
            if(node.getAttributes().getNamedItem("cislo").getNodeValue().equals("2")){
                correctRoom = true;
            }else{
                correctRoom = false;
            }
        }
        // Print specified node
        if(correctRoom){
            System.out.print(indent + "- " + ((Element) node).getTagName());
            if(node.getNodeName().equals("student")){
                System.out.print(": " + node.getTextContent());
            }
            if(node.getNodeName().equals("izba")){
                System.out.print(" cislo " + node.getAttributes().getNamedItem("cislo").getNodeValue());
            }
            System.out.println();
        }
        // To create a loop for traversing throughout the document
        for (Node n = walker.firstChild(); n != null; n = walker.nextSibling()) {
            // Traverse the document through walker
            traverseNodes(walker, indent + '\t');
        }
        // Return position to the original level
        walker.setCurrentNode(node);
    }
}

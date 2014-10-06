package javaxml.module5;
/*
 * @Author Dano
 */

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;

public class AddRoleWithIterator {
    
    static File f = null;
    
    public static void main(String[] args) {
        try {
            f = new File("tomcat-users.xml");
            // Obtain factory instance
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // Obtain builder instance
            DocumentBuilder db = dbf.newDocumentBuilder();
            // Parse document
            Document doc = db.parse(f);
            // Search the entire tree for the first user
            Node firstUser = firstNode(doc, "user");
            // Create a new role
            Element role = doc.createElement("role");
            role.setAttribute("rolename", "newRole");
            // Insert new role into the document
            doc.getDocumentElement().insertBefore(role, firstUser);
            //TODO Document could be formated before written to the file
            // Write the modified document to a new file.
            writeXml(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Iterates through the document to find the first occurrence of nodeName
     * If none are found returns null
     */
    public static Node firstNode(Document doc, String nodeName){
        DocumentTraversal dt = (DocumentTraversal) doc;
        //Iterator will filter out anything that is not an element, since we only care about those
        NodeIterator iterator = dt.createNodeIterator(doc.getDocumentElement(), NodeFilter.SHOW_ELEMENT, null, true);
        
        //Iterate through the document
        Node current = iterator.nextNode();
        while(current != null){
            if(current.getNodeName().equals(nodeName))
                return current;
            current = iterator.nextNode();
        }
        
        //If iterator did not find a suitable result, return null
        return null;
    }
    
    // This method writes a DOM document to a file
    public static void writeXml(Document doc) {
        try {
            // Create a DOM document for writing
            Source source = new DOMSource(doc);
            // Prepare the output file
            Result result = new StreamResult(f);
            // Create an instance of Transformer
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            // Write the DOM document to the file
            xformer.transform(source, result);
            System.out.println("New role was inserted right after all other roles.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


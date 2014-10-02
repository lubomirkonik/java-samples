package javaxml.module5;
/*
 * UpdatePassword.java
 * This program searches the tomocat-users.xml configuration file for the presence of
 * the username specified on the command line. If the user is found, the program modifies 
 * its password with the new password supplied as a commmand line parameter.
 * The program requries three command line parameters.
 * parameter1 : tomcat configuration file name (tomcat-users.xml)
 * parameter2 : username to search
 * parameter3 : new password for the specified user
 */

import java.io.File;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class AddRoleUpdatePassword {
    
    static String name = null;
    static String password = null;
    static File f = null;
    static Document doc = null;
    static int count = 0;
    
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java UpdatePassword tomcat-users.xml username password");
            System.exit(1);
        }
        name = args[1];
        password = args[2];
        try {
            f = new File(args[0]);
            // Obtain factory instance
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            // Obtain builder instance
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            // Parse document
            doc = builder.parse(f);
            // Insert new role element right after existing role elements
            addRoleAfterLast(doc);
            // Search the entire tree for the specified user
            searchAndModify(doc);
            // Write the modified document to the file.
            writeXml(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void addRoleAfterLast(Node node) {
    	System.out.println("\tNode: " + node.getNodeName());

    	Node sibling = null;
    	if (node.getNodeName().equals("role") && 
    			!(sibling = node.getNextSibling().getNextSibling()).getNodeName().equals("role")) {
    		
    		System.out.println("\tInsert before: " + sibling.getNodeName()); 
    		
    		Element role = doc.createElement("role");
    		role.setAttribute("rolename", "role2");
    		Text text = doc.createTextNode("\n  ");
    		
    		Node parent = node.getParentNode();
    		parent.insertBefore(role, sibling);
    		parent.insertBefore(text, sibling);
    		
    		System.out.println("\tNew '" + role.getNodeName() + "' inserted before '" + sibling.getNodeName() + "'");
    		count++;
    		return;
    	}
    	NodeList children = node.getChildNodes();
    	int i = 0;
    	while (i < children.getLength()) {
    		System.out.println("\t" + i);
    		if (count == 1) {
    			break;
    		}
    		addRoleAfterLast(children.item(i++));
    	}
    }
    /*
     *  searchAndModify is a recursive function that searches the user name
     *  specified by the class variable 'name' in the input document and if
     *  found modifies the password attribute to the new value specified by
     *  a class variable
     */
    private static void searchAndModify(Node node) {
        // null value indicates that we have reached a leaf node
        if (node == null) {
            return;
        }
        // Check if the current element is 'user'
        if (node.getNodeName().equals("user")) {
            //Get the username
            String username = node.getAttributes().getNamedItem("username").getNodeValue();
            // check if the username attribute matches the required one
            if (username.equals(name)) {
                // Modify the password
                node.getAttributes().getNamedItem("password").setNodeValue(password);
                System.out.println("\nPassword for user '" + username +
                        "' changed to '" + password + "'");
                return;
            }
        }
        // Get children of the current node
        NodeList children = node.getChildNodes();
        // recurse through the function to visit each node
        int i = 0;
//        System.out.println("\t\tBefore while loop.");
        while (i < children.getLength()) {
            searchAndModify(children.item(i++));
        }
    }
    // This method writes a DOM document to a file
    public static void writeXml(Document doc) {
        try {
          // Create a DOM document for writing
          Source source = new DOMSource(doc);
          StringWriter stringWriter = new StringWriter();
          
          // Prepare the output file
          Result fresult = new StreamResult(f);
          Result result = new StreamResult(stringWriter);
          
          // Create an instance of Transformer
          Transformer xformer = TransformerFactory.newInstance().newTransformer();
//          xformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
          xformer.setOutputProperty(OutputKeys.INDENT, "yes");
          
          // Write the DOM document to the file
          xformer.transform(source, fresult);
          xformer.transform(source, result);
          
          String xmlString = stringWriter.toString();
          System.out.println("\nPouzivatelia:\n\n" + xmlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


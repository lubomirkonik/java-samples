package javaxml.module5;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SeekUser {
	
	static String password = null;
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java SeekUserByPass tomcat-users.xml password");
            System.exit(1);
        }
        password = args[1];
        try {
            File f = new File(args[0]);
            // Obtain factory instance
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // Obtain builder instance
            DocumentBuilder db = dbf.newDocumentBuilder();
            // Parse document
            Document doc = db.parse(f);
            // Search the entire tree for the specified user
            searchUser(doc);
            System.out.println("\nReached end of the document.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        /*
         *  searchUser is a recursive function that searches user 
         *  by the specified password in the input document.
         */
    private static void searchUser(Node node) {
        // null value indicates that we have reached a leaf node
        if (node == null) {
            return;
        }
        // Check if the current element is 'user'
        if (node.getNodeName().equals("user")) {
            //Get the password
            String currentPassword =
                    node.getAttributes().getNamedItem("password").getNodeValue();
            // check if the password attribute matches the required one
            if (currentPassword.equals(password)) {
                // retrieve the username and print it on console
                String username = 
                   node.getAttributes().getNamedItem("username").getNodeValue();
                System.out.println("Username for password '" + password + "': " + username);
                return;
            }
        }
        // Get children of the current node
        NodeList children = node.getChildNodes();
        // recurse through the function to visit each node
        int i = 0;
        while (i < children.getLength()) {
            searchUser(children.item(i++));
        }
    }
}

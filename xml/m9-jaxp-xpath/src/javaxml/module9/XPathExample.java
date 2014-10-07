/*
 * Document:    XPathExample.java
 *
 * Description:
 * This program searches the customer order from database CustomerOrders.xml
 * for the 'Laptop' and displays the total number of orders received.
 */
package javaxml.module9;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class XPathExample {
    
    public static void main(String[] args) {
        int count = 0;
        try {
            // Obtain factory instance
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Obtain builder instance
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Parse the specified document
            Document doc = builder.parse(new File("CustomerOrders.xml"));
            // Obtain path factory instance
            XPathFactory xpathFactory = XPathFactory.newInstance();
            // Create a new empty path
            XPath xpath = xpathFactory.newXPath();
            // Evaluate the path
            NodeList list = (NodeList) xpath.evaluate("//customers/customer/item", 
                    doc, XPathConstants.NODESET);
            for (int i = 0; i < list.getLength(); i++) {
                // Check if the selected node text is 'Laptop'
                if (list.item(i).getTextContent().equalsIgnoreCase("Laptop")) {
                    count++;
                }
            }
            // Print result on user console
            System.out.println("Out of " + list.getLength() + " customers " 
                    + count + " customers have placed an order for Laptop.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

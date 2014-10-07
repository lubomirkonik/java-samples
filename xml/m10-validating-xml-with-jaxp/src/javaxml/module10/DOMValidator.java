/*
 * This program creates an application demonstrating validation of DOM using Validation APIs.
 * DOMValidator.java
 */
package javaxml.module10;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
/*
 * The DOMValidator validates the XML document against the specified XML by first creating a DOM source for the document.
 */
public class DOMValidator {
    
    public static void main(String[] args) {
    	String xmlFile = "tomcat-users.xml";
    	String xsdFile = "tomcat-users.xsd";
        try {       
            // create an object of DocumentBuilder class
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // parse an XML document into a DOM tree
            Document document = parser.parse(new File(xmlFile));
            // create a SchemaFactory capable of understanding WXS schemas
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            // create an object of Source class
            Source schemaFile = new StreamSource(new File(xsdFile));
            // load a WXS schema, represented by a Schema instance
            Schema schema = factory.newSchema(schemaFile);
            // create a Validator instance, which can be used to validate an instance document
            Validator validator = schema.newValidator();
            // validate the DOM tree
            validator.validate(new DOMSource(document));
            System.out.println(xmlFile + " document is valid!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
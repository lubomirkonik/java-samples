/*
 * This program creates an application demonstrating validation of SAX using Validation APIs.
 * SAXValidator.java
 */
package javaxml.module10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import org.xml.sax.InputSource;
/*
 * SAXValidator class validates the given XML document against the specified XML by first creating a SAX source on the document.
 */
public class SAXValidator {
    
    public static void main(String[] args) {
    	String xmlFile = "tomcat-users.xml";
    	String xsdFile = "tomcat-users.xsd";
        try {   
            // Create InputSource on the XML document
            InputSource is = new InputSource(new BufferedReader(new FileReader(xmlFile)));           
            // Create a SchemaFactory capable of understanding WXS schemas
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);  
            // Create an object of Source class
            Source schemaFile = new StreamSource(new File(xsdFile));
            // Load a WXS schema, represented by a Schema instance
            Schema schema = factory.newSchema(schemaFile);
            // Create a Validator instance, which can be used to validate a SAX input source
            Validator validator = schema.newValidator();
            // Validate the source document
            validator.validate(new SAXSource(is));
            System.out.println(xmlFile + " document is valid!");
        } catch (SAXException e) { 
            // Instance document is invalid!
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
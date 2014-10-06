package javaxml.module5;

import java.io.FileOutputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class DOMCreator {
    
	public static void main(String[] args) {
		
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.newDocument();
            
            Element root = doc.createElement("trieda");
            doc.appendChild(root);
            
            Text indent = doc.createTextNode("\n\t");
            root.appendChild(indent);
            Comment comment = doc.createComment("zoznam studentov v triede");
            root.appendChild(comment);
            
            indent = doc.createTextNode("\n\t");
            root.appendChild(indent);
            Element student = doc.createElement("student");
            student.setAttribute("meno", "Marek");
            student.setAttribute("priezvysko", "Sikovny");
            root.appendChild(student);
            
            Text dindent = doc.createTextNode("\n\t\t");
            student.appendChild(dindent);
            Element address = doc.createElement("adresa");
            address.setAttribute("psc", "852 00");
            address.setAttribute("mesto", "Povazka Bystrica");
            student.appendChild(address);
            
            indent = doc.createTextNode("\n\t");
            student.appendChild(indent);
            
            indent = doc.createTextNode("\n\t");
            root.appendChild(indent);
            student = doc.createElement("student");
            student.setAttribute("meno", "Tomas");
            student.setAttribute("priezvysko", "Bystry");
            root.appendChild(student);
            dindent = doc.createTextNode("\n\t\t");
            student.appendChild(dindent);
            address = doc.createElement("adresa");
            address.setAttribute("psc", "936 21");
            address.setAttribute("mesto", "Stupava");
            student.appendChild(address);
            indent = doc.createTextNode("\n\t");
            student.appendChild(indent);
            
            indent = doc.createTextNode("\n\t");
            root.appendChild(indent);
            student = doc.createElement("student");
            student.setAttribute("meno", "Igor");
            student.setAttribute("priezvysko", "Kralik");
            root.appendChild(student);
            dindent = doc.createTextNode("\n\t\t");
            student.appendChild(dindent);
            address = doc.createElement("adresa");
            address.setAttribute("psc", "881 06");
            address.setAttribute("mesto", "Modra");
            student.appendChild(address);
            indent = doc.createTextNode("\n\t");
            student.appendChild(indent);
            
            indent = doc.createTextNode("\n\t");
            root.appendChild(indent);
            student = doc.createElement("student");
            student.setAttribute("meno", "Jaroslav");
            student.setAttribute("priezvysko", "Stastny");
            root.appendChild(student);
            dindent = doc.createTextNode("\n\t\t");
            student.appendChild(dindent);
            address = doc.createElement("adresa");
            address.setAttribute("psc", "815 07");
            address.setAttribute("mesto", "Presov");
            student.appendChild(address);
            indent = doc.createTextNode("\n\t");
            student.appendChild(indent);
            
            indent = doc.createTextNode("\n\t");
            root.appendChild(indent);
            student = doc.createElement("student");
            student.setAttribute("meno", "Milan");
            student.setAttribute("priezvysko", "Prochadzka");
            root.appendChild(student);
            dindent = doc.createTextNode("\n\t\t");
            student.appendChild(dindent);
            address = doc.createElement("adresa");
            address.setAttribute("psc", "949 08");
            address.setAttribute("mesto", "Banska Stiavnica");
            student.appendChild(address);
            indent = doc.createTextNode("\n\t");
            student.appendChild(indent);
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, streamResult);
            String xmlString = stringWriter.toString();
            
            System.out.println("Zoznam studentov:\n\n" + xmlString);
            
            FileOutputStream fos = new FileOutputStream("example.xml");
            fos.write(xmlString.getBytes());
            fos.close();
            
            System.out.println("\nZapisane do suboru: example.xml");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}

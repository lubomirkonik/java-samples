package staxjaxb.medicalrecords;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.XMLEvent;

public class StAXParser {
    static final String MEDICALBEAN = "medicalBean";
    static final String FNAME = "fName";
    static final String LNAME = "lName";
    static final String SSN = "SSN";
    static final String HISTORY = "history";
    
    @SuppressWarnings({"unchecked", "null"})
    public static MedicalBean readMedicalData(String medicalInfo){
        MedicalBean mBean = new MedicalBean();
        try{
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            InputStream in = new FileInputStream(medicalInfo);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            
            while(eventReader.hasNext()){
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()){
                    if (event.asStartElement().getName().getLocalPart().equals(FNAME)){
                        event = eventReader.nextEvent();
                        mBean.setfName(event.asCharacters().getData());
                        continue;
                    }
                }
                if (event.isStartElement()){
                    if (event.asStartElement().getName().getLocalPart().equals(LNAME)){
                        event = eventReader.nextEvent();
                        mBean.setlName(event.asCharacters().getData());
                        continue;
                    }
                }
                if (event.isStartElement()){
                    if (event.asStartElement().getName().getLocalPart().equals(SSN)){
                        event = eventReader.nextEvent();
                        mBean.setSSN(Integer.parseInt(event.asCharacters().getData()));
                        continue;
                    }
                }
                if (event.isStartElement()){
                    if (event.asStartElement().getName().getLocalPart().equals(HISTORY)){
                        event = eventReader.nextEvent();
                        mBean.setHistory(event.asCharacters().getData());
                        continue;
                    }
                }
                if (event.isEndElement()){
                    EndElement endElement = event.asEndElement();
                }
            } 
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (XMLStreamException e){
            e.printStackTrace();
        }
  
        return mBean;
    }
}

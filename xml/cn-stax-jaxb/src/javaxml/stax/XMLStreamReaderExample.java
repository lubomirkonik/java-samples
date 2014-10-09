package javaxml.stax;

import java.io.File;
import java.io.FileInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

public class XMLStreamReaderExample {
    public static void main(String[] args) throws Exception {
        File file = new File("resources\\ccinfo.xml");
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader streamReader = inputFactory.createXMLStreamReader(new FileInputStream(file));
        
        while(streamReader.hasNext()){
            int eventType = streamReader.getEventType();
            switch(eventType){
                case 1:
                    System.out.print("Event Type - START_ELEMENT(1) ");
                    System.out.print("Start Element Name - " + streamReader.getLocalName() + " ");
                    if(streamReader.getAttributeCount() > 0){
                        System.out.print("ATTRIBUTES: ");
                        for(int i = 0; i < streamReader.getAttributeCount(); i++) {
                            System.out.print(streamReader.getAttributeName(i) + " = " + streamReader.getAttributeValue(i) + " ");
                        } 
                    }
                    System.out.println();
                    break;
                case 2:
                    System.out.print("Event Type - END_ELEMENT(2) ");
                    System.out.println("End element name - " + streamReader.getLocalName());
                    break;
                case 3:
                    System.out.print("Event Type - PROCESSING_INSTRUCTION(3) ");
                    System.out.println("Processing Instruction Data - " + streamReader.getPIData());
                    break;
                case 4:
                    System.out.print("Event Type - CHARACTERS(4) ");
                    System.out.println("Character Data - " + streamReader.getText());
                    break;
                case 5:
                    System.out.print("Event Type - COMMENT(5) ");
                    System.out.println("Comment Data - " + streamReader.getText());
                    break;
                case 6:
                    System.out.print("Event Type - SPACE(6) ");
                    System.out.println("Space Info - " + streamReader.getText());
                    break;
                case 7:
                    System.out.print("Event type - START_DOCUMENT(7) ");
                    System.out.print("Character Encoding Scheme - " + streamReader.getCharacterEncodingScheme());
                    System.out.println(" Version - " + streamReader.getVersion());
                    break;
            }
            streamReader.next();
        }
        int eventType = streamReader.getEventType();
        if (eventType == 8){
            System.out.println("Event Type - END_DOCUMENT(8) - closing document");
            streamReader.close();
        }
    }
}

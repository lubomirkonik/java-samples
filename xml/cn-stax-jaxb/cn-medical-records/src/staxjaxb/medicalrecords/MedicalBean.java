package staxjaxb.medicalrecords;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class MedicalBean {
    private String fName;
    private String lName;
    private int SSN;
    private String history;

    public MedicalBean(){
        
    }
    
    public MedicalBean(String fName, String lName, int SSN, String history){
        this.fName = fName;
        this.lName = lName;
        this.SSN = SSN;
        this.history = history;
    }
    
    @XmlElement()
    public int getSSN() {
        return SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    @XmlElement()
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    @XmlElement()
    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    @XmlElement()
    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    } 
}

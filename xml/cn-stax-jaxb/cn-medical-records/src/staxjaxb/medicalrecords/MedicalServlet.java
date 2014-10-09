package staxjaxb.medicalrecords;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

@WebServlet("/MedicalServlet")
public class MedicalServlet extends HttpServlet{
    
    @Override
    public String getServletInfo(){
        return "Processes medical details and marshalls them into an XML file";
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            MedicalBean medicalBean = new MedicalBean();
            medicalBean.setfName(request.getParameter("fName"));
            medicalBean.setlName(request.getParameter("lName"));
            medicalBean.setSSN(Integer.parseInt(request.getParameter("SSN")));
            medicalBean.setHistory(request.getParameter("history"));
            
            marshalXMLDocument(medicalBean, request.getParameter("fName"), Integer.parseInt(request.getParameter("SSN")));
            
            medicalBean = null;
            
            medicalBean = StAXParser.readMedicalData(Integer.parseInt(request.getParameter("SSN")) 
                    + request.getParameter("fName") + ".xml");
            HttpSession session = request.getSession();
            if(session != null){
                session.setAttribute("MedicalInfo", medicalBean);
            }
            response.sendRedirect("displayMedicalInfo.jsp");
        } catch (Exception e){
            out.println(e);
        } finally {
            out.close();
        }
    }
    
    protected void marshalXMLDocument(MedicalBean mb, String fName, int SSN) throws Exception{
        JAXBContext context = JAXBContext.newInstance(MedicalBean.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        File xmlFile = new File(SSN + fName + ".xml");
        marshaller.marshal(mb, new FileWriter(xmlFile));
//        System.out.println(xmlFile.getAbsolutePath());
    }
}

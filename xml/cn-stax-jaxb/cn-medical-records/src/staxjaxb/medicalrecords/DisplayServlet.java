package staxjaxb.medicalrecords;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DisplayServlet")
public class DisplayServlet extends HttpServlet{
    
    @Override
    public String getServletInfo(){
        return "Fetches details for a student";
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
            MedicalBean medicalBean = StAXParser.readMedicalData(Integer.parseInt(request.getParameter("SSN")) 
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
}

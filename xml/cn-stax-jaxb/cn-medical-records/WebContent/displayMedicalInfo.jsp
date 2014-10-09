<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="staxjaxb.medicalrecords.MedicalBean" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical Info</title>
    </head>
    <body>
        <h2>Medical Information</h2>
        <table>
        <% 
        MedicalBean bean = (MedicalBean)session.getAttribute("MedicalInfo");
        out.println("<tr><td>First Name:</td><td>" + bean.getfName() + "</td></tr>");
        out.println("<tr><td>Last Name:</td><td>" + bean.getlName() + "</td></tr>");
        out.println("<tr><td>Social Security Number:</td><td>" + bean.getSSN() + "</td></tr>");
        out.println("<tr><td>Medical History:</td><td>" + bean.getHistory() + "</td></tr>");       
        %>
        </table>
        <a href="index.jsp">Back to index</a>
    </body>
</html>

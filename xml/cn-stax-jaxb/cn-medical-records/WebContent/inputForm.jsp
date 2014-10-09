<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical Info Input</title>
    </head>
    <body>
        <form name="studentInput" action="MedicalServlet" method="POST">
            <table>
                <tr>
                    <td><label for="fName">First Name:</label></td>
                    <td><input type="text" name="fName" id="fName" value="" /></td>
                </tr>
                <tr>
                    <td><label for="lName">Last Name:</label></td>
                    <td><input type="text" name="lName" id="lName" value="" /></td>
                </tr>
                <tr>
                    <td><label for="SSN">SSN:</label></td>
                    <td><input type="number" name="SSN" id="SSN" value="" /></td>
                </tr>
                <tr>
                    <td><label for="history">Medical History:</label></td>
                    <td><input type="text" name="history" id="history" value="" /></td>
                </tr>
            </table>
            <input type="submit" value="Submit" name="btnSubmit" />
            <input type="reset" value="Reset" name="btnReset" />
        </form>
        <a href="index.jsp">Back to index</a>
    </body>
</html>

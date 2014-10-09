<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medical</title>
    </head>
    <body>
        <form name="studentInput" action="DisplayServlet" method="POST">
            <table>
                <tr>
                    <td><label for="fName">First Name:</label></td>
                    <td><input type="text" name="fName" id="fName" value="" /></td>
                </tr>
                <tr>
                    <td><label for="SSN">SSN:</label></td>
                    <td><input type="number" name="SSN" id="SSN" value="" /></td>
                </tr>
            </table>
            <input type="submit" value="Submit" name="btnSubmit" />
            <input type="reset" value="Reset" name="btnReset" />
        </form>
        <a href="index.jsp">Back to index</a>
    </body>
</html>

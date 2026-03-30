<%-- 
    Document   : User
    Created on : 28 Mar 2026, 5:41:10 pm
    Author     : riya vesuwala
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="JMSSenderServlet" method="post">
            <label>Enter Message:</label>
            <input type="text" name="msg" size="50"/>
            <input type="submit" value="Send"/>
        </form>
    </body>
</html>

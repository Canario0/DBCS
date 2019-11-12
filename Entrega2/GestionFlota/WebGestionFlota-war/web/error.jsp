<%-- 
    Document   : error
    Created on : 07-oct-2019, 11:04:44
    Author     : cevp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Algo ha ido mal: <%= session.getAttribute("mensaje")%></h1>
    </body>
</html>

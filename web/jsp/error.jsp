<%-- 
    Document   : error
    Created on : 9 oct 2024, 12:16:27
    Author     : Amand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error - Page</title>
    </head>
    <body>
        <h1>Error: Matricula o contraseña incorrectos</h1>
        <a href="${pageContext.request.contextPath}/jsp/login.jsp">Intentar de nuevo</a>
    </body>
</html>
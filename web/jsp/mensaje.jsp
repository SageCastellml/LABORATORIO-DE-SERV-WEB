<%-- 
    Document   : mensaje
    Created on : 9 oct 2024, 12:18:19
    Author     : Amand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mensaje</title>
    </head>
    <body>
        <h2>Operación</h2>
        <p><%= request.getAttribute("mensaje")%></p>
        <a href="${pageContext.request.contextPath}/jsp/login.jsp">Iniciar Sesión</a>
    </body>
</html>


<%-- 
    Document   : palabraClaveCrear
    Created on : 11-abr-2020, 12:25:18
    Author     : aleja
--%>

<%@page import="mercafour.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Usuario user = (Usuario)session.getAttribute("user");
    if(user == null){
        response.sendRedirect("login.jsp");
    }
    
    String status = (String)request.getAttribute("status");
    if(status == null){
        status = "";
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Palabra Clave</title>
    </head>
    <body>
        <h1>Introduzca la palabra clave</h1>
        <form action="PalabraClaveCrear">
            Palabra: <input type="text" size="50" name="palabra"/> <br/>
            <input type="submit" value="Enviar"/>
        </form>
        <p><%= status %></p>
    </body>
</html>

<%-- 
    Document   : login
    Created on : 26-mar-2020, 11:20:53
    Author     : aleja
--%>

<%@page import="mercafour.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario user = (Usuario)session.getAttribute("user");
    String status;
    if(user != null){
        if(user.getAdministrador()){
            response.sendRedirect("menuAdministrador.jsp"); // Pagina principal admin
        }else{
            response.sendRedirect("menuProductoVendedor.jsp"); //Paquina principal usuario
        }
    }
    
    status = (String)request.getAttribute("status");
    if(status == null){
        status = "";
    }

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/styleMenuProductoVendedor.css">
        <title>Mercafour: Inicio de Sesion</title>
    </head>
    <body>
        <h1>Mercafour</h1>
        <h2>Inicio de sesi&oacute;n</h2>
        <form action="Login" method="post">
            Email <input type="text" name="email"/><br/>
            Contrase&ntilde;a <input type="password" name="password"/><br/>
            <input type="submit" value="Iniciar Sesión"/>
            <a href="registro.jsp">Registrarse</a>
        </form>
        <p><%= status %></p>
        
    </body>
</html>

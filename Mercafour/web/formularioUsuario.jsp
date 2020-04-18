<%-- 
    Document   : formularioUsuario
    Created on : 05-abr-2020, 12:39:45
    Author     : Adrián Laguna
--%>

<%@page import="mercafour.dto.UsuarioDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar usuario</title>
    </head>
    
    <%
        String idUsuario = "", username = "", password = "", nombre = "", email = "";
        UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("usuario");
 

        if (usuario != null) {
            idUsuario = usuario.getUserId().toString();
            username = usuario.getUsername();
            password = usuario.getPassword();
            nombre = usuario.getNombre();
            email = usuario.getEmail();
        }

    %>
    
    <body>
        <h1>Edite los datos del usuario</h1>
        
        <form method="post" action="UsuariosGuardar">  
            <input type="hidden" name="idUsuario" value="<%= idUsuario%>" />
            Username: <input type="text" name="username" value="<%= username %>"/></br>
            Contrase&ntilde;a: <input type="password" name="password" value="<%= password %>"></br>
            Nombre: <input type="text" name="nombre" value="<%= nombre %>"/></br>
            Email: <input type="text" name="email" value="<%= email %>"/></br>
            <input type="submit" name="Enviar" value="Enviar"/>
        </form>
        
    </body>
</html>


<%-- 
    Document   : formularioUsuario
    Created on : 05-abr-2020, 12:39:45
    Author     : Adrián Laguna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Introducir usuario</title>
    </head>
    <body>
        <h1>Introduzca los datos del usuario</h1>
        
        <form action="ProductosGuardar">
            <input type="hidden" name="idProducto" value="<%= idUsuario %>" />
            
            Administrador: <input type="checkbox" id="admin" name="admin" value="admin"></br>
            Username: <input type="text" name="username" value="<%= username %>"/></br>
            Contrase&ntilde;a: <input type="password" name="password" value="<%= password %>"></br>
            Nombre: <input type="text" name="nombre" value="<%= nombre %>"/></br>
            Email: <input type="text" name="email" value="<%= email %>"/></br>
            <input type="submit" name="Enviar"/>
        </form>
        
    </body>
</html>

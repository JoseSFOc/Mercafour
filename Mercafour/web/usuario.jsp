<%-- 
    Document   : usuario
    Created on : 18-abr-2020, 18:18:40
    Author     : Adrián Laguna
--%>

<%@page import="mercafour.dto.UsuarioDTO"%>
<%@page import="mercafour.entity.Usuario"%>
<%@page import="mercafour.dto.ProductoDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página del usuario</title>
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
        <h1><%= username %></h1>
        Nombre: <%= nombre %><br/>
        Email: <%= email %><br/><br/>
        <a href="ProductosListar?modo=2&idProp=<%= idUsuario %>">Productos de <%= username %></a>
    </body>
</html>

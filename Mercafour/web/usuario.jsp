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
        <link rel="stylesheet" href="CSS/styleUsuario.css">
    </head>
    <%
        String idUsuario = "", username = "", password = "", nombre = "", email = "";
        UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("usuario");
        UsuarioDTO conectado = ((Usuario) session.getAttribute("user")).getDTO();

        if (usuario != null) {
            idUsuario = usuario.getUserId().toString();
            username = usuario.getUsername();
            password = usuario.getPassword();
            nombre = usuario.getNombre();
            email = usuario.getEmail();
        }

    %>
    <body>

    <u1 class="navbar">
        <% if (conectado.isIsAdmin()) {%><li><a href="menuAdministrador.jsp">Página de Administración</a></li><% }%>
        <li><a href="PalabrasClaveListar">Palabras Clave</a></li>
        <li><a href="Logout">Logout</a></li>
    </u1>

    <u2 class="navbar">
        <li><a href="ProductosListar?modo=0">Mis Productos</a></li>
        <li><a href="ProductosListar?modo=1">Todos los Productos</a></li>
        <li><a href="ProductosCrear">Subir Productos</a></li>
    </u2>

    <h1><%= username%></h1>

    <fieldset>
        <label>Nombre: </label><%= nombre%><br/>
        <label>Email: </label><%= email%><br/><br/>
        <a href="ProductosListar?modo=2&idProp=<%= idUsuario%>">Productos de <%= username%></a>
    </fieldset>

</body>
</html>

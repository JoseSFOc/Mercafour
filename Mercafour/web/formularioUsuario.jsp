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
        <link rel="stylesheet" href="CSS/styleFormularioUsuario.css">
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

    <u1 class="navbar">
        <% if (usuario.isIsAdmin()) {%><li><a href="menuAdministrador.jsp">Página de Administración</a></li><% }%>
        <li><a href="PalabrasClaveListar">Palabras Clave</a></li>
        <li><a href="Logout">Logout</a></li>
    </u1>

    <u2 class="navbar">
        <li><a href="ProductosListar?modo=0">Mis Productos</a></li>
        <li><a href="ProductosListar?modo=1">Todos los Productos</a></li>
    </u2>

    <form method="post" action="UsuariosGuardar">  
        <input type="hidden" name="idUsuario" value="<%= idUsuario%>" />
        Username: <input type="text" name="username" value="<%= username%>"/></br>
        Contrase&ntilde;a: <input type="password" name="password" value="<%= password%>"></br>
        Nombre: <input type="text" name="nombre" value="<%= nombre%>"/></br>
        Email: <input type="text" name="email" value="<%= email%>"/></br>
        <input type="submit" name="Enviar" value="Enviar"/>
    </form>

</body>
</html>


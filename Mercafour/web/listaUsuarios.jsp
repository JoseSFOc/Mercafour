<%-- 
    Document   : listaUsuarios
    Created on : 05-abr-2020, 11:59:10
    Author     : Adri�n Laguna
--%>

<%@page import="mercafour.dto.UsuarioDTO"%>
<%@page import="java.util.List"%>
<%@page import="mercafour.entity.Usuario"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de usuarios</title>
        <link rel="stylesheet" href="CSS/styleListaUsuarios.css">
    </head>

    <%
        List<UsuarioDTO> listaUsuarios = (List) request.getAttribute("listaUsuarios");
        UsuarioDTO user = ((Usuario) session.getAttribute("user")).getDTO();
    %>

    <body>
        <h1>Usuarios</h1>

    <u1 class="navbar">
        <% if (user.isIsAdmin()) {%><li><a href="menuAdministrador.jsp">Página de Administración</a></li></br><% } %>
        <li><a href="ProductosListar?modo=1">Todos los Productos</a></li>
        <li><a href="ProductosListar?modo=0">Mis Productos</a></li>
        <li><a href="ProductosCrear">Subir Productos</a></li>
        <li><a href="PalabrasClaveListar">Palabras Clave</a></li></br>
        <li><a href="Logout">Logout</a></li>
    </u1>

    <table>
        <thead>
            <tr>
                <th>ID Usuario</th>
                <th>Administrador</th>
                <th>Username</th>
                <th>Contrase&ntilde;a</th>
                <th>Nombre</th>
                <th>Email</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <%
                for (UsuarioDTO u : listaUsuarios) {
            %>
            <tr>
                <td><%= u.getUserId()%></td>
                <td><%= u.isIsAdmin()%></td>
                <td><a href="UsuariosVer?id=<%= u.getUserId()%>"><%= u.getUsername()%></td>
                <td><%= u.getPassword()%></td>
                <td><%= u.getNombre()%></td>
                <td><%= u.getEmail()%></td>
                <td><button><a href="UsuariosEditar?id=<%= u.getUserId()%>">Editar</button></td>
                <td><button><a href="UsuariosBorrar?id=<%= u.getUserId()%>">Borrar</button></td>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>
</body>
</html>

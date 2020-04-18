<%-- 
    Document   : listaUsuarios
    Created on : 05-abr-2020, 11:59:10
    Author     : Adrián Laguna
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
    </head>
    
    <%
        List<UsuarioDTO> listaUsuarios = (List)request.getAttribute("listaUsuarios");
    %>
    
    <body>
        <h1>Usuarios</h1>
            <button><a href="registro.jsp">A&ntilde;adir usuario</a></button>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID Usuario</th>
                        <th>Administrador</th>
                        <th>Username</th>
                        <th>Contrase&ntilde;a</th>
                        <th>Nombre</th>
                        <th>Email</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (UsuarioDTO u : listaUsuarios) {
                    %>
                    <tr>
                        <td><%= u.getUserId() %></td>
                        <td><%= u.isIsAdmin() %></td>
                        <td><a href="UsuariosVer?id=<%= u.getUserId()%>"><%= u.getUsername() %></td>
                        <td><%= u.getPassword() %></td>
                        <td><%= u.getNombre() %></td>
                        <td><%= u.getEmail() %></td>
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

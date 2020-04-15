<%-- 
    Document   : listaPalabrasClave
    Created on : 11-abr-2020, 12:51:29
    Author     : aleja
--%>

<%@page import="mercafour.dto.PalabraClaveDTO"%>
<%@page import="java.util.List"%>
<%@page import="mercafour.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario user = (Usuario) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
    }

    String status = "";
    if (request.getAttribute("status") != null) {
        status = (String) request.getAttribute("status");
    }

    List<PalabraClaveDTO> palabrasClave = (List<PalabraClaveDTO>) request.getAttribute("palabrasClave");


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/styleListaPalabrasClave.css">
        <title>Palabras Clave</title>
    </head>
    <body>
        <h1>Listado de Palabras Clave</h1>

    <u1 class="navbar">
        <% if (user.getDTO().isIsAdmin()) {%><li><a href="menuAdministrador.jsp">Página de Administración</a></li><% }%>
        <li><a href="">Buscar</a></li>
        <li><a href="Logout">Logout</a></li>
    </u1>

    <u2 class="navbar">
        <li><a href="ProductosListar?modo=1">Todos los Productos</a></li>
        <li><a href="ProductosListar?modo=0">Mis Productos</a></li>
        <li><a href="ProductosCrear">Subir Productos</a></li>
    </u2>

    <form action="PalabraClaveCrear">
        <label name="palabra">Nueva palabra clave</label> </br></br>
        Palabra: <input type="text" size="50" name="palabra"/> <br/>
        <input type="submit" value="Enviar"/>
    </form>
    <p><%= status%></p>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Palabra</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <%
                for (PalabraClaveDTO p : palabrasClave) {

            %>
            <tr>
                <td><%= p.getIdPalabraClave()%></td>
                <td><%= p.getPalabra()%></td>
                <%
                    if (user.getAdministrador()) {
                %>
                <td><a href="PalabraClaveBorrar?idPalabra=<%= p.getIdPalabraClave()%>">Borrar</a>
                <%
                    } else {
                %>
                <td></td>
                <%  }  %>
            </tr>
            <%
                }
            %>
        </tbody>
    </table>

</body>
</html>

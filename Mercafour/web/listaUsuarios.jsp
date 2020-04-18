<%-- 
    Document   : listaUsuarios
    Created on : 05-abr-2020, 11:59:10
    Author     : Adrián Laguna
--%>

<%@page import="mercafour.dto.UsuarioDTO"%>
<%@page import="mercafour.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de usuarios</title>
        <link rel="stylesheet" href="CSS/styleListaUsuarios.css">
    </head>
    <body>

        <%
            UsuarioDTO user = ((Usuario) session.getAttribute("user")).getDTO();

            if (user == null) {
                response.sendRedirect("login.jsp");
            }

        %>

    <u1 class="navbar">
        <% if (user.isIsAdmin()) {%><li><a href="menuAdministrador.jsp">Página de Administración</a></li><% } %>
        <li><a href="">Buscar</a></li>
        <li><a href="PalabrasClaveListar">Palabras Clave</a></li>
        <li><a href="Logout">Logout</a></li>
    </u1>

    <u2 class="navbar">
        <li><a href="ProductosListar?modo=1">Todos los Productos</a></li>
        <li><a href="ProductosListar?modo=0">Mis Productos</a></li>
        <li><a href="ProductosCrear">Subir Productos</a></li>
    </u2>


    <h1>Usuarios</h1>
    <form action="UsuariosListar">
        Buscar por: <br/><br/>
        Administrador:
        <select name="filtro_administrador">
            <option value="">-Administador o no-</option>
            <%
                for (int i = 0; i < 1; i++) {
            %>
            <option value="<%=i%>"><%=i%></option>
            <%
                }
            %>
        </select>
        <br/>
        <br/>
        Username: 
        <input type="text" name="filtro_username"/>
        <br/>
        <br/>
        Nombre: 
        <input type="text" name="filtro_usuario_nombre"/>
        <br/>
        <br/>
        Email: 
        <input type="text" name="filtro_email"/>
        <br/>
        <br/>
        Búsqueda libre: <input type="text" name="filtro_palabras_clave" value="Búsqueda" /></br>              
        <input type="submit" value="Filtrar" />
        <form action="ProductosListarResetear">
            <button>Resetear filtros</button>
        </form>
    </form>

    <br/>
    <br/>
    <h2>Usuarios encontrados</h2>
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
                //for(int j = 0 ; j<5; j++){

            %>
            <tr>
                <td><%=1%></td>
                <td><%=2%></td>
                <td><%=2%></td>
                <td><%=2%></td>
                <td><%=2%></td>
                <td><%=2%></td>
                <td><button><a href="">Modificar</button></td>
                <td><button><a href="">Borrar</button></td>
            </tr>
            <%
                // }
            %>
        </tbody>
    </table>

</body>
</html>

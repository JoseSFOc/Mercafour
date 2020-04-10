<%-- 
    Document   : productosListado
    Created on : 03-abr-2020, 11:12:14
    Author     : josem
--%>

<%@page import="mercafour.dto.UsuarioDTO"%>
<%@page import="mercafour.dto.ProductoDTO"%>
<%@page import="mercafour.entity.Producto"%>
<%@page import="mercafour.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="mercafour.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Productos</title>
        <link rel="stylesheet" href="CSS/styleProductosListado.css">
    </head>
    <%
        List<Usuario> listaUsuarios = (List) request.getAttribute("listaUsuarios");
        List<Categoria> listaCategorias = (List) request.getAttribute("listaCategorias");
        List<ProductoDTO> listaProductos = (List) request.getAttribute("listaProductos");
        UsuarioDTO user = ((Usuario) session.getAttribute("user")).getDTO();
    %>
    <body>
        <h1>Listado de Productos</h1>

    <u1 class="navbar">
        <li><a href="menuProductoVendedor.jsp">Página principal</a></li>
        <li><a href="">Buscar</a></li>
        <li><a href="">Categorias</a></li>
        <li><a href="Logout">Logout</a></li>
    </u1>

    <u2 class="navbar">
        <li><a href="ProductosListar?modo=0">Mis Productos</a></li>
        <li><a href="ProductosListar?modo=1">Todos los Productos</a></li>
        <li><a href="ProductosCrear">Subir Productos</a></li>
    </u2>

    <%
        if (listaProductos == null || listaProductos.isEmpty()) {
    %>          
    <h2>En la actualidad no hay ningun producto en la base de datos</h2>
    <%
    } else {
    %>
    <main>
        <table>
            <tr>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Fecha</th>
                <th>Propietario</th>
                <th>Categoria</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <%
                for (ProductoDTO p : listaProductos) {
            %>
            <tr>
                <td><%= p.getNombre()%></td>
                <td><%= p.getPrecio()%></td>
                <td><%= p.getFecha().toString()%></td>
                <td><%= p.getPropietario().getNombre()%></td>
                <td><%= p.getCategoria().getNombre()%></td>
                <td><a href="ProductosVer?id=<%= p.getProductoId()%>">Ver</a></td>

                <td><% if (p.getPropietario().equals(user)) { %><a href="ProductosEditar?id=<%= p.getProductoId()%>">Editar</a><%  } else { %>Editar<%  } %></td>
                <td><% if (p.getPropietario().equals(user)) { %><a href="ProductosBorrar?id=<%= p.getProductoId()%>">Borrar</a><%  } else { %>Borrar<%  } %></td>

            </tr>
            <%
                } // Cierre del for
            %>

        </table>
    </main>
    <%
            } // Cierre if
%>
</body>
</html>

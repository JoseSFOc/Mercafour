<%-- 
    Document   : productosListado
    Created on : 03-abr-2020, 11:12:14
    Author     : josem
--%>

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
    </head>
    <%
        List<Usuario> listaUsuarios = (List)request.getAttribute("listaUsuarios");
        List<Categoria> listaCategorias = (List)request.getAttribute("listaCategorias");
        List<ProductoDTO> listaProductos = (List)request.getAttribute("listaProductos");
        Usuario user = (Usuario)session.getAttribute("user");
        String imagen = "", categoria = "";
    %>
    <body>
        <h1>Listado de Productos</h1>
        
    <%
        if (listaProductos == null || listaProductos.isEmpty()) {
    %>          
            <h2>En la actualidad no hay ningun producto en la base de datos</h2>
    <%
        } else {
    %>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>NOMBRE</th>
            <th>DESCRIPCION</th>
            <th>PRECIO</th>
            <th>FECHA</th>
            <th>IMAGEN</th>
            <th>PROPIETARIO</th>
            <th>CATEGORIA</th>
        </tr>
        <%
            for(ProductoDTO p : listaProductos){
        %>
        <tr>
            <td><%= p.getProductoId() %></td>
            <td><%= p.getNombre() %></td>
            <td><%= p.getDescripcion() %></td>
            <td><%= p.getPrecio() %></td>
            <td><%= p.getFecha().toString() %></td>
            <td><%= imagen %></td>
            <td><%= p.getPropietario().getNombre() %></td>
            <td><%= categoria %></td>
            <td><a href="ProductosVer?id=<%= p.getProductoId()%>">Ver</a></td>
            <td><a href="ProductosEditar?id=<%= p.getProductoId()%>">Editar</a></td>
            <td><a href="ProductosBorrar?id=<%= p.getProductoId() %>">Borrar</a></td>   
        </tr>
        <%
            } // Cierre del for
        %>
        
    </table>
    <%  
        } // Cierre if
    %>
    
    </body>
</html>

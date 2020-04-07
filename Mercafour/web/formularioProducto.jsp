<%-- 
    Document   : formularioProducto
    Created on : 01-abr-2020, 19:11:58
    Author     : josem
--%>

<%@page import="mercafour.dto.ProductoDTO"%>
<%@page import="mercafour.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="mercafour.entity.Usuario"%>
<%@page import="mercafour.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Producto</title>
    </head>
    
    <%
        String idProducto = "", nombre = "", descripcion = "", precio = "";
        ProductoDTO producto = (ProductoDTO)request.getAttribute("producto");
        List<Usuario> listaUsuarios;
        List<Categoria> listaCategorias;
        
        if(producto != null){
            idProducto = producto.getProductoId().toString();
            nombre = producto.getNombre();
            descripcion = producto.getDescripcion();
            precio = producto.getPrecio().toString();
        }

        listaUsuarios = (List<Usuario>)request.getAttribute("listaUsuarios");
        listaCategorias = (List<Categoria>)request.getAttribute("listaCategorias");

    %>
    
    <body>
        <h1>Introduzca los datos del producto</h1>
        
        <form method="post" action="ProductosGuardar">
            <input type="hidden" name="idProducto" value="<%= idProducto %>" />
            
            Nombre: <input type="text" name="nombre" value="<%= nombre %>"/></br>
            Descripcion: </br>
            <textarea name="descripcion" rows="10" cols="30" value="<%= descripcion %>"/></textarea></br>
            Precio: <input type="number" placeholder="1.00" step="0.01" min="0" max="1000000" name="precio" value="<%= precio %>"/></br>
            Imagen: <input type="file" name="imagen" value="" accept="image/*"/></br>
            Categoria: 
            <select name="categoria">
                <%
                    for(Categoria c : listaCategorias){
                        String str = "";
                        if(c.getSupercategoria() != null) str = " - ";
                %>
                <option value="<%= c.getIdCategoria() %>"><%= str + c.getNombre() %></option>
                <%
                    }
                %>
            </select></br>
            <input type="submit" name="Enviar" value="Enviar"/>
        </form>
        
    </body>
</html>

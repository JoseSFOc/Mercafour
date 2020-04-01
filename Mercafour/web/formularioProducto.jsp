<%-- 
    Document   : formularioProducto
    Created on : 01-abr-2020, 19:11:58
    Author     : josem
--%>

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
        Producto producto = (Producto)request.getAttribute("producto");
        List<Usuario> listaUsuarios;
        List<Categoria> listaCategorias;
        
        if(producto != null){
            idProducto = producto.getIdProducto().toString();
            nombre = producto.getNombre();
            descripcion = producto.getDescripcion();
            precio = producto.getPrecio().toString();
        }

        listaUsuarios = (List<Usuario>)request.getAttribute("listaUsuarios");
        listaCategorias = (List<Categoria>)request.getAttribute("listaCategorias");

    %>
    
    <body>
        <h1>Introduzca los datos del producto</h1>
        
        <form action="ProductoGuardar">
            <input type="hidden" name="idProducto" value="<%= idProducto %>" />
            
            Nombre: <input type="text" name="nombre" value="<%= nombre %>"/></br>
            Descripcion: <input type="text" name="descripcion" value="<%= descripcion %>"/></br>
            Precio: <input type="number" placeholder="1.00" step="0.01" min="0" max="10" value="<%= precio %>"/></br>
            Imagen: <input type="image" name="imagen" value=""/></br>
            Categoria: 
            <select name="categoria">
                <%
                    for(Categoria c : listaCategorias){
                %>
                <option value="<%= c.getIdCategoria() %>"><%= c.getNombre() %></option>
                <%
                    }
                %>
            </select></br>
            <input type="submit" name="Enviar"/>
        </form>
        
    </body>
</html>

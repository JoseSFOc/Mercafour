<%-- 
    Document   : producto
    Created on : 29-mar-2020, 21:06:22
    Author     : Marco Hurtado
--%>

<%@page import="mercafour.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Producto</title>
    </head>
    <%
        Producto producto;
        String categoria = "";
        
        producto = (Producto)request.getAttribute("producto");        
        if(producto.getCategoria() != null){
            categoria = producto.getCategoria().getNombre();
        }
    %>    
    <body>
        <h1>Página del producto</h1>
        
        Nombre: <%= producto.getNombre() %></br>
        Descripcion: <%= producto.getDescripcion() %></br>
        Precio: <%= producto.getPrecio().toString() %></br>
        Fecha: <%= producto.getFecha().toString() %></br>
        Propietario: <%= producto.getPropietario().getNombre() %></br>
        Categoria: <%= categoria %></br>
        
        <h2>Imagen</h2>
        <form method="" action="ProductoComentar">
            Comentario:<br/>
            <textarea id="comentario" rows="4" cols="50">Introduce un comentario (300 caracteres máximo).
            </textarea><br/>
            <br/>
            <button>Enviar comentario</button>
            <br/><br/>
        </form>
        <form method="" action="ProductoValorar">
            Valoración:
            <input type="radio" name="valoracion" value="1" />1
            <input type="radio" name="valoracion" value="2" />2
            <input type="radio" name="valoracion" value="3" />3
            <input type="radio" name="valoracion" value="4" />4
            <input type="radio" name="valoracion" value="5" />5
            <br/><br/>
            <button>Enviar valoración</button>
        </form>
    </body>
</html>

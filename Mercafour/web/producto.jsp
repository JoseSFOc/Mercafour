<%-- 
    Document   : producto
    Created on : 29-mar-2020, 21:06:22
    Author     : Marco Hurtado
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mercafour.dto.ComentarioDTO"%>
<%@page import="mercafour.dto.ProductoDTO"%>
<%@page import="mercafour.entity.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Producto</title>
    </head>
    <%
        SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        ProductoDTO producto;
        String categoria = "", imagen = "", productoId = "";

        producto = (ProductoDTO) request.getAttribute("producto");
        if (producto.getCategoria() != null) {
            categoria = producto.getCategoria().getNombre();
            productoId = producto.getProductoId().toString();
        }
        if (producto.getImagen()!= null) {
            imagen = producto.getImagen();
        }
    %>    
    <body>
        <h1>Página del producto</h1>

        Nombre: <%= producto.getNombre()%></br>
        Descripcion: <%= producto.getDescripcion()%></br>
        Precio: <%= producto.getPrecio().toString()%></br>
        Fecha: <%= producto.getFecha().toString()%></br>
        Propietario: <%= producto.getPropietario().getNombre()%></br>
        Categoria: <%= categoria%></br>

        <h2>Imagen</h2>
        <img src="<%= imagen %>"> 

        <form method="" action="ProductoValorar">
            <input type="hidden" name="productoId" value="<%= productoId %>" />
            Comentario:<br/>
            <textarea id="comentario" name="textoComentario" rows="4" cols="50">Introduce un comentario (300 caracteres máximo).
            </textarea><br/>
            <br/>
            <button>Enviar comentario</button>
            <br/><br/>
            
            Valoración:
            <input type="radio" name="valoracion" value="1" />1
            <input type="radio" name="valoracion" value="2" />2
            <input type="radio" name="valoracion" value="3" />3
            <input type="radio" name="valoracion" value="4" />4
            <input type="radio" name="valoracion" value="5" />5
            <br/><br/>
            <button>Enviar valoración</button>
        </form>
        <h2>Comentarios</h2>
      
        <%
            if(producto.getComentarios()==null || producto.getComentarios().isEmpty()){
        %>
         <p>En la actualidad no hay ningun comentario para este producto</p>
        <%
            }else{
                for (ComentarioDTO com : producto.getComentarios()) {
        %>
        <h3>Autor: <%=com.getAutor().getNombre()%>. Fecha: <%=format.format(com.getFecha())%></h3><br/>
        <h3>Valoración: <%=com.getValoracion()%>/5</h3><br/>
        <p><%=com.getTexto()%></p>
        <br/>
        <br/>
        <%
                }
        %>
        <%
            }
        %>
        
    </body>
</html>

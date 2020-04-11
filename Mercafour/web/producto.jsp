<%-- 
    Document   : producto
    Created on : 29-mar-2020, 21:06:22
    Author     : Marco Hurtado
--%>

<%@page import="java.util.List"%>
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
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        ProductoDTO producto;
        Producto p;
        List<ComentarioDTO> comentarios = (List)request.getAttribute("listaComentarios");
        String categoria = "", imagen = "", productoId = "";

        //p =  (Producto) request.getAttribute("producto");
        //producto = p.getDTO();
        producto = (ProductoDTO) request.getAttribute("producto");
        if (producto.getCategoria() != null) {
            categoria = producto.getCategoria().getNombre();
            productoId = producto.getProductoId().toString();
        }else{
            System.out.println("´");
        }
    %>    
    <body>
        <h1>Página del producto</h1>

        Nombre: <%= producto.getNombre()%></br>
        Descripcion: <%= producto.getDescripcion()%></br>
        Precio: <%= producto.getPrecio().toString()%></br>
        Fecha: <%= format.format(producto.getFecha())%></br>
        Propietario: <%= producto.getPropietario().getNombre()%></br>
        Categoria: <%= categoria%></br>

        <h2>Imagen</h2><br/>
        <%
            if (producto.getImagen()== null || producto.getImagen().isEmpty()) {
                
        %>
        <p>Este producto no tiene imagen.</p>
        <%
            }else{
                imagen = producto.getImagen();
        %>                
        <img src="<%= imagen %>"> 
        %>
        <%
            }
        %>
        <form method="" action="ProductosValorar">
            <input type="hidden" name="id" value="<%= productoId %>" />
            Comentario:<br/>
            <textarea id="comentario" name="textoComentario" rows="4" cols="50">Introduce un comentario (300 caracteres máximo).
            </textarea><br/>
            <br/><br/>
            
            Valoración:
            <input type="radio" name="valoracion" value="1" />1
            <input type="radio" name="valoracion" value="2" />2
            <input type="radio" name="valoracion" value="3" />3
            <input type="radio" name="valoracion" value="4" />4
            <input type="radio" name="valoracion" value="5" />5
            <br/><br/>
            <button>Enviar valoración</button>
            <br/>
        </form>
            <%/*
            <form action="ProductosListar?modo=1">
                <button>Volver</button>
            </form>
            */%>
        <a href="ProductosListar?modo=1">Volver</a>
        <h2>Comentarios</h2>
      
        <%
            if(comentarios==null || comentarios.isEmpty()){
                
        %>
         <p>En la actualidad no hay ningun comentario para este producto</p>
        <%
            }else{
                for (ComentarioDTO com : comentarios) {System.out.println(com.getTexto());};
                for (ComentarioDTO com : comentarios) {
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

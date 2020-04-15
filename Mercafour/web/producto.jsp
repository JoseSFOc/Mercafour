<%-- 
    Document   : producto
    Created on : 29-mar-2020, 21:06:22
    Author     : Marco Hurtado
--%>

<%@page import="mercafour.dto.PalabraClaveDTO"%>
<%@page import="mercafour.entity.PalabraClave"%>
<%@page import="mercafour.entity.Usuario"%>
<%@page import="mercafour.dto.UsuarioDTO"%>
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
        <link rel="stylesheet" href="CSS/styleProducto.css">
        <title>Producto</title>
    </head>
    <%
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        ProductoDTO producto;
        Producto p;
        List<ComentarioDTO> comentarios = (List) request.getAttribute("listaComentarios");
        String categoria = "", imagen = "", productoId = "";
        String status;
        status = (String) request.getAttribute("status");
        UsuarioDTO user = ((Usuario) session.getAttribute("user")).getDTO();
        if (status == null) {
            status = "";
        }
        producto = (ProductoDTO) request.getAttribute("producto");
        if (producto.getCategoria() != null) {
            categoria = producto.getCategoria().getNombre();
            productoId = producto.getProductoId().toString();
        } else {
            System.out.println("´");
        }
    %>    
    <body>
        <h1>Página del producto</h1>

    <u1 class="navbar">
        <% if (user.isIsAdmin()) {%><li><a href="menuAdministrador.jsp">Página de Administración</a></li><% }%>
        <li><a href="">Buscar</a></li>
        <li><a href="PalabrasClaveListar">Palabras Clave</a></li>
        <li><a href="Logout">Logout</a></li>
    </u1>

    <u2 class="navbar">
        <li><a href="ProductosListar?modo=0">Mis Productos</a></li>
        <li><a href="ProductosListar?modo=1">Todos los Productos</a></li>
        <li><a href="ProductosCrear">Subir Productos</a></li>
    </u2>

    <fieldset>
        Nombre: <%= producto.getNombre()%></br>
        Descripcion: <%= producto.getDescripcion()%></br>
        Precio: <%= producto.getPrecio().toString()%></br>
        Fecha: <%= format.format(producto.getFecha())%></br>
        Propietario: <%= producto.getPropietario().getNombre()%></br>
        Categoria: <%= categoria%></br>
        <% String str = "";
            
            for(PalabraClaveDTO pAux : producto.getPalabrasClave()){
                str += pAux.getPalabra() + " ";
            }
        %>
        Palabras clave: <%= str %></br>
    </fieldset>

    <h2>Imagen</h2><br/>
    <%
        if (producto.getImagen() == null || producto.getImagen().isEmpty()) {

    %>
    <p>Este producto no tiene imagen.</p>
    <%        } else {
        imagen = producto.getImagen();
    %>                
    <img src="<%= imagen%>"> 
    %>
    <%
        }
    %>
    <form method="" action="ProductosValorar">
        <input type="hidden" name="id" value="<%= productoId%>" />
        Comentario:<br/>
        <textarea id="comentario" maxlength="300" placeholder="Introduce un comentario (300 caracteres máximo)." name="textoComentario" rows="4" cols="50"></textarea><br/>
        <br/><br/>

        Valoración:
        <input type="radio" name="valoracion" value="1" />1
        <input type="radio" name="valoracion" value="2" />2
        <input type="radio" name="valoracion" value="3" />3
        <input type="radio" name="valoracion" value="4" />4
        <input type="radio" name="valoracion" value="5" />5
        <br/><br/>
        <button>Enviar valoración</button> 
        <a href="ProductosListar?modo=1">Volver</a>
        <p><%= status%></p> 
        <br/>
    </form>
    <%/*
    <form action="ProductosListar?modo=1">
        <button>Volver</button>
    </form>
         */%>

    <h2>Comentarios</h2>

    <%
        if (comentarios == null || comentarios.isEmpty()) {

    %>
    <p>En la actualidad no hay ningun comentario para este producto</p>
    <%        } else {
        for (ComentarioDTO com : comentarios) {
            System.out.println(com.getTexto());
        };
        for (ComentarioDTO com : comentarios) {
    %>
    <h3>Autor: <%=com.getAutor().getNombre()%>. Fecha: <%=format.format(com.getFecha())%></h3>
    <h3>Valoración: <%=com.getValoracion()%>/5</h3>
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

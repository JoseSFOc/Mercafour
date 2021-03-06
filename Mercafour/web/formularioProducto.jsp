<%-- 
    Document   : formularioProducto
    Created on : 01-abr-2020, 19:11:58
    Author     : josem
--%>

<%@page import="mercafour.dto.UsuarioDTO"%>
<%@page import="mercafour.dto.ProductoDTO"%>
<%@page import="mercafour.dto.PalabraClaveDTO"%>
<%@page import="mercafour.entity.PalabraClave"%>
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
        <title>Añadir Producto</title>
        <link rel="stylesheet" href="CSS/styleFormularioProducto.css">
    </head>

    <%
        String estado = (String)request.getAttribute("estadoSubirProducto");
        String idProducto = "", nombre = "", descripcion = "", precio = "";
        List<PalabraClaveDTO> palabraClave = new java.util.ArrayList();
        ProductoDTO producto = (ProductoDTO) request.getAttribute("producto");
        List<Usuario> listaUsuarios;
        List<Categoria> listaCategorias;
        UsuarioDTO user = ((Usuario) session.getAttribute("user")).getDTO();

        if (producto != null) {
            idProducto = producto.getProductoId().toString();
            nombre = producto.getNombre();
            descripcion = producto.getDescripcion();
            precio = producto.getPrecio().toString();
        }

        listaUsuarios = (List<Usuario>) request.getAttribute("listaUsuarios");
        listaCategorias = (List<Categoria>) request.getAttribute("listaCategorias");
    %>

    <body>
        <h1>Introduzca los datos del producto</h1>

    <u1 class="navbar">
        <% if (user.isIsAdmin()) {%><li><a href="menuAdministrador.jsp">Página de Administración</a></li><% } %>
        <li><a href="PalabrasClaveListar">Palabras Clave</a></li>
        <li><a href="Logout">Logout</a></li>
    </u1>

    <u2 class="navbar">
        <li><a href="ProductosListar?modo=0">Mis Productos</a></li>
        <li><a href="ProductosListar?modo=1">Todos los Productos</a></li>
    </u2>

    <form method="post" action="ProductosGuardar" name="formFormulario" >
        <input type="hidden" name="idProducto" value="<%= idProducto%>" />

        <div>Nombre: <input type="text" name="nombre" maxlength="20" value="<%= nombre%>"/></div></br>
        <div>Descripcion: </br>
            <textarea name="descripcion" rows="10" cols="30" /><%= descripcion%></textarea></div></br>
        <div>Precio: <input type="number" placeholder="1.00" step="0.01" min="0" max="1000000" name="precio" value="<%= precio%>"/></div></br>
        <div>Imagen: <input type="file" name="imagen" value="" accept="image/*"/></div></br>
        <div>Categoria: 
            <select name="categoria">
                <%
                    for (Categoria c : listaCategorias) {
                        String str = "";
                        if (c.getSupercategoria() != null) {
                            str = " - ";
                        }
                %>
                <option value="<%= c.getIdCategoria()%>"><%= str + c.getNombre()%></option>
                <%
                    }
                 System.out.println("HOLA");

                %>
            </select></div></br>        
        <% String str ="";
            if(producto!= null){
                for(PalabraClaveDTO p : producto.getPalabrasClave()){
                    str += p.getPalabra() + ", ";  
                    System.out.println(p.getPalabra());
                }
            }
        %>
            <div>Palabras clave: <textarea name="palabrasClave" rows="10" cols="30" /><%= str %></textarea></div> </br>
        <%
                if(estado == null){
                    estado = "";
                }
            %>
            <div> <%=estado%></br></div>  
            
            
        <input type="submit" name="Enviar" value="Enviar"/>
    </form>
             
</body>
</html>

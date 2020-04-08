<%-- 
    Document   : listaProductosDesc
    Created on : 29-mar-2020, 21:00:18
    Author     : Marco Hurtado
--%>

<%@page import="java.text.DateFormat"%>
<%@page import="mercafour.dto.ProductoDTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="mercafour.entity.Producto"%>
<%@page import="mercafour.entity.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="mercafour.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Últimos productos</title>
    </head>
    <%
        List<Usuario> listaUsuarios = (List)request.getAttribute("listaUsuarios");
        List<Categoria> listaCategorias = (List)request.getAttribute("listaCategorias");
        List<ProductoDTO> listaProductos = (List)request.getAttribute("listaProductos");

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        /*String str_filtro_supermercado = request.getParameter("filtro_supermercado");
        String str_filtro_supermercado = request.getParameter("filtro_supermercado");
        String str_filtro_nombre = request.getParameter("filtro_nombre");
        if (str_filtro_nombre == null) {
            str_filtro_nombre = "";
        }*/
    %>
    <body>
        <h1>Últimos productos</h1>
        <form action="ProductosListar">
            Buscar por: <br/><br/>
            Fecha
            <select name="filtro_dia">
                <option value="">-Todos-</option>
                <%
                    for(int i = 1 ; i <= 31; i++){
                %>
                <option value="<%=i%>"><%=i%></option>
                <%
                    }
                %>
            </select>
            /
            <select name="filtro_mes">
                <option value="">-Todos-</option>
                <%
                    for(int i = 1 ; i <= 12; i++){
                %>
                <option value="<%=i%>"><%=i%></option>
                <%
                    }
                %>
            </select>
            /
            <select name="filtro_anyo">
                <option value="">-Todos-</option>
                <%
                    for(int i = 2020 ; i > 2001 ; i--){
                %>
                <option value="<%=i%>"><%=i%></option>
                <%
                    }
                %>
            </select>
            <br/>
            <br/>
            Hora:
            <select name="filtro_anyo">
                <option value="">-Todos-</option>
                <%
                    for(int i = 0 ;  i< 24 ; i++){
                %>
                <option value="<%=i%>"><%=i%></option>
                <%
                    }
                %>
            </select>
            <br/>
            <br/>
            Categoría:
            <select name="filtro_categoria">
                <option value="">-Todas las categorías-</option>
                <%
                    for(int i = 0 ;  i< 1 ; i++){
                %>
                <option value="<%=i%>"><%=i%></option>
                <%
                    }
                %>
            </select>
            Título: <input type="text" name="filtro_nombre" value="Título" />
            <br/>
            <br/>
            Búsqueda libre: <input type="text" name="filtro_palabras_clave" value="Búsqueda" />              
            <br/>
            <input type="submit" value="Filtrar" />
        </form>
        <form action="ProductosListarResetear">
            <button>Resetear filtros</button>
        </form>
            <br/>
            <br/>
            <h2>Productos encontrados</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Fecha</th>
                        <th>Imagen</th>
                        <th>Propietario</th>
                        <th>Categoría</th>
                        <%
                            Usuario user = (Usuario)session.getAttribute("user");
                            if(user != null && user.getAdministrador()){ //si el usuario es administrador aparecen las columnas de borrar y editar
                        %>
                        <th>Borrar</th>
                        <th>Modificar</th>
                        <%
                            }
                        %>
                        
                        
                    </tr>
                </thead>
                <tbody>
                    <%
                        for(ProductoDTO p: listaProductos){
                            
                    %>
                    <tr>
                        <td><%=p.getNombre()%></td>
                        <td><%=p.getPrecio()%></td>
                        <td><%=DateFormat.getInstance().format(p.getFecha())%></td>
                        <td><%=p.getImagen()%></td>
                        <td><%=p.getPropietario().getNombre()%></td>
                        <td><%=p.getCategoria()%></td>
                        <td><a href="ProductosVer?id=<%= p.getProductoId() %>">Ver producto</a></td>
                        <%
                            if(user != null && user.getAdministrador()){ //Si el usuario es administrador, se le añaden dos columnas para editar y borrar los productos listados
                        %>
                           <td><a href="ProductosBorrar?id=<%= p.getProductoId()%>">Borrar</a></td> 
                           <td><a href="ProductosEditar?id=<%= p.getProductoId()%>">Editar</a></td> 
                        <%
                            }
                        %>
                        
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>

    </body>
</html>

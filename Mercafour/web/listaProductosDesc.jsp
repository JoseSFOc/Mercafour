<%-- 
    Document   : listaProductosDesc
    Created on : 29-mar-2020, 21:00:18
    Author     : Marco Hurtado
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Últimos productos</title>
    </head>
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
                    </tr>
                </thead>
                <tbody>
                    <%
                        //for(int j = 0 ; j<5; j++){
                            
                    %>
                    <tr>
                        <td><%=1%></td>
                        <td><%=2%></td>
                        <td><%=2%></td>
                        <td><%=2%></td>
                        <td><%=2%></td>
                        <td><%=2%></td>
                    </tr>
                    <%
                       // }
                    %>
                </tbody>
            </table>

    </body>
</html>

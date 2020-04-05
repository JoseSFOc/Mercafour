<%-- 
    Document   : listaUsuarios
    Created on : 05-abr-2020, 11:59:10
    Author     : Adrián Laguna
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de usuarios</title>
    </head>
    <body>
        <h1>Usuarios</h1>
        <form action="UsuariosListar">
            Buscar por: <br/><br/>
            Administrador:
            <select name="filtro_administrador">
                <option value="">-Administador o no-</option>
                <%
                    for(int i = 0 ;  i< 1 ; i++){
                %>
                <option value="<%=i%>"><%=i%></option>
                <%
                    }
                %>
            </select>
            <br/>
            <br/>
            Username: 
            <input type="text" name="filtro_username"/>
            <br/>
            <br/>
            Nombre: 
            <input type="text" name="filtro_usuario_nombre"/>
            <br/>
            <br/>
            Email: 
            <input type="text" name="filtro_email"/>
            <br/>
            <br/>
            Búsqueda libre: <input type="text" name="filtro_palabras_clave" value="Búsqueda" />              
            <input type="submit" value="Filtrar" />
        </form>
        <form action="ProductosListarResetear">
            <button>Resetear filtros</button>
        </form>
            <br/>
            <br/>
            <h2>Usuarios encontrados</h2>
            <table border="1">
                <thead>
                    <tr>
                        <th>ID Usuario</th>
                        <th>Administrador</th>
                        <th>Username</th>
                        <th>Contrase&ntilde;a</th>
                        <th>Nombre</th>
                        <th>Email</th>
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
                        <td><button><a href="">Modificar</button></td>
                        <td><button><a href="">Borrar</button></td>
                    </tr>
                    <%
                       // }
                    %>
                </tbody>
            </table>

    </body>
</html>

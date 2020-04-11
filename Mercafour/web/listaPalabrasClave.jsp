<%-- 
    Document   : listaPalabrasClave
    Created on : 11-abr-2020, 12:51:29
    Author     : aleja
--%>

<%@page import="mercafour.entity.PalabraClave"%>
<%@page import="java.util.List"%>
<%@page import="mercafour.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario user = (Usuario)session.getAttribute("user");
    if(user == null){
        response.sendRedirect("login.jsp");
    }
    
    List<PalabraClave> palabrasClave = (List<PalabraClave>)request.getAttribute("palabrasClave");
    

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Palabras Clave</title>
    </head>
    <body>
        <h1>Listado de Palabras Clave</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Palabra</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for(PalabraClave p : palabrasClave){
                        
                %>
                <tr>
                    <td><%= p.getIdPalabraClave() %></td>
                    <td><%= p.getPalabra() %></td>
                    <%
                        if(user.getAdministrador()){
                    %>
                    <td><a href="PalabraClaveBorrar?idPalabra=<%= p.getIdPalabraClave()%>">Borrar</a>
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

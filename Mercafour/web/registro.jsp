<%-- 
    Document   : registro
    Created on : 26-mar-2020, 11:33:36
    Author     : aleja
--%>

<%@page import="mercafour.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario user = (Usuario)session.getAttribute("user");
    String status = (String)request.getAttribute("status");
    if(user != null){
        if(user.getAdministrador()){
            response.sendRedirect("menuAdministrador.jsp"); //Pagina proncipal del admin
        }else{
            response.sendRedirect("menuProductoVendedor.jsp"); //Pagina proncipal del usuario
        }
    }
    
    if(status == null){
        status = "";
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Usuario</title>
    </head>
    <body>
        <h1>Registro</h1>
        <form action="Registro" method="post">
            Email <input type="text" name="email"/><br/>
            Nombre de usuario <input type="text" name="username"/><br/>
            Nombre <input type="text" name="nombre"/><br/>
            Contrase&ntilde;a <input type="password" name="password"/><br/>
            <input type="submit" value="Registrarse"/>
            <a href="login.jsp">Cancelar</a>
        </form>
        <p><%=status%></p><br/>
        <p>Nota: Para poder registrarse es necesario proporcionar un email y una contrase&ntilde;a</p>
    </body>
</html>

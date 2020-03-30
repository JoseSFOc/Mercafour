<%-- 
    Document   : registro
    Created on : 26-mar-2020, 11:33:36
    Author     : aleja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Usuario</title>
    </head>
    <body>
        <h1>Registro</h1>
        <form action="" method="post">
            Email <input type="text" name="email"/><br/>
            Nombre de usuario <input type="text" name="username"/><br/>
            Nombre <input type="text" name="nombre"/><br/>
            Contrase&ntilde;a <input type="password" nombre="password"/><br/>
            <input type="submit" value="Registrarse"/>
            <a href="login.jsp">Cancelar</a>
        </form>
    </body>
</html>

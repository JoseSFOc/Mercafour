<%-- 
    Document   : login
    Created on : 26-mar-2020, 11:20:53
    Author     : aleja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mercafour: Inicio de Sesion</title>
    </head>
    <body>
        <h1>Mercafour</h1>
        <h2>Inicio de sesi&oacute;n</h2>
        <form action="" method="post">
            Usuario <input type="text" name="usuario"/><br/>
            Contrase&ntilde;a <input type="password" name="password"/><br/>
            <input type="submit" value="Iniciar Sesión"/>
        </form>
        <a href="registro.jsp">Registrarse</a>
    </body>
</html>

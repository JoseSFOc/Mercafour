<%-- 
    Document   : producto
    Created on : 29-mar-2020, 21:06:22
    Author     : Marco Hurtado
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Producto</title>
    </head>
    <body>
        <h1>Página del producto</h1>
        <form method="" action="comentario">
            Comentario:
            <textarea id="comentario" rows="4" cols="50">
            Introduce un comentario (300 caracteres máximo).
            </textarea>
            <br/>
            <button>"Enviar comentario" </button>
        </form>
        <form method="" action="valoracion">
            Valoración:
            <input type="radio" name="valoracion" value="1" />1
            <input type="radio" name="valoracion" value="2" />2
            <input type="radio" name="valoracion" value="3" />3
            <input type="radio" name="valoracion" value="4" />4
            <input type="radio" name="valoracion" value="5" />5
            <br/>
            <button>"Enviar valoración" </button>
        </form>
    </body>
</html>

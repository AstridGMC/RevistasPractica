<%-- 
    Document   : Consultas
    Created on : 5/10/2019, 12:02:42 PM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <link href= "<%=request.getContextPath()%>/bootstrap/css/estiloConsultas.css" rel="stylesheet" type ="text/css">
        <title>mis Consultas</title>
    </head>
    <body>
        <%@include  file= "headerPrincipalEditor.jsp"%>
        <div style="padding-top: 320px; padding-left: 100px;">
            <form action="<%=request.getContextPath()%>/MisConsultas" method="POST">
                <div style="display: inline-block;">
                    <select  name="consulta" class="select-css">
                        <option>opciones</option>
                        <option value="Ganancias">Mis Ganancias
                        <option value="Comentarios">Comentarios
                        <option value="Revistas Mas Gustadas">Revistas Favoritas
                        <option value="Suscripciones">Suscripciones
                    </select>
                </div>
                <div id="botonDiv" style="display: inline-block;">
                <input id="boton" type="submit" value="buscar" >
                </div>
            </form>
        </div>
    </body>
</html>

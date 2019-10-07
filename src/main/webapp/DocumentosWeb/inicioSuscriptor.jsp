<%-- 
    Document   : inicioSuscriptor
    Created on : 29/09/2019, 11:52:48 PM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/estiloInicio.css" rel="stylesheet" type ="text/css">
        <title>Bienvenido Suscriptor</title>
    </head>
    <body style= " background-color: white">
        
        <%@include  file= "headerPrincipalSuscriptor.jsp"%>
                  }
            if (session.getAttribute("rango") == "Administrador") {

            }%>
        <div  style=" align-content: center; "  id="seccion" class="contenido">
            <div class="card" style="width: 100%;">
                <img src="../DocumentosWeb/imagenes/leer.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">BIENVENIDO Suscriptor!!</h5>
                    <p class="card-text">ahora tienes acceso a diferentes revistas de diferentes temas para ampliar tus conocimientos!</p>
                    <br>
                    <br>
                </div>
            </div>
        </div>
    </body>
</html>

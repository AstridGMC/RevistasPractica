<%-- 
    Document   : Perfil
    Created on : 29/09/2019, 05:08:39 PM
    Author     : astridmc
--%>

<%@page import="revistaspractica.Backend.Perfil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/estiloInicio.css" rel="stylesheet" type ="text/css">
        <title>Ver Perfil</title>
    </head>
    <body>
        <%
            System.out.println("mi rango es " + session.getAttribute("rango"));
            if ("Editor".equals(session.getAttribute("rango"))) {
                     System.out.println("se incluye rango " + session.getAttribute("rango"));%>
        <%@include  file= "headerPrincipalEditor.jsp"%>
        <% } else if ("Suscriptor".equals(session.getAttribute("rango"))) {%>
        <%@include  file= "headerPrincipalSuscriptor.jsp"%>
        <%  } else if (session.getAttribute("rango").equals("Administrador")) {%>
                <%@include  file= "headerAdministrador.jsp"%>
            <%}%>
       
            
            <div  style=" align-content: center; padding-top: 350px; padding-left: 200px;"  id="seccion" class="contenido">
            <%Perfil perfilEscritor = (Perfil) request.getAttribute("perfilEscritor");
            request.setAttribute("cuiEscritor", perfilEscritor.getCui());
            System.out.println(perfilEscritor.getCui());
            request.setAttribute("imagen", "imagen");
                String nombreEscritor = (String) request.getAttribute("nombreEscritor");%>
            
                
                <div class="card" style="width: 80rem;">
                <img  src="<%=request.getContextPath()%>/foto?cuiEscritor=<%=perfilEscritor.getCui()%>" alt="Mi imagen" id="IMG" width="300" height="320">
                <div class="card-body">
                    <h2 class="card-title"> <%=nombreEscritor%> </h2>
                    <p class="card-text">Descripcion: <%=perfilEscritor.getDescripcion()%></p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Gustos: <%= perfilEscritor.getGustos()%></li>
                    <li class="list-group-item">Hobbies: <%= perfilEscritor.getHobbies()%></li>
                    <li class="list-group-item">Intereses: <%= perfilEscritor.getIntereses()%></li>
                </ul>
                <div class="card-body">
                    <a href="#" class="card-link">Ver todas las Revistas</a>
                </div>
            </div>
        </div>
    </body>
</html>

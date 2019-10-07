<%-- 
    Document   : verRevista
    Created on : 28/09/2019, 11:57:20 PM
    Author     : astridmc
--%>

<%@page import="revistaspractica.Backend.Revista"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Revistas</title>
    </head>
    <body>
        <%
            System.out.println("mi rango es " + session.getAttribute("rango"));
            if ("Editor".equals(session.getAttribute("rango"))) {
                System.out.println("se incluye rango " + session.getAttribute("rango"));%>
        <%@include  file= "headerPrincipalEditor.jsp"%>
        <% } else if ("Suscriptor".equals(session.getAttribute("rango"))) {%>
        <%@include  file= "headerPrincipalSuscriptor.jsp"%>
        <%}%>
        <div style="padding-top: 320px;">
            <table action="../SubirMisRevistas" method="POST" class="table">
                <thead>
                    <tr>
                        <th scope="col">Nombre</th>
                        <th scope="col">Escritor</th>
                        <th scope="col">Precio</th>
                        <th scope="col">Descripcion</th>
                            <%if ("Suscriptor".equals(session.getAttribute("rango"))) {%>
                        <th scope="col">Suscribirme</th>
                            <%}%>
                    </tr>
                </thead>

                <%
                    
                    ArrayList<Revista> revistas = (ArrayList) request.getAttribute("revistas");
                    System.out.println(revistas.size());
                    for (int i = 0; i < revistas.size(); i++) {
                        Revista revista = revistas.get(i);
                        int idRevista = revista.getRevistaID();
                        System.out.println(revista.getRevistaID() + "kkk");
                %>
                <tr> 

                    <td name = "nombre" > <%=revista.getNombre()%> </td>
                    <td name = "escritor" > <a href="<%=request.getContextPath()%>/perfilVista?cuiEscritor=<%=revista.getCuiUsuario()%>"><%=revista.getEscritor()%> </a></td>
                    <td name = "precio" > <%=revista.getCuotaSuscripcion()%> </td>
                    <td name = "descripcion" > <%=revista.getDescripcion()%> </td>
                    <%if ("Suscriptor".equals(session.getAttribute("rango")) && request.getAttribute("suscrito") == "no") {%>
                    <td name = "suscribir" ><div class="alert alert-success alert-dismissable" id="myAlert2"><input type="button" id="botonWindowOpen" onClick= 'window.open("<%=request.getContextPath()%>/suscripcionNueva?suscripcion=<%=revista.getRevistaID()%>", "MsgWindow", "width=550, height=700, top=100,left=500");'  value="SUSCRIBIR"></div></td>
                            <%} else if (request.getAttribute("suscrito") == "si") {%>
                    <td name = "suscribir" ><div class="alert alert-success alert-dismissable" id="myAlert2"><input type="button" id="botonWindowOpen" onClick= 'window.open("<%=request.getContextPath()%>/LecturaRevista?lectura=<%=revista.getRevistaID()%>", "MsgWindow", "width=550, height=700, top=100,left=500");'  value="LEER"></div></td>
                            <%}%>
                </tr>
                <%}%>



            </table>
        </div>
    </body>
</html>

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
        <title>JSP Page</title>
    </head>
    <body>
        <%
            System.out.println("mi rango es " + session.getAttribute("rango"));
            if ("Editor".equals(session.getAttribute("rango"))) {
                System.out.println("se incluye rango " + session.getAttribute("rango"));%>
        <%@include  file= "headerPrincipalEditor.jsp"%>
        <% } else if ("Suscriptor".equals(session.getAttribute("rango"))) {%>
        <%@include  file= "headerPrincipalSuscriptor.jsp"%>
        <%            }
            if (session.getAttribute("rango") == "Administrador") {

            }%>
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
                    for (int i = 0; i < revistas.size(); i++) {
                        Revista revista = revistas.get(i);
                %>
                <tr> 

                    <td name = "nombre" > <%=revista.getNombre()%> </td>
                    <td name = "escritor" > <a href="<%=request.getContextPath()%>/perfilVista?cuiEscritor=<%=revista.getCuiUsuario()%>"><%=revista.getEscritor()%> </a></td>
                    <td name = "precio" > <%=revista.getCuotaSuscripcion()%> </td>
                    <td name = "descripcion" > <%=revista.getDescripcion()%> </td>
                    <%if ("Suscriptor".equals(session.getAttribute("rango"))) {%>
                    <td name = "suscribir" ><div class="alert alert-success alert-dismissable" id="myAlert2"><input type="button" id="botonWindowOpen" onClick="window_open();"value="SUSCRIBIR"></div></td>
                        <%}
                        %>

                </tr>
                <%}%>
                <script>
                    var miVentana;

                    //La función window_open crea el pop-up o ventana emergente
                    function window_open() {
                        miVentana = window.open("DocumentosWeb/LogIn.jsp", "nombrePop-Up", "width=700,height=700, top=40,left=50");
                    }

                    //La función window_close cerrara el pop-up o ventana emergente
                    function window_close() {
                        miVentana.close();
                    }

                    
                </script>


            </table>
        </div>
    </body>
</html>

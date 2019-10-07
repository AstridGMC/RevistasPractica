<%-- 
    Document   : asignarCostosRevistas
    Created on : 6/10/2019, 01:01:19 AM
    Author     : astridmc
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="revistaspractica.Backend.Revista"%>
<%@page import="revistaspractica.Backend.Revista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Asignar Costos</title>
    </head>
    <body>
        <%@include  file= "headerAdministrador.jsp"%>
         <div style="padding-top: 320px;">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Nombre</th>
                        <th scope="col">Escritor</th>
                        <th scope="col">Precio</th>
                        <th scope="col">Costo por Dia</th>
                    </tr>
                </thead>

                <%
                    ArrayList<Revista> revistas = (ArrayList) request.getAttribute("revistasSinCosto");
                    for (int i = 0; i < revistas.size(); i++) {
                        Revista revista = revistas.get(i);
                        int idRevista = revista.getRevistaID();
                        System.out.println(revista.getRevistaID());
                %>
                <tr> 

                    <td name = "nombre" > <%=revista.getNombre()%> </td>
                    <td name = "escritor" > <a href="<%=request.getContextPath()%>/perfilVista?cuiEscritor=<%=revista.getCuiUsuario()%>"><%=revista.getEscritor()%> </a></td>
                    <td name = "precio" > <%=revista.getCuotaSuscripcion()%> </td>
                    <td name = "suscribir" ><form action='<%=request.getContextPath()%>/CostosAdmin' method='POST'><div class="alert alert-success alert-dismissable">
                                <input name='idRevista' value='<%=revista.getRevistaID()%>' style="display:none" >
                                <input type="text" name='costo'><input type="submit"  value="Guardar"></div></form></td>
                           
                   
                </tr>
                <%}%>



            </table>
        </div>
    </body>
</html>

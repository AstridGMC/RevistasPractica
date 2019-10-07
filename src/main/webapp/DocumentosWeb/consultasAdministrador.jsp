<%-- 
    Document   : consulatasAdministrador
    Created on : 6/10/2019, 01:22:38 PM
    Author     : astridmc
--%>

<%@page import="revistaspractica.Backend.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="revistaspractica.Backend.Revista"%>
<%@page import="revistaspractica.Backend.Revista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Consultas</title>
    </head>
    <body>
        <%@include  file= "headerAdministrador.jsp"%>
        <div style="padding-top: 320px;">
            <form action="<%=request.getContextPath()%>/ReportesAdmin" method="POST">
                
            <div>
                <div class="form-group" id="div1" style="display: inline-block;">
                    <label class="titulos" id="as">Fecha inicial </label>
                    <div class="col-sm-10">
                        <input class="fechas" type="date" name="fecha1" size="20" required>
                    </div>
                </div>
                <div class="form-group" id="div1" style="display: inline-block;">
                    <label class="titulos" id="as">Fecha final </label>
                    <div class="col-sm-10">
                        <input class="fechas" type="date" name="fecha2" size="20" required>
                    </div>
                </div>
                <input type="text" name ="tipo" value="<%=request.getAttribute("tipo")%>" style="display: none;">
                <input type="submit" value="Buscar">
            </div>
                </form>
            <div>
                <table class="table">
                    <thead>
                        <tr>
                            <%String[] columnas = (String[]) request.getAttribute("columnas");
                                for (int i = 0; i < columnas.length; i++) {
                                    String columna = columnas[i];
                                    System.out.println(columna);
                            %>
                            <th scope="col"><%= columna%></th>
                                <%}%>
                        </tr>
                    </thead>

                    <%
                        ArrayList<Revista> revistas = (ArrayList) request.getAttribute("revistas");
                        for (int i = 0; i < revistas.size(); i++) {
                            Revista revista = revistas.get(i);
                            int idRevista = revista.getRevistaID();
                            System.out.println(revista.getRevistaID());
                    %>
                    <tr> 

                        <%System.out.println("entrandoAqui");
                            if (request.getAttribute("tipo").equals("populares")) {%>
                        <td> <%=revista.getNombre()%> </td>
                            <td> <a href="<%=request.getContextPath()%>/perfilVista?cuiEscritor=<%=revista.getCuiUsuario()%>"><%=revista.getEscritor()%> </a></td>
                            <td> <ol>
                                    <%if (request.getAttribute("tipo").equals("populares")) {
                                        ArrayList<Usuario> usuarios = revista.getSuscriptores();
                                        for (int j = 0; j < usuarios.size(); j++) {
                                            Usuario usuario = new Usuario();
                                            usuario = usuarios.get(j);
                                    %> <li><%=usuario.getNombre()%></li> <%}}%>
                                </ol>
                            </td>

                        <%}if (request.getAttribute("tipo").equals("comentario")) {%>
                            <td > <%=revista.getNombre()%> </td>
                            <td  > <a href="<%=request.getContextPath()%>/perfilVista?cuiEscritor=<%=revista.getCuiUsuario()%>"><%=revista.getEscritor()%> </a></td>
                            <td  > <ol>
                                    <%if (request.getAttribute("tipo").equals("comentario")) {
                                        System.out.println("entrando segundo");
                                        ArrayList<String> comentatrios = revista.getComentarios();
                                        for (int j = 0; j < comentatrios.size(); j++) {
                                    %> 
                                    <li><%=comentatrios.get(j)%></li> 
                                    <%}}%>
                                </ol>
                            </td>
                        <%}%>
                    </tr>
                    <%}%>
                    
                </table>
            </div>
        </div>
    </body>
</html>

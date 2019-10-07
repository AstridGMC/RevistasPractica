     
<%@page import="java.util.ArrayList"%>
<%@page import="revistaspractica.Backend.Revista"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Revistas</title>
    </head>
    <body>
        <%@include  file= "headerPrincipalEditor.jsp"%>
        <div style="padding-top: 320px;">
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
                    ArrayList<Revista> revistas = (ArrayList) request.getAttribute("revista");
                    for (int i = 0; i < revistas.size(); i++) {
                        Revista revista = revistas.get(i);
                        int idRevista = revista.getRevistaID();
                        System.out.println(revista.getRevistaID());
                %>
                <tr> 

                    <%if (request.getAttribute("consulta").equals("Comentarios")) {%>
                    <td > <%=revista.getNombre()%> </td>
                    <td  > <a href="<%=request.getContextPath()%>/perfilVista?cuiEscritor=<%=revista.getCuiUsuario()%>"><%=revista.getEscritor()%> </a></td>
                    <td  > <%=revista.getComentario()%> </td>
                    <%} else if (request.getAttribute("consulta").equals("Revistas Mas Gustadas")) {%>
                    <td > <%=revista.getNombre()%> </td>
                    <td  > <%=revista.getDescripcion()%> </td>
                    <td  > <%=revista.getLikes()%> </td>
                    <%} else if (request.getAttribute("consulta").equals("Suscripciones")) {%>
                    <td > <%=revista.getNombre()%> </td>
                    <td  > <a href="<%=request.getContextPath()%>/perfilVista?cuiEscritor=<%=revista.getCuiUsuario()%>"><%=revista.getEscritor()%> </a></td>
                    <td  > <%=revista.getFecha()%> </td>
                    <%}%>
                </tr>
                <%}%>
            </table>
        </div>
    </body>
</html>

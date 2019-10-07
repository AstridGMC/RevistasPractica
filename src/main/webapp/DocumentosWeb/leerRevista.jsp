<%-- 
    Document   : leerRevista
    Created on : 2/10/2019, 11:45:39 PM
    Author     : astridmc
--%>

<%@page import="Servlet.inicioSesion"%>
<%@page import="revistaspractica.Backend.Suscriptor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="revistaspractica.Backend.Revista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/estiloInicio.css" rel="stylesheet" type ="text/css">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/estiloPerfil.css" rel="stylesheet" type ="text/css">
        <title>leer Revista</title>
    </head>
    <body>
        <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
            <embed src="<%=request.getContextPath()%>/RevistaLeer?lectura=<%=request.getAttribute("lectura")%>" type="application/pdf" width="100%" height=750px" />
        </div>

        <div style=" padding-left: 100px;  width: 800px;" >
            <h1>COMENTARIOS</h1>
            <ul class="list-group list-group-flush">
                <%
                    int idRevista = (int) request.getAttribute("lectura");
                    Suscriptor suscriptor = new Suscriptor();
                    ArrayList<Revista> comentario = (ArrayList) request.getAttribute("Comentarios");
                    for (int i = 0; i < comentario.size(); i++) {
                        Revista revista = comentario.get(i);
                        System.out.println(i + revista.getComentario());

                %>

                <li class="list-group-item"><%=revista.getEscritor()%> : <%=revista.getComentario()%>  </li>
                    <%}%>
            </ul>
            <input  id="meGusta" type="button"  style=" width: 100px; height: 43px; border-radius: 40px; background-image:url(<%=request.getContextPath()%>/DocumentosWeb/imagenes/meGusta.png);">
            <input  id="noMeGusta" type="button" style=" background-color: #e4b9b9; width: 95px; height: 43px; border-radius: 40px;display: none; background-image:url(<%=request.getContextPath()%>/DocumentosWeb/imagenes/noMeGusta.png);"  >
        </div>  

        <form action="<%=request.getContextPath()%>/RevistaLeer" method="POST">
            <div style="align-content: center; padding-left: 100px; padding-bottom: 200px;">
                <h1>Deja Tu Comentario</h1>
                <textarea required width="800px" name="miComentario" class="form-control" placeholder ="Que te paece la revista?"></textarea>
                <input type="text" name="idRevista" style="display: none;" value="<%=request.getAttribute("lectura")%>">
                <input style="align-self: center; " type="submit" value="Publicar">
            </div>
        </form>
    </body>

    <script>


        var noMeGusta = document.getElementById('noMeGusta');
        var meGusta = document.getElementById('meGusta');

        var numero = 1;
        meGusta.onclick = function (e) {
           
            document.getElementById('noMeGusta').style.display = 'inline';
            document.getElementById('meGusta').style.display = 'none';
             <%System.out.println("meGusta");
            suscriptor.DarMeGusta(inicioSesion.conexion, idRevista);
            %>
        };

        noMeGusta.onclick = function (e) {
            <%System.out.println("noMegusta");
            suscriptor.DarNoMeGusta(inicioSesion.conexion, idRevista);%>
            document.getElementById('meGusta').style.display = 'inline';
            document.getElementById('noMeGusta').style.display = 'none';

        };

    </script>
</html>

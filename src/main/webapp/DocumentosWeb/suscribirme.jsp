<%-- 
    Document   : suscribirme
    Created on : 30/09/2019, 11:26:49 AM
    Author     : astridmc
--%>

<%@page import="revistaspractica.Backend.Revista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">


        <title>Suscripcion</title>
    </head>
    <body>
        <div class="container"  id="myAlert">
            <section>
                <script>

                    function alerta() {
                        alert("Felicidades, la suscripcion se ha llevado a cabo satisfactoriamente");
                    }

                    function cerrar() {
                        setTimeout(function () {
                            window.close();
                        }, 200); //Dejara un tiempo de 3 seg para que el usuario vea que se envio el formulario correctamente
                    }
                    if (<%=request.getAttribute("mensaje")%> === "Felicidades, la suscripcion se ha llevado a cabo satisfactoriamente") {
                        window.close();
                        <%request.setAttribute("mensaje", null);
                        System.out.println("esta entrandi segundo");%>
                    }
                </script>

                <div  style=" align-content: center; padding-top: 30px; padding-left: 30x;"  id="seccion" class="contenido">
                    <%Revista revista = (Revista) request.getAttribute("revistaASuscribir");%>
                    <div class="card" style="width: 50rem;">
                        <form action="<%=request.getContextPath()%>/suscripcionNueva" method="POST" id="formulario">
                            <div style="padding-top: 10px; padding-left: 100px;">
                                <img src="<%=request.getContextPath()%><%=request.getAttribute("path")%> "  alt="Mi imagen" id="IMG" width="300" height="360">
                            </div>
                            <div class="card-body">
                                <h2 class="card-title"> <%=revista.getNombre()%> </h2>
                            </div>
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">Descripcion: <%=revista.getDescripcion()%></li>
                                <li class="list-group-item">Escritor: <%=revista.getEscritor()%></li>
                                <li class="list-group-item">PrecioSuscripcion:  Q.<%=revista.getCuotaSuscripcion()%>.00</li>
                                <li class="list-group-item">Categoria: <%=revista.getCategoria()%></li>
                            </ul>
                            <div class="form-group" id="div1">
                                <label class="titulos" id="as">Fecha de Suscripcion </label>
                                <div class="col-sm-10">
                                    <input class="fechas" type="date" name="miFecha" size="20" required>
                                    <input type="text" name="idRevista" style="display: none;" value="<%=revista.getRevistaID()%>">

                                </div>
                            </div>
                            <input onclick="alerta(); cerrar(); window.close();"  type="submit" name="accion" id="btnGuardar" value="SUSCRIBIR">
                        </form>
                        <br>
                        <br>
                    </div>
                </div>

            </section>
        </div>

    </body>
</html>

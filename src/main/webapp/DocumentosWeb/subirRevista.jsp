<%-- 
    Document   : subirRebista
    Created on : 24/09/2019, 06:39:23 PM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "../bootstrap/css/estiloSubirRevista.css" rel="stylesheet" type ="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <% //si ls respuesta de mi servlet es diferente a vacio
            if (session.getAttribute("error") != null) {  //tendrìa que mandar a imprimir por medio del alert de abajo el mensaje de acceso correcto o incorrecto, pero no me lo muestra
        %>          
        <script> alert('la revista se ha guardado con exito');</script>
        <%
            }
            session.setAttribute("error", null);
        %>

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
        <div id="seccion" class="contenido">
            <center><h2>Agrega una nueva revista</h2></center>

            <section>
                <form action="../SubirMisRevistas" method="POST" enctype="multipart/form-data" id="formulario">
                    <div class="form-group" id="div1">
                        <label class="titulos" id="as">Ingresa tu revista en formato pdf </label>
                        <div>
                            <input id="docBtn" type="file" name="fileDoc" accept="application/pdf" class="input-file" required>
                        </div>
                        <div id="file-preview-zone">
                        </div>
                        <div class="form-group">
                            <div class="form-group" id="div1">
                                <label class="col-sm-2 control-label">Nombre de La Revista</label>nombre
                                <div class="col-sm-10">
                                    <input type="text" class="form-control" name="nombreRevista" required>
                                </div>
                            </div>
                            <br>
                            <div id="div1">
                                <label class="titulos" id="as">Descripcion </label>
                                <div class="col-sm-10">
                                    <textarea class="form-control" rows="2" name="descripcion" placeholder ="danos una breve descripcion de la revista" required></textarea>
                                </div>
                            </div>
                            <br>
                            <div class="form-group" id="div1">
                                <label class="titulos" id="as">Fecha de Creacion </label>
                                <div class="col-sm-10">
                                    <input class="fechas" type="date" name="fecha" size="20" required>
                                </div>
                            </div>
                            <div class="form-group" id="div4">
                                <div class="col-sm-10">
                                    Precio de Suscripcion  <input id="precio" type="number" name="precio" pattern=".{13}" required >
                                </div>
                                <input type="submit" name="accion" id="btnGuardar" value="GUARDAR">
                            </div>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </body>

    <script>
        function readFile(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    var filePreview = document.createElement('img-doc');
                    filePreview.id = 'file-preview';
                    //e.target.result contents the base64 data from the image uploaded
                    filePreview.src = e.target.result;
                    var previewZone = document.getElementById('file-preview-zone');
                    previewZone.appendChild(filePreview);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }

        var fileUpload = document.getElementById('docBtn');
        fileUpload.onchange = function (e) {
            readFile(e.srcElement);
        }
        var cancelar = document.getElementById('btnGuardar');
        cancelar.onclick = function (e) {
        }


    </script>

</html>

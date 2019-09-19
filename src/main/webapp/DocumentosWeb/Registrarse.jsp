<%-- 
    Document   : Registrarse
    Created on : 18/09/2019, 11:13:16 AM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "../bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <link href = "../bootstrap/css/estiloRegistrarse.css" rel="stylesheet" type ="text/css">
        <title>Registrarse en la Pagina</title>
    </head>
    <body  background = "imagenes/rev2.jpg">
        <div id="cuadro">
            <div id="superior">
                <h1 id ="titulo">CREA TU CUENTA</h1>
                <h3 id = "titulo2">Completamente Gratis!!</h3>
            </div>

            <div id = "centro"></div>
            <div id="inferior">
                <form method ="POST" action ="iniciarSesion"  class="form-horizontal">
                    <div class="form-group" id="div0">
                        <label class="col-sm-2 control-label" id="cui">CUI </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="cui" id="nombre" placeholder ="Numero de cui (13 digitos)">
                        </div>
                    </div>
                    <div class="form-group" id="div1">
                        <label class="col-sm-2 control-label" id="as">Nombres </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="nombres" id="nombre" placeholder ="Nombres completos">
                        </div>
                    </div>
                    <div class="form-group" id="div2">
                        <label class="col-sm-2 control-label">Apellidos </label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="apellidos" id="apellidos" placeholder ="Apellidos completos">
                        </div>
                    </div>
                    <div class="form-group" id="div3">
                        <label id="user" class="col-sm-2 control-label">Nombre de Usuario</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="usuario" id="usuario" placeholder ="Nombre De Usuario">
                        </div>
                    </div>
                    <div class="form-group" id="div4">
                        <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10" >
                            <input type="password" name ="password" class="form-control" id="url" placeholder="Password">
                        </div>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="rango" id="optionsRadios1" value="Editor" checked>
                            Editor
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="rango" id="optionsRadios2" value="Suscriptor">
                            Suscriptor
                        </label>
                    </div

                    <div class="form-group">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-success pull-right" id="registrarse">Registrarme</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>

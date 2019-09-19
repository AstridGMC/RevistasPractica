
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "../bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <link href = "../bootstrap/css/estiloLogIn.css" rel="stylesheet" type ="text/css">
        <title>Mis Revistas: Inicio de Sesion </title>
    </head>
    <body background = "imagenes/fondo1.jpg">

        <div id="cuadro">
            <% /*HttpSession sesion = request.getSession(); */%>
            <div id="superior">
                <h1>Iniciar Secion</h1>
            </div>
            <div id="inferior">
                <form method ="POST" action ="../iniciarSesion"  class="form-horizontal">
                    <div class="form-group" id="div1">
                        <label class="col-sm-2 control-label">Usuario</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="usuario" id="nombre" value ="">
                        </div>
                    </div>
                    <div class="form-group" id="div2">
                        <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10" >
                            <input type="password" name ="password" class="form-control" id="url" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-success pull-right" id="acceder">Acceder</button>
                        </div>
                    </div>
                </form>
                <div id="registrarse">
                    <h4 id="h4">Aun no estas registrado?</h4>
                    <a id ="enlace1">registrarse</a>
                </div>
            </div>
            <div id="wrapperbottom"></div>
            <%/*
                String error = (String) sesion.getAttribute("error");
                if (error != null) {
                
                <div style="border-color: red; color: white;"> 
                <%=error//?%//>
            </div>*/
            %>
            
            <%
                /*
                }*/
            %>


        </div>
    </body>
</html>

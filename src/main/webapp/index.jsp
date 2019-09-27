<%-- 
    Document   : index
    Created on : 15/09/2019, 02:32:06 PM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <link href = "bootstrap/css/estiloPrincipal.css" rel="stylesheet" type ="text/css">
        <title>Sitio de Revistas</title>
    </head>
    
    <body background = "DocumentosWeb/imagenes/fndo2.jpg">
        <header id="header">
        <div class="container" id="contenedor" style="background-image: url('DocumentosWeb/imagenes/12.jpg')">
            <div class="item">
                <h3 id="titulo2P">Bienvenido</h3>
                <h1 id = "tituloPrincipal">SITIO DE REVISTAS</h1>
                <!--<a href="DocumentosWeb/Registrarse.jsp"><button class="btn btn-primary"  >REGISTRARME</button></a>-->
            </div>
        </div>
        
            <div id = "barra">
                <nav class="navbar navbar-inverse" role="navigation">
                    <div class="collapse navbar-collapse navbar-ex1-collapse">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="index.jsp">Pagina Principal</a></li>
                            <li><a href="DocumentosWeb/Registrarse.jsp">Registrarse</a></li>
                        </ul>
                        <ul class="nav navbar-nav navbar-left">
                            <li><a href="DocumentosWeb/LogIn.jsp" >Iniciar Sesion</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
        </header>
        <div id= "div5"  class="media">
            <a class="pull-left">
                <img  class="media-object" src="DocumentosWeb/imagenes/leer.jpg" alt="Mi imagen" width="300" height="220">
            </a>
            <div class="media-body">
                <h2 class="media-heading">como Suscriptor</h2>
                <h4>como Suscriptor podras tener acceso a cinetos de revistas mediante suscripciones mensuales el las cuales
                    podras estar informado de tus temas de interes o simplemente distraerte mediante la lectura, ampleando tu conocimiento
                    a la vez que pasas un buen rato.</h4>
            </div>
        </div>
        <div id= "div5"  class="media">
            <a class="pull-left">
                <img  class="media-object" src="DocumentosWeb/imagenes/escribir1.jpg" alt="Mi imagen" width="300" height="220">
            </a>
            <div class="media-body">
                <h4 class="media-heading">Título del contenido</h4>
            </div>
        </div>
    </body>
</html>

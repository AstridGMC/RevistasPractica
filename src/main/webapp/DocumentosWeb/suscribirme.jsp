<%-- 
    Document   : suscribirme
    Created on : 30/09/2019, 11:26:49 AM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">


        <title>JSP Page</title>
    </head>
    <body>

        <div class="container"  id="myAlert">
            <h2>Dismissal Alert Messages</h2>
            <div class="alert alert-success alert-dismissable" id="myAlert2">
                  <button id="botonWindowOpen">Abrir pop-up</button>
            <button id="botonWindowClose">Cerrar pop-up</button>

        </div>
    </body>
         <script>
    //Variable que almacena el método window.open()
    var miVentana;

    //La función window_open crea el pop-up o ventana emergente
    function window_open(){
      miVentana = window.open( "../DocumentosWeb/LogIn.jsp", "nombrePop-Up", "width=700,height=700, top=40,left=50");
    }
    
    //La función window_close cerrara el pop-up o ventana emergente
    function window_close(){
      miVentana.close();
    }
    
    // Llamo a la función window_open en el evento click del botón con id = "botonWindowOpen"
    document.getElementById("botonWindowOpen").onclick = function() {window_open()};

    // Llamo a la función window_close en el evento click del botón con id = "botonWindowClose"
    document.getElementById("botonWindowClose").onclick = function() {window_close()};
  </script>
</html>

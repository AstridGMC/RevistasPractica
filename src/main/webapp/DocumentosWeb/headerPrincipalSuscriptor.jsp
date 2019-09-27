<link href = "../bootstrap/css/estiloBarra.css" rel="stylesheet" type ="text/css">
<%
    String cui = (String) session.getAttribute("cui");
    String nombre = (String) session.getAttribute("nombre");
%>
<header>
    <div class="container" id="contenedor" style="background-image: url('../DocumentosWeb/imagenes/portada.jpg')">
        <div id ="divIMG">
            <img src="../Controler" alt="Mi imagen" width="200" height="220" id="IMG">
        </div>
        <div id ="div7">
            <h3>Suscriptor</h3>
            <h1> <%=nombre%> </h1>
        </div>
    </div>
    <div>
        <nav>
            <ul>
                <li><a href="#">Buscar Revistas</a></li>
                <li><a href="editarPerfil.jsp">Ver Mi Perfil</a></li>
                <li><a href="#">Ver Suscripciones</a></li>
                <li><a href="#">Pagos</a></li>
                <li><a href="#">Conusltas</a></li>
            </ul>
        </nav>
    </div>

</header>
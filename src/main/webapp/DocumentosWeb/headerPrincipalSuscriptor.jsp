<link href = "<%=request.getContextPath()%>//bootstrap/css/estiloBarra.css" rel="stylesheet" type ="text/css">
<%
    String cui = (String) session.getAttribute("cui");
    String nombre = (String) session.getAttribute("nombre");
    request.setAttribute("imagen", "imagenPerfil");
%>
<header>
    <div class="container" id="contenedor" style="background-image: url('<%=request.getContextPath()%>/DocumentosWeb/imagenes/portada.jpg')">
        <div id ="divIMG">
            <img src="<%=request.getContextPath()%>/Controler" alt="Mi imagen" width="200" height="220" id="IMG">
        </div>
        <div id ="div7">
            <h3>Suscriptor</h3>
            <h1> <%=nombre%> </h1>
        </div>
    </div>
    <div>
        <nav>
            <ul>
                <li><a  href="<%=request.getContextPath()%>/DocumentosWeb/inicioSuscriptor.jsp">Inicio</a></li>
                <li><a href="<%=request.getContextPath()%>/editarMiPerfil">Ver Mi Perfil</a></li>
                <li><a name="mis revistas" value="todasLasRevistas" href="<%=request.getContextPath()%>/verRevistas?mis revistas=todasLasRevistas">Ver Revistas</a></li>
                <li><a name="mis revistas" value="misRevistas" href="<%=request.getContextPath()%>/verRevistas?mis revistas=misRevistas">Ver mis Suscripciones</a></li>
                <li><a href="#">Pagos</a></li>
                <li><a href="#">Conusltas</a></li>
            </ul>
        </nav>
    </div>

</header>
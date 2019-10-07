<link href= "<%=request.getContextPath()%>/bootstrap/css/estiloBarra.css" rel="stylesheet" type ="text/css">

<%
    String cui = (String) session.getAttribute("cui");
    String nombre = (String) session.getAttribute("nombreDelUsuario");
    request.setAttribute("imagen", "imagenPerfil");
%>
<header>
    <div class="container" id="contenedor" style="background-image: url('<%=request.getContextPath()%>/DocumentosWeb/imagenes/portada.jpg')">
        <div id ="divIMG">
            <img src="<%=request.getContextPath()%>/Controler" alt="Mi imagen" width="200" name="imagen" height="220" value="IMG">
        </div>
        <div id ="div7">
            <h3>Administrador</h3>
            <h1> <%=nombre%> </h1>
        </div>
    </div>
    <div>
        <nav>
            <ul>
                <li><a href="<%=request.getContextPath()%>/DocumentosWeb/InicioEditor.jsp">Inicio</a></li>
                <li><a href="<%=request.getContextPath()%>/editarMiPerfil">Ver Mi Perfil</a></li>
                <li><a href="<%=request.getContextPath()%>/CostosAdmin">Asignar Costos Revistas</a></li>
                <li><a href="<%=request.getContextPath()%>/SubirMisRevistas">Ganancias</a></li>
                <li><a href="<%=request.getContextPath()%>/ReportesAdmin?tipo=populares">Reportes Revistas Populares</a></li>
                <li><a href="<%=request.getContextPath()%>/ReportesAdmin?tipo=comentario">Reportes Revistas </a></li>
                <li><a href="<%=request.getContextPath()%>/verRevistas">Crear Administrador</a></li>
            </ul>
        </nav>
    </div>

</header>
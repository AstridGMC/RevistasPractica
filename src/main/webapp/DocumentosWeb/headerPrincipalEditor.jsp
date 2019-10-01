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
            <h3>Editor</h3>
            <h1> <%=nombre%> </h1>
        </div>
    </div>
    <div>
        <nav>
            <ul>
                <li><a href="<%=request.getContextPath()%>/DocumentosWeb/InicioEditor.jsp">Inicio</a></li>
                <li><a href="<%=request.getContextPath()%>/editarMiPerfil">Ver Mi Perfil</a></li>
                <li><a href="<%=request.getContextPath()%>/SubirMisRevistas">Subir nueva revista</a></li>
                <li><a href="<%=request.getContextPath()%>/verRevistas">Ver Mis Revistas</a></li>
                <li><a href="#">Conusltas</a></li>
            </ul>
        </nav>
    </div>

</header>
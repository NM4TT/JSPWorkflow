
<%@page import="controlador.Controlador"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stylesIndex.css" />
        <title>Flujo De Trabajo</title>
    </head>
    <body>
        
        <%
            Controlador.trabajadorSesion.limpiarInstancia();
            Controlador.sesionIniciada = false;
        %>
        
        <div class="contenedor">
            <div style="margin-top: 230px" id="inicioSesion">

            <!-- Titulo -->
            <div>
                <h2>Proyecto Final</h2>
            </div>

            <!-- Login Form -->
            <form action="Controlador">
              <input type="text" id="correo" name="txtCorreo" placeholder="correo">
              <input type="password" id="password" name="txtClave" placeholder="clave">
              <input type="submit" name="sesion" value="Iniciar Sesion">
            </form>

            
            <div id="pieContenedor">
                <h3>${avisoSesion}</h3>
            </div>

          </div>
        </div>

    </body>
</html>

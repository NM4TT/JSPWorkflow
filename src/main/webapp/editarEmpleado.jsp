
<%@page import="controlador.Controlador"%>
<%@page import="modelo.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>

        <nav class="navbar navbar-dark bg-dark">

            <div style="left:43%;" class="dropdown">
                <a style="color: white;" href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Sesion</a>
                <div class="dropdown-menu text-center">
                    <a style="color: black" href="${pageContext.request.contextPath}/PrincipalM.jsp" style="color: white" class="dropdown-item">Inicio</a>
                    <div class="dropdown-divider"></div>
                    <a style="color: black" href="${pageContext.request.contextPath}/index.jsp" style="color: white" class="dropdown-item">Salir</a>
                </div>
            </div>
        </nav>      

        <div class="container">
            <div class="col-lg-6">
                <%
                    Trabajador t = new Trabajador();
                    t.asignarId(Controlador.entidadAinteractuar);
                    t.leerEnBd();
                %>
                <h1>Modificar Empleado</h1>
                <form action="Controlador">
                    <input type="hidden" name="txtId" value="<%=t.traerId()%>">
                    Nombre:<br>
                    <input class="form-control" type="text" name="txtNombre" value="<%=t.traerPrimerNombre()%>"><br>
                    Apellido:<br>
                    <input class="form-control" type="text" name="txtApellido" value="<%=t.traerPrimerApellido()%>"><br>
                    Fecha de Nacimiento:<br>
                    <input class="form-control" type="date" min="1960-12-31" max="2003-12-31" name="txtNacimiento" value="<%=t.traerFechaNacimiento()%>"><br>
                    Telefono:<br>
                    <input class="form-control" type="text" name="txtTelefono" value="<%=t.traerNumeroTelefonico()%>"><br>
                    Correo:<br>
                    <input class="form-control" type="text" name="txtCorreo" value="<%=t.traerCorreo()%>"><br>
                    Clave:<br>
                    <input class="form-control" type="text" name="txtClave" value="<%=t.traerClave()%>"><br>
                    <input class="btn btn-primary" type="submit" name="accion" value="Actualizar Empleado">
                    <a href="${pageContext.request.contextPath}/listarEmpleados.jsp">Regresar</a>
                </form>
            </div>

            <div>${estadoDeProceso}</div>

        </div>

        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
    </body>
</html>

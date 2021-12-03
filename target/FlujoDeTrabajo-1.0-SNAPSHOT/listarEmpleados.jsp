
<%@page import="modelo.*"%>
<%@page import="controlador.*"%>
<%@page import="java.util.List"%>
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
            <h1>Empleados</h1>
            <a class="btn btn-success" href="${pageContext.request.contextPath}/agregarEmpleado.jsp">Agregar Nuevo</a>
            <br><br>
            <div>${estadoDeProceso}</div>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">ID</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">Apellido</th>
                        <th class="text-center">Nacimiento</th>
                        <th class="text-center">Telefono</th>
                        <th class="text-center">Correo</th>
                        <th class="text-center">Clave</th>
                        <th class="text-center">Accion</th>
                    </tr>
                </thead>
                <%
                    List<Trabajador>list=Trabajador.traerEmpleados();
                    for(Trabajador emp : list){
                %>
                <tbody>
                    <tr>
                        <td class="text-center"><%= emp.traerId()%></td>
                        <td class="text-center"><%= emp.traerPrimerNombre()%></td>
                        <td class="text-center"><%= emp.traerPrimerApellido()%></td>
                        <td class="text-center"><%= emp.traerFechaNacimiento()%></td>
                        <td class="text-center"><%= emp.traerNumeroTelefonico()%></td>
                        <td class="text-center"><%= emp.traerCorreo()%></td>
                        <td class="text-center"><%= emp.traerClave()%></td>
                        <td class="text-center">
                            <a class="btn btn-warning" href="Controlador?accion=editarEmpleado&id=<%= emp.traerId()%>">Editar</a>
                            <a class="btn btn-danger" href="Controlador?accion=eliminarEmpleado&id=<%= emp.traerId()%>">Remover</a>
                        </td>
                    </tr>
                    <%
                       }
                    %>
                </tbody>
            </table>

        </div>
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
    </body>
</html>

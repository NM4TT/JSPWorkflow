
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
                    <a style="color: black" href="${pageContext.request.contextPath}/PrincipalE.jsp" style="color: white" class="dropdown-item">Inicio</a>
                    <div class="dropdown-divider"></div>
                    <a style="color: black" href="${pageContext.request.contextPath}/index.jsp" name = "cerroSesion" style="color: white" class="dropdown-item">Salir</a>
                </div>
            </div>
        </nav>        
        
        <div class="container">
            <h1>Actividades</h1>
            <br>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">ID</th>
                        <th class="text-center">Titulo</th>
                        <th class="text-center">Descripcion</th>
                        <th class="text-center">Estatus</th>
                        <th class="text-center">Marcar Como</th>
                    </tr>
                </thead>
                <%
                    List<Solicitud>list=Solicitud.traerActividades();
                    for(Solicitud act : list){
                %>
                <tbody>
                    <tr>
                        <td class="text-center"><%= act.traerId()%></td>
                        <td class="text-center"><%= act.traerTitulo()%></td>
                        <td class="text-center"><%= act.traerDescripcion()%></td>
                        <td class="text-center"><%= act.traerEstatus()%></td>
                        <td class="text-center">
                            <a class="btn btn-warning" href="${pageContext.request.contextPath}/Controlador?accion=marcarCompletada&id=<%= act.traerId()%>">Completada</a>
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

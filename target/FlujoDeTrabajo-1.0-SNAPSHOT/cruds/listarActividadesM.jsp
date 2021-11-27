
<%@page import="java.util.Iterator"%>
<%@page import="modelo.*"%>
<%@page import="controlador.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <h1>Actividades</h1>
            <a class="btn btn-success" href="Controlador?accion=agregarActividad">Agregar Nueva</a>
            <br>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">ID</th>
                        <th class="text-center">Titulo</th>
                        <th class="text-center">Descripcion</th>
                        <th class="text-center">Estatus</th>
                        <th class="text-center">Accion</th>
                    </tr>
                </thead>
                <%
                    Solicitud actividad=new Solicitud();
                    List<Solicitud>list=actividad.traerActividades();
                    Iterator<Solicitud>iter=list.iterator();
                    Solicitud act=null;
                    while(iter.hasNext()){
                        act=iter.next();
                    
                %>
                <tbody>
                    <tr>
                        <td class="text-center"><%= act.traerId()%></td>
                        <td class="text-center"><%= act.traerTitulo()%></td>
                        <td><%= act.traerDescripcion()%></td>
                        <td><%= act.traerEstatus()%></td>
                        <td class="text-center">
                            <a class="btn btn-warning" href="Controlador?accion=editar&id=<%= act.traerId()%>">Editar</a>
                            <a class="btn btn-danger" href="Controlador?accion=eliminar&id=<%= act.traerId()%>">Eliminar</a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>

        </div>
    </body>
</html>

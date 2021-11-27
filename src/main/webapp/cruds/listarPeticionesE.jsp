
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
            <h1>Peticiones</h1>
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
                    Solicitud peticion=new Solicitud();
                    List<Solicitud>list=peticion.traerPeticiones(Controlador.trabajador.traerId());
                    Iterator<Solicitud>iter=list.iterator();
                    Solicitud petic=null;
                    while(iter.hasNext()){
                        petic=iter.next();
                    
                %>
                <tbody>
                    <tr>
                        <td class="text-center"><%= petic.traerId()%></td>
                        <td class="text-center"><%= petic.traerTitulo()%></td>
                        <td><%= petic.traerDescripcion()%></td>
                        <td><%= petic.traerEstatus()%></td>
                        <td class="text-center">
                            <a class="btn btn-warning" href="Controlador?accion=editar&id=<%= petic.traerId()%>">Editar</a>
                            <a class="btn btn-danger" href="Controlador?accion=eliminar&id=<%= petic.traerId()%>">Remover</a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>

        </div>
    </body>
</html>

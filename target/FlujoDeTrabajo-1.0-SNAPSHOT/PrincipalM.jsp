<%@page  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
        
        <title>JSP Page</title>
    </head>
    <body>
        
        <%%>
        
        <nav class="navbar navbar-dark bg-dark">

            <div style="left:1400px;" class="dropdown">
                <a style="color: white;" href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Sesion</a>
                <div class="dropdown-menu text-center">
                    <div class="dropdown-divider"></div>
                    <a style="color: black" href="index.jsp" style="color: white" class="dropdown-item">Salir</a>
                </div>
            </div>
        </nav>
        
        <div class="container mt-4">
            
            <h1>Bienvenido</h1>
            
            <div style="margin-top: 60px">
                <div class="row gutters-sm">
                  <div class="col-md-4 mb-3">
                    <div class="card">
                      <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                          <img src="${pageContext.request.contextPath}/images/userimage.png" alt="user" class="rounded-circle" width="150">
                          <div class="mt-3">
                            <h4>${nombre}</h4>
                            <p class="text-secondary mb-1">${rol}</p>
                            <p class="text-muted font-size-sm">${correo}</p>
                          </div>
                        </div>
                      </div>
                    </div>

                  </div>
                    <div style="bottom: 9px"class="col-md-8">
                    <div class="card mb-3">
                      <div class="card-body">
                        <div class="row">
                          <div class="col-sm-3">
                            <h6 class="mb-0">Nombre Completo</h6>
                          </div>
                          <div class="col-sm-9 text-secondary">
                            ${nombre}
                          </div>
                        </div>
                        <hr>
                        <div class="row">
                          <div class="col-sm-3">
                            <h6 class="mb-0">Correo</h6>
                          </div>
                          <div class="col-sm-9 text-secondary">
                            ${correo}
                          </div>
                        </div>
                        <hr>
                        <div class="row">
                          <div class="col-sm-3">
                            <h6 class="mb-0">Rol</h6>
                          </div>
                          <div class="col-sm-9 text-secondary">
                            ${rol}
                          </div>
                        </div>
                        <hr>
                        <div class="row">
                          <div class="col-sm-3">
                            <h6 class="mb-0">Celular</h6>
                          </div>
                          <div class="col-sm-9 text-secondary">
                            ${telefono}
                          </div>
                        </div>
                        <hr>
                        <div class="row">
                          <div class="col-sm-3">
                            <h6 class="mb-0">Fecha Nacimiento</h6>
                          </div>
                          <div class="col-sm-9 text-secondary">
                            ${nacimiento}
                          </div>
                        </div>
                        <hr>
                        <div class="row">
                          <div class="col-sm-12">
                            <a class="btn btn-info " href="AdminActividades.jsp">Administrar Actividades</a>
                            <a class="btn btn-info " href="RevSolicitudes.jsp">Revisar Solicitudes</a>
                            <a class="btn btn-info " href="AdminEmpleados.jsp">Administrar Empleados</a>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
            </div>
            
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-fQybjgWLrvvRgtW6bFlB7jaZrFsaBXjsOMm/tB9LTS58ONXgqbR9W8oWht/amnpF" crossorigin="anonymous"></script>
    </body>
</html>

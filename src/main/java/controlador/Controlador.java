package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.*;
import javax.servlet.RequestDispatcher;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    public static Trabajador trabajadorSesion = new Trabajador();
    public static int entidadAinteractuar;
    public static boolean sesionIniciada;

    private Trabajador empleado = new Trabajador();
    private Solicitud solicitud = new Solicitud();
    private boolean queryOk = false;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getSession().setAttribute("avisoSesion", "");
        request.getSession().setAttribute("estadoDeProceso", "");

        String accionSesion = request.getParameter("sesion");
        String accion = request.getParameter("accion");
        String ventanaAMostrar = "";

        try {
            if (!sesionIniciada) {

                if (accionSesion.equals("Iniciar Sesion")) {
                    String correo = request.getParameter("txtCorreo");
                    String clave = request.getParameter("txtClave");

                    trabajadorSesion.asignarCorreo(correo);
                    trabajadorSesion.asignarClave(clave);

                    sesionIniciada = trabajadorSesion.iniciarSesion();

                    if (sesionIniciada) {

                        request.getSession().setAttribute("nombre", trabajadorSesion.traerPrimerNombre() + " " + trabajadorSesion.traerPrimerApellido());
                        request.getSession().setAttribute("rol", trabajadorSesion.traerTipo());
                        request.getSession().setAttribute("correo", trabajadorSesion.traerCorreo());
                        request.getSession().setAttribute("telefono", trabajadorSesion.traerNumeroTelefonico());
                        request.getSession().setAttribute("nacimiento", trabajadorSesion.traerFechaNacimiento());

                        if (trabajadorSesion.traerTipo().equals("MANAGER")) {
                            request.getRequestDispatcher("PrincipalM.jsp").forward(request, response);
                        } else {
                            request.getRequestDispatcher("PrincipalE.jsp").forward(request, response);
                        }

                    } else {
                        request.getSession().setAttribute("avisoSesion", "Error en credenciales. Reintente.");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }

                }

                //Si sesion esta iniciada puede interactuar con el sistema
            } else {

                //Agregaciones
                if (accion.equalsIgnoreCase("Agregar Empleado")) {
                    empleado.asignarTipo("EMPLEADO");
                    empleado.asignarPrimerNombre(request.getParameter("txtNombre").trim());
                    empleado.asignarPrimerApellido(request.getParameter("txtApellido").trim());
                    empleado.asignarFechaNacimiento(request.getParameter("txtNacimiento").trim());
                    empleado.asignarNumeroTelefonico(request.getParameter("txtTelefono").trim());
                    empleado.asignarCorreo(request.getParameter("txtCorreo").trim());
                    empleado.asignarClave(request.getParameter("txtClave").trim());
                    queryOk = empleado.crearEnBd();

                    if (queryOk) {
                        ventanaAMostrar = "listarEmpleados.jsp";
                        request.getSession().setAttribute("estadoDeProceso", "Operacion Realizada.");
                    } else {
                        ventanaAMostrar = "agregarEmpleados.jsp";
                        request.getSession().setAttribute("estadoDeProceso", "Error. Reintente.");
                    }

                } else if (accion.equalsIgnoreCase("Agregar Actividad")) {
                    solicitud.asignarTitulo(request.getParameter("txtTitulo").trim());
                    solicitud.asignarDescripcion(request.getParameter("txtDescripcion").trim());
                    solicitud.asignarTipo("ACTIVIDAD");
                    solicitud.asignarIDSolicitante(trabajadorSesion.traerId());
                    solicitud.asignarEstatus("PENDIENTE");
                    queryOk = solicitud.crearEnBd();

                    if (queryOk) {
                        ventanaAMostrar = "listarActividadesM.jsp";
                        request.getSession().setAttribute("estadoDeProceso", "Operacion Realizada.");
                    } else {
                        ventanaAMostrar = "agregarActividad.jsp";
                        request.getSession().setAttribute("estadoDeProceso", "Error. Reintente.");
                    }

                } else if (accion.equalsIgnoreCase("Agregar Peticion")) {
                    solicitud.asignarTitulo(request.getParameter("txtTitulo").trim());
                    solicitud.asignarDescripcion(request.getParameter("txtDescripcion").trim());
                    solicitud.asignarTipo("PETICION");
                    solicitud.asignarIDSolicitante(trabajadorSesion.traerId());
                    solicitud.asignarEstatus("PENDIENTE");
                    queryOk = solicitud.crearEnBd();

                    if (queryOk) {
                        ventanaAMostrar = "listarPeticionesE.jsp";
                        request.getSession().setAttribute("estadoDeProceso", "Operacion Realizada.");
                    } else {
                        ventanaAMostrar = "agregarPeticion.jsp";
                        request.getSession().setAttribute("estadoDeProceso", "Error. Reintente.");
                    }
                }

                //Ediciones (ventana)
                if (accion.contains("editar")) {
                    entidadAinteractuar = Integer.parseInt(request.getParameter("id"));

                    if (accion.contains("Empleado")) {
                        ventanaAMostrar = "editarEmpleado.jsp";

                    } else if (accion.contains("Peticion")) {
                        ventanaAMostrar = "editarPeticion.jsp";

                    } else if (accion.contains("Actividad")) {
                        ventanaAMostrar = "editarActividad.jsp";
                    }

                }

                //Actualizaciones (queries)
                if (accion.equalsIgnoreCase("Actualizar Empleado")) {
                    empleado.asignarTipo("EMPLEADO");
                    empleado.asignarId(Integer.parseInt(request.getParameter("txtId")));
                    empleado.asignarPrimerNombre(request.getParameter("txtNombre").trim());
                    empleado.asignarPrimerApellido(request.getParameter("txtApellido").trim());
                    empleado.asignarFechaNacimiento(request.getParameter("txtNacimiento").trim());
                    empleado.asignarNumeroTelefonico(request.getParameter("txtTelefono").trim());
                    empleado.asignarCorreo(request.getParameter("txtCorreo").trim());
                    empleado.asignarClave(request.getParameter("txtClave").trim());
                    queryOk = empleado.actualizarEnBd();

                    if (queryOk) {
                        ventanaAMostrar = "listarEmpleados.jsp";
                        request.getSession().setAttribute("estadoDeProceso", "Operacion Realizada.");
                    } else {
                        ventanaAMostrar = "editarEmpleado.jsp";
                        request.getSession().setAttribute("estadoDeProceso", "Error. Reintente.");
                    }

                } else if (accion.equalsIgnoreCase("Actualizar Peticion")) {
                    solicitud.asignarId(Integer.parseInt(request.getParameter("txtId")));
                    solicitud.asignarTitulo(request.getParameter("txtTitulo").trim());
                    solicitud.asignarDescripcion(request.getParameter("txtDescripcion").trim());
                    solicitud.asignarTipo("PETICION");
                    solicitud.asignarIDSolicitante(trabajadorSesion.traerId());
                    solicitud.asignarEstatus("PENDIENTE");
                    queryOk = solicitud.actualizarEnBd();

                    if (queryOk) {
                        ventanaAMostrar = "listarPeticionesE.jsp";
                        request.getSession().setAttribute("estadoDeProceso", "Operacion Realizada.");
                    } else {
                        ventanaAMostrar = "editarPeticion.jsp";
                        request.getSession().setAttribute("estadoDeProceso", "Error. Reintente.");
                    }

                } else if (accion.equalsIgnoreCase("Actualizar Actividad")) {
                    solicitud.asignarId(Integer.parseInt(request.getParameter("txtId")));
                    solicitud.asignarTitulo(request.getParameter("txtTitulo").trim());
                    solicitud.asignarDescripcion(request.getParameter("txtDescripcion").trim());
                    solicitud.asignarTipo("ACTIVIDAD");
                    solicitud.asignarIDSolicitante(trabajadorSesion.traerId());
                    solicitud.asignarEstatus("PENDIENTE");
                    queryOk = solicitud.actualizarEnBd();

                    if (queryOk) {
                        ventanaAMostrar = "listarActividadesM.jsp";
                        request.getSession().setAttribute("estadoDeProceso", "Operacion Realizada.");
                    } else {
                        ventanaAMostrar = "editarActividad.jsp";
                        request.getSession().setAttribute("estadoDeProceso", "Error. Reintente.");
                    }
                }

                //Eliminaciones
                if (accion.contains("eliminar")) {
                    entidadAinteractuar = Integer.parseInt(request.getParameter("id"));

                    if (accion.contains("Empleado")) {
                        ventanaAMostrar = "listarEmpleados.jsp";
                        empleado.asignarId(entidadAinteractuar);
                        queryOk = empleado.eliminarEnBd();

                    } else if (accion.contains("Peticion")) {
                        ventanaAMostrar = "listarPeticionesE.jsp";
                        solicitud.asignarId(entidadAinteractuar);
                        queryOk = solicitud.eliminarEnBd();

                    } else if (accion.contains("Actividad")) {
                        ventanaAMostrar = "listarActividadesM.jsp";
                        solicitud.asignarId(entidadAinteractuar);
                        queryOk = solicitud.eliminarEnBd();
                    }

                    if (queryOk) {
                        request.getSession().setAttribute("estadoDeProceso", "Operacion Realizada.");
                    } else {
                        request.getSession().setAttribute("estadoDeProceso", "Error. Reintente.");
                    }

                }

                //Marcados
                if (accion.contains("marcar")) {
                    entidadAinteractuar = Integer.parseInt(request.getParameter("id"));
                    solicitud.asignarId(entidadAinteractuar);
                    solicitud.leerEnBd();

                    if (accion.contains("Completada")) {
                        ventanaAMostrar = "listarActividadesE.jsp";
                        solicitud.asignarEstatus("Completada");

                    } else if (accion.contains("Aprobada")) {
                        ventanaAMostrar = "listarPeticionesM.jsp";
                        solicitud.asignarEstatus("Aprobada");

                    } else if (accion.contains("Negada")) {
                        ventanaAMostrar = "listarPeticionesM.jsp";
                        solicitud.asignarEstatus("Negada");

                    }
                    
                    queryOk = solicitud.actualizarEnBd();

                    if (queryOk) {
                        request.getSession().setAttribute("estadoDeProceso", "Operacion Realizada.");
                    } else {
                        request.getSession().setAttribute("estadoDeProceso", "Error. Reintente.");
                    }

                }
            }

        } catch (Exception e) {
            ventanaAMostrar = "index.jsp";
        }

        RequestDispatcher vista = request.getRequestDispatcher(ventanaAMostrar);
        vista.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

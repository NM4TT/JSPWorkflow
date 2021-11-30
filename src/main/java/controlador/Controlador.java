package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.jsp.PageContext;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    public static Trabajador trabajadorSesion = new Trabajador();
    Trabajador empleado = new Trabajador();
    Solicitud solicitud = new Solicitud();
    public static boolean sesionIniciada = false;
    boolean queryOk = false;

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
        String accionCrud = request.getParameter("accion");
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

            }

            //Agregaciones
            if (accionCrud.equalsIgnoreCase("Agregar Empleado")) {
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

            } else if (accionCrud.equalsIgnoreCase("Agregar Actividad")) {
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

            } else if (accionCrud.equalsIgnoreCase("Agregar Peticion")) {
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

            //Ediciones
            if (accionCrud.equalsIgnoreCase("Actualizar Empleado")) {
                empleado.asignarId(Integer.parseInt(request.getParameter("txtid").trim()));
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

            } else if (accionCrud.equalsIgnoreCase("Actualizar Peticion")) {
                solicitud.asignarId(Integer.parseInt(request.getParameter("txtid").trim()));
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

            } else if (accionCrud.equalsIgnoreCase("Actualizar Actividad")) {
                solicitud.asignarId(Integer.parseInt(request.getParameter("txtid").trim()));
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
                    ventanaAMostrar = "editarActividad.jsp";
                    request.getSession().setAttribute("estadoDeProceso", "Error. Reintente.");
                }
            }

            //Eliminaciones
            if (accionCrud.equalsIgnoreCase("Eliminar Empleado")) {
                empleado.asignarId(Integer.parseInt(request.getParameter("id")));
                queryOk = empleado.eliminarEnBd();
                ventanaAMostrar = "listarEmpleados.jsp";
                if (queryOk) {
                    request.getSession().setAttribute("estadoDeProceso", "Operacion Realizada.");
                } else {
                    request.getSession().setAttribute("estadoDeProceso", "Error. Reintente.");
                }

            } else if (accionCrud.equalsIgnoreCase("Eliminar Actividad")) {
                solicitud.asignarId(Integer.parseInt(request.getParameter("id")));
                queryOk = solicitud.eliminarEnBd();
                ventanaAMostrar = "listarActividadesM.jsp";
                if (queryOk) {
                    request.getSession().setAttribute("estadoDeProceso", "Operacion Realizada.");
                } else {
                    request.getSession().setAttribute("estadoDeProceso", "Error. Reintente.");
                }

            } else if (accionCrud.equalsIgnoreCase("Eliminar Peticion")) {
                solicitud.asignarId(Integer.parseInt(request.getParameter("id")));
                queryOk = solicitud.eliminarEnBd();
                ventanaAMostrar = "listarPeticionesE.jsp";
                if (queryOk) {
                    request.getSession().setAttribute("estadoDeProceso", "Operacion Realizada.");
                } else {
                    request.getSession().setAttribute("estadoDeProceso", "Error. Reintente.");
                }
            }

        } catch (Exception e) {

            if (sesionIniciada) {
                ventanaAMostrar = (trabajadorSesion.traerTipo().equals("MANAGER")) ? "PrincipalM.jsp" : "PrincipalE.jsp";
            } else {
                ventanaAMostrar = "index.jsp";
            }

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

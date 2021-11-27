package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.*;
import static modelo.EntidadQueryable.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.jsp.PageContext;

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    public static Trabajador trabajadorSesion = new Trabajador();
    Trabajador empleado = new Trabajador();
    Solicitud solicitud = new Solicitud();
    static boolean sesionIniciada = false;
    
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
        
        String accionSesion = request.getParameter("sesion");
        String accionCrud = request.getParameter("accion");
        String ventanaAMostrar = "";
        
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
            
         
            
            //Agregaciones
            if(accionCrud.equalsIgnoreCase("Agregar")){
                empleado.asignarPrimerNombre(request.getParameter("txtNombre").trim());
                empleado.asignarPrimerNombre(request.getParameter("txtApellido").trim());
                empleado.asignarPrimerNombre(request.getParameter("txtNacimiento").trim());
                empleado.asignarPrimerNombre(request.getParameter("txtTelefono").trim());
                empleado.asignarPrimerNombre(request.getParameter("txtCorreo").trim());
                empleado.asignarPrimerNombre(request.getParameter("txtClave").trim());
                empleado.crearEnBd();
                ventanaAMostrar = "listarEmpleados";
                
            } else if(accionCrud.equalsIgnoreCase("Agregar")){
                solicitud.asignarTitulo(request.getParameter("txtTitulo").trim());
                solicitud.asignarDescripcion(request.getParameter("txtDescripcion").trim());
                solicitud.asignarTipo("ACTIVIDAD");
                solicitud.asignarIDSolicitante(trabajadorSesion.traerId());
                solicitud.asignarEstatus("PENDIENTE");
                solicitud.crearEnBd();
                ventanaAMostrar = "listarActividadesM";
            
            } else if(accionCrud.equalsIgnoreCase("Agregar")){
                solicitud.asignarTitulo(request.getParameter("txtTitulo").trim());
                solicitud.asignarDescripcion(request.getParameter("txtDescripcion").trim());
                solicitud.asignarTipo("PETICION");
                solicitud.asignarIDSolicitante(trabajadorSesion.traerId());
                solicitud.asignarEstatus("PENDIENTE");
                solicitud.crearEnBd();
                ventanaAMostrar = "listarPeticionesE";
            }
            
            
           //Ediciones
            if(accionCrud.equalsIgnoreCase("Actualizar")){
                empleado.asignarId(Integer.parseInt(request.getParameter("txtid").trim()));
                empleado.asignarPrimerNombre(request.getParameter("txtNombre").trim());
                empleado.asignarPrimerNombre(request.getParameter("txtApellido").trim());
                empleado.asignarPrimerNombre(request.getParameter("txtNacimiento").trim());
                empleado.asignarPrimerNombre(request.getParameter("txtTelefono").trim());
                empleado.asignarPrimerNombre(request.getParameter("txtCorreo").trim());
                empleado.asignarPrimerNombre(request.getParameter("txtClave").trim());
                empleado.actualizarEnBd();
                ventanaAMostrar = "listarEmpleados";
            
            } else if(accionCrud.equalsIgnoreCase("Actualizar")){
                solicitud.asignarId(Integer.parseInt(request.getParameter("txtid").trim()));
                solicitud.asignarTitulo(request.getParameter("txtTitulo").trim());
                solicitud.asignarDescripcion(request.getParameter("txtDescripcion").trim());
                solicitud.asignarTipo("PETICION");
                solicitud.asignarIDSolicitante(trabajadorSesion.traerId());
                solicitud.asignarEstatus("PENDIENTE");
                solicitud.actualizarEnBd();
                ventanaAMostrar = "listarPeticionesE";
            
            } else if(accionCrud.equalsIgnoreCase("Actualizar")){
                solicitud.asignarId(Integer.parseInt(request.getParameter("txtid").trim()));
                solicitud.asignarTitulo(request.getParameter("txtTitulo").trim());
                solicitud.asignarDescripcion(request.getParameter("txtDescripcion").trim());
                solicitud.asignarTipo("ACTIVIDAD");
                solicitud.asignarIDSolicitante(trabajadorSesion.traerId());
                solicitud.asignarEstatus("PENDIENTE");
                solicitud.crearEnBd();
                ventanaAMostrar = "listarActividadesM";
            }
            
            
            //Eliminaciones
            if(accionCrud.equalsIgnoreCase("Eliminar")){
                empleado.asignarId(Integer.parseInt(request.getParameter("id")));
                empleado.eliminarEnBd();
                ventanaAMostrar = "listarEmpleados";
            
            } else if(accionCrud.equalsIgnoreCase("Eliminar")){
                solicitud.asignarId(Integer.parseInt(request.getParameter("id")));
                solicitud.eliminarEnBd();
                ventanaAMostrar = "listarActividadesM";
            
            } else if(accionCrud.equalsIgnoreCase("Eliminar")){
                solicitud.asignarId(Integer.parseInt(request.getParameter("id")));
                solicitud.eliminarEnBd();
                ventanaAMostrar = "listarPeticionesE";
            }
            
            PageContext pageContext;
            RequestDispatcher vista=request.getRequestDispatcher(ventanaAMostrar);
            vista.forward(request, response);
            

        }
        
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

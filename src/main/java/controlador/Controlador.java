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

@WebServlet(name = "Controlador", urlPatterns = {"/Controlador"})
public class Controlador extends HttpServlet {

    static Trabajador trabajador = new Trabajador();
    static Solicitud solicitud = new Solicitud();
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
        
        String accion = request.getParameter("sesion");
        
        if (accion.equals("Iniciar Sesion")) {
            String correo = request.getParameter("txtCorreo");
            String clave = request.getParameter("txtClave");
            
            trabajador.asignarCorreo(correo);
            trabajador.asignarClave(clave);
            
            sesionIniciada = trabajador.iniciarSesion();
          
            if (sesionIniciada) {
                
                request.getSession().setAttribute("nombre", trabajador.traerPrimerNombre() + " " + trabajador.traerPrimerApellido());
                request.getSession().setAttribute("rol", trabajador.traerTipo());
                request.getSession().setAttribute("correo", trabajador.traerCorreo());
                
                if (trabajador.traerTipo().equals("MANAGER")) {                
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

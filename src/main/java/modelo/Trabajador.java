package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class Trabajador extends EntidadQueryable {

    
    //Tabla en base de datos
    public static final String BD_TABLA = "trabajadores";
    
    //Atributos de un trabajador
    private int id;
    private String tipo;
    private String primerNombre;
    private String primerApellido;
    private String fechaNacimiento;
    private String numeroTelefonico;
    private String correo;
    private String clave;
    

    @Override
    public boolean crearEnBd() {
        boolean exito = false;
        String sql = "INSERT INTO " + BD_TABLA + " (Tipo, Nombre, Apellido, Nacimiento, Telefono, Correo, Clave) VALUES (?,?,?,?,?,?,?);";
        
        try {
            Connection cn = conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, this.traerTipo());
            pst.setString(2, this.traerPrimerNombre());
            pst.setString(3, this.traerPrimerApellido());
            pst.setString(4, this.traerFechaNacimiento());
            pst.setString(5, this.traerNumeroTelefonico());
            pst.setString(6, this.traerCorreo());
            pst.setString(7, this.traerClave());
            
            pst.execute();
            
            cn.close();
            pst.close();
            
            exito = true;
            
        } catch (SQLException e) {
            System.err.println("ERROR AL INSERTAR EN BASE DE DATOS " + BD_TABLA + ": \n" + e.getMessage());
        } 
        
        return exito;
    }

    @Override
    public boolean leerEnBd() {
        boolean encontrado = false;

        String sql = "SELECT * FROM " + BD_TABLA + " WHERE id=?;";
        
        try {
            Connection cn = conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, this.traerId());
            
            ResultSet rs = pst.executeQuery();
            
            if(rs != null){
                encontrado = true;
                
                while(rs.next()){
                    this.asignarTipo(rs.getString("Tipo"));
                    this.asignarPrimerNombre(rs.getString("Nombre"));
                    this.asignarPrimerApellido(rs.getString("Apellido"));
                    this.asignarFechaNacimiento(rs.getString("Nacimiento"));
                    this.asignarNumeroTelefonico(rs.getString("Telefono"));
                    this.asignarCorreo(rs.getString("Correo"));
                    this.asignarClave(rs.getString("Clave"));
                }
                
            } else {
                encontrado = false;
            }
            
            cn.close();
            pst.close();
            
        } catch (SQLException e) {
            System.err.println("ERROR AL BUSCAR EN BASE DE DATOS " + BD_TABLA + ": \n" + e.getMessage());
        } 
        
        return encontrado;
    }

    @Override
    public boolean actualizarEnBd() {
        boolean exito = false;
        
        String sql = "UPDATE " + BD_TABLA + " SET Tipo = ?, Nombre = ?, Apellido = ?, Nacimiento = ?, Telefono = ?, correo = ?, clave = ? WHERE id=?;";
        
        try {
            Connection cn = conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, this.traerTipo());
            pst.setString(2, this.traerPrimerNombre());
            pst.setString(3, this.traerPrimerApellido());
            pst.setString(4, this.traerFechaNacimiento());
            pst.setString(5, this.traerNumeroTelefonico());
            pst.setString(6, this.traerCorreo());
            pst.setString(7, this.traerClave());
            pst.setInt(8, this.traerId());
            
            
            cn.close();
            pst.close();
            
            exito = true;
            
        } catch (SQLException e) {
            System.err.println("ERROR AL ACTUALIZAR EN BASE DE DATOS " + BD_TABLA + ": \n" + e.getMessage());
        } 
        
        return exito;
    }

    @Override
    public boolean eliminarEnBd() {
        boolean exito = false;

        String sql = "DELETE FROM " + BD_TABLA + " WHERE id=?;";
        
        try {
            Connection cn = conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, this.traerId());
            
            cn.close();
            pst.close();
            
            exito = true;
            
        } catch (SQLException e) {
            System.err.println("ERROR AL ELIMINAR EN BASE DE DATOS " + BD_TABLA + ": \n" + e.getMessage());
        } 
        
        return exito;       
    }
    
    @Override
    public void limpiarInstancia(){
        this.asignarId(0);
        this.asignarTipo(null);
        this.asignarPrimerNombre(null);
        this.asignarPrimerApellido(null);
        this.asignarFechaNacimiento(null);
        this.asignarNumeroTelefonico(null);
        this.asignarCorreo(null);
        this.asignarClave(null);
    }
    
    public boolean iniciarSesion(){
    
        boolean sesionIniciada = false;

        String sql = "SELECT * FROM " + BD_TABLA + " WHERE Correo=? AND Clave=?;";
        
        try {
            Connection cn = conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, this.traerCorreo());
            pst.setString(2, this.traerClave());
            
            ResultSet rs = pst.executeQuery();
            

            while(rs.next()){
                this.asignarId(rs.getInt("id"));
                this.asignarTipo(rs.getString("Tipo"));
                this.asignarPrimerNombre(rs.getString("Nombre"));
                this.asignarPrimerApellido(rs.getString("Apellido"));
                this.asignarFechaNacimiento(rs.getString("Nacimiento"));
                this.asignarNumeroTelefonico(rs.getString("Telefono"));
            }
            
            if (this.traerId() != 0) {
                sesionIniciada = true;
            }
            
            cn.close();
            pst.close();
            
        } catch (SQLException e) {
            System.err.println("ERROR AL INICIAR SESION EN BASE DE DATOS " + BD_TABLA + ": \n" + e.getMessage());
        } 
        
        return sesionIniciada;        
        
    }
    
    public List<Trabajador> traerEmpleados(){
        Trabajador trabajador = new Trabajador();
        List<Trabajador> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM trabajadores WHERE Tipo = ?;";
        
        try {
            Connection cn = conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, "EMPLEADO");
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                this.asignarPrimerNombre(rs.getString("Nombre"));
                this.asignarPrimerApellido(rs.getString("Apellido"));
                this.asignarFechaNacimiento(rs.getString("Nacimiento"));
                this.asignarNumeroTelefonico(rs.getString("Telefono"));
                this.asignarCorreo(rs.getString("Correo"));
                this.asignarClave(rs.getString("Clave"));
                lista.add(trabajador);
            }
            
        } catch (SQLException e) {
            System.err.println("ERROR AL BUSCAR EN BASE DE DATOS " + BD_TABLA + ": \n" + e.getMessage());
        }
        
        return lista;
    }

    /**
     * @return the id
     */
    public int traerId() {
        return id;
    }

    /**
     * @param id the id to asignar
     */
    public void asignarId(int id) {
        this.id = id;
    }

    /**
     * @return the tipo
     */
    public String traerTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to asignar
     */
    public void asignarTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the primerNombre
     */
    public String traerPrimerNombre() {
        return primerNombre;
    }

    /**
     * @param primerNombre the primerNombre to asignar
     */
    public void asignarPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    /**
     * @return the primerApellido
     */
    public String traerPrimerApellido() {
        return primerApellido;
    }

    /**
     * @param primerApellido the primerApellido to asignar
     */
    public void asignarPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    /**
     * @return the fechaNacimiento
     */
    public String traerFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * @param fechaNacimiento the fechaNacimiento to asignar
     */
    public void asignarFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * @return the numeroTelefonico
     */
    public String traerNumeroTelefonico() {
        return numeroTelefonico;
    }

    /**
     * @param numeroTelefonico the numeroTelefonico to asignar
     */
    public void asignarNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    /**
     * @return the correo
     */
    public String traerCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to asignar
     */
    public void asignarCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the clave
     */
    public String traerClave() {
        return clave;
    }

    /**
     * @param clave the clave to asignar
     */
    public void asignarClave(String clave) {
        this.clave = clave;
    }
    
}

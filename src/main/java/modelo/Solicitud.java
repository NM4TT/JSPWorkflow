/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;

public class Solicitud extends EntidadQueryable{

    protected enum TipoSolicitud {
        PETICION,ACTIVIDAD;
    }
    
    //Tabla en base de datos
    public static final String BD_TABLA = "solicitudes";
    //Atributos de una solicitud
    private int id;
    private String titulo;
    private String tipo;
    private String descripcion;
    private int recibidorID;
    private int enviadorID;

    @Override
    public boolean crearEnBd() {
        boolean exito = false;
        Connection cn;
        PreparedStatement pst;
        String sql = "INSERT INTO " + BD_TABLA + " (Titulo, Tipo, Descripcion, Recibidor, Enviador) VALUES (?,?,?,?,?);";
        
        try {
            cn = conectar();
            pst = cn.prepareStatement(sql);
            pst.setString(1, this.traerTitulo());
            pst.setString(2, this.traerTipo());
            pst.setString(3, this.traerDescripcion());
            pst.setInt(4, this.traerRecibidorID());
            pst.setInt(5, this.traerEnviadorID());
           
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
        Connection cn;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM " + BD_TABLA + " WHERE id=?;";
        
        try {
            cn = conectar();
            pst = cn.prepareStatement(sql);
            pst.setInt(1, this.traerId());
            
            rs = pst.executeQuery();
            
            if(rs != null){
                encontrado = true;
                
                while(rs.next()){
                    this.asignarTitulo(rs.getString("Titulo"));
                    this.asignarTipo(rs.getString("Tipo"));
                    this.asignarDescripcion(rs.getString("Descripcion"));
                    this.asignarRecibidorID(rs.getInt("Recibidor"));
                    this.asignarEnviadorID(rs.getInt("Enviador"));
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
        Connection cn;
        PreparedStatement pst;
        String sql = "UPDATE " + BD_TABLA + " SET Titulo = ?, Tipo = ?, Descripcion = ?, Recibidor = ?, Enviador = ? WHERE id=?;";
        
        try {
            cn = conectar();
            pst = cn.prepareStatement(sql);
            pst.setString(1, this.traerTitulo());
            pst.setString(2, this.traerTipo());
            pst.setString(3, this.traerDescripcion());
            pst.setInt(4, this.traerRecibidorID());
            pst.setInt(5, this.traerEnviadorID());
            pst.setInt(6, this.traerId());
            
            
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
        Connection cn;
        PreparedStatement pst;
        String sql = "DELETE FROM " + BD_TABLA + " WHERE id=?;";
        
        try {
            cn = conectar();
            pst = cn.prepareStatement(sql);
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
        this.asignarTitulo(null);
        this.asignarTipo(null);
        this.asignarDescripcion(null);
        this.asignarEnviadorID(0);
        this.asignarRecibidorID(0);
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
     * @return the titulo
     */
    public String traerTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to asignar
     */
    public void asignarTitulo(String titulo) {
        this.titulo = titulo;
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
     * @return the descripcion
     */
    public String traerDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to asignar
     */
    public void asignarDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the recibidorID
     */
    public int traerRecibidorID() {
        return recibidorID;
    }

    /**
     * @param recibidorID the recibidorID to asignar
     */
    public void asignarRecibidorID(int recibidorID) {
        this.recibidorID = recibidorID;
    }

    /**
     * @return the enviadorID
     */
    public int traerEnviadorID() {
        return enviadorID;
    }

    /**
     * @param enviadorID the enviadorID to asignar
     */
    public void asignarEnviadorID(int enviadorID) {
        this.enviadorID = enviadorID;
    }
    
}

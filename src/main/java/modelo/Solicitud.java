package modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Solicitud extends EntidadQueryable{

    //Tabla en base de datos
    public static final String BD_TABLA = "solicitudes";
    //Atributos de una solicitud
    private int id;
    private String titulo;
    private String tipo;
    private String descripcion;
    private int IDSolicitante;
    private String estatus;

    @Override
    public boolean crearEnBd() {
        boolean exito = false;
        Connection cn;
        PreparedStatement pst;
        String sql = "INSERT INTO " + BD_TABLA + " (Titulo, Tipo, Descripcion, IDSolicitante, Estatus) VALUES (?,?,?,?,?);";
        
        try {
            cn = conectar();
            pst = cn.prepareStatement(sql);
            pst.setString(1, this.traerTitulo());
            pst.setString(2, this.traerTipo());
            pst.setString(3, this.traerDescripcion());
            pst.setInt(4, this.traerIDSolicitante());
            pst.setString(5,this.traerEstatus());
            
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
                    this.asignarId(rs.getInt("id"));
                    this.asignarTitulo(rs.getString("Titulo"));
                    this.asignarTipo(rs.getString("Tipo"));
                    this.asignarDescripcion(rs.getString("Descripcion"));
                    this.asignarIDSolicitante(rs.getInt("IDSolicitante"));
                    this.asignarEstatus(rs.getString("Estatus"));
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
        String sql = "UPDATE " + BD_TABLA + " SET Titulo = ?, Tipo = ?, Descripcion = ?, IDSolicitante = ?, Estatus = ? WHERE id=?;";
        
        try {
            cn = conectar();
            pst = cn.prepareStatement(sql);
            pst.setString(1, this.traerTitulo());
            pst.setString(2, this.traerTipo());
            pst.setString(3, this.traerDescripcion());
            pst.setInt(4, this.traerIDSolicitante());
            pst.setString(5,this.traerEstatus());
            pst.setInt(6, this.traerId());
            
            pst.executeUpdate();
            
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
            
            pst.executeUpdate();
            
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
        this.asignarIDSolicitante(0);
        this.asignarEstatus(null);
    }
    
    public static List<Solicitud> traerPeticiones(){
        
        List<Solicitud> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM solicitudes WHERE Tipo = 'PETICION';";
        
        try {
            Connection cn = conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Solicitud p = new Solicitud();
                p.asignarId(rs.getInt("id"));
                p.asignarTitulo(rs.getString("Titulo"));
                p.asignarDescripcion(rs.getString("Descripcion"));
                p.asignarIDSolicitante(rs.getInt("IDSolicitante"));
                p.asignarEstatus(rs.getString("Estatus"));
                lista.add(p);
            }
            
            cn.close();
            pst.close();
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("ERROR AL BUSCAR EN BASE DE DATOS " + BD_TABLA + ": \n" + e.getMessage());
        }
        
        return lista;
    }
    
    public static List<Solicitud> traerPeticiones(int idSolicitante){
        
        List<Solicitud> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM solicitudes WHERE IDSolicitante = ? and Tipo = 'PETICION';";
        
        try {
            Connection cn = conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, idSolicitante);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Solicitud p = new Solicitud();
                p.asignarId(rs.getInt("id"));
                p.asignarTitulo(rs.getString("Titulo"));
                p.asignarDescripcion(rs.getString("Descripcion"));
                p.asignarIDSolicitante(idSolicitante);
                p.asignarEstatus(rs.getString("Estatus"));
                lista.add(p);
            }
            
            cn.close();
            pst.close();
            rs.close();
            
        } catch (SQLException e) {
            System.err.println("ERROR AL BUSCAR EN BASE DE DATOS " + BD_TABLA + ": \n" + e.getMessage());
        }
        
        return lista;
    }
    
    public static List<Solicitud> traerActividades(){
        
        List<Solicitud> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM solicitudes WHERE Tipo = 'ACTIVIDAD';";
        
        try {
            Connection cn = conectar();
            PreparedStatement pst = cn.prepareStatement(sql);
            
            ResultSet rs = pst.executeQuery();
            
            while(rs.next()){
                Solicitud a = new Solicitud();
                a.asignarId(rs.getInt("id"));
                a.asignarTitulo(rs.getString("Titulo"));
                a.asignarDescripcion(rs.getString("Descripcion"));
                a.asignarIDSolicitante(rs.getInt("IDSolicitante"));
                a.asignarEstatus(rs.getString("Estatus"));
                lista.add(a);
            }
            
            cn.close();
            pst.close();
            rs.close();
            
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
     * @return the IDSolicitante
     */
    public int traerIDSolicitante() {
        return IDSolicitante;
    }

    /**
     * @param recibidorID the IDSolicitante to asignar
     */
    public void asignarIDSolicitante(int recibidorID) {
        this.IDSolicitante = recibidorID;
    }

    /**
     * @return the estatus
     */
    public String traerEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void asignarEstatus(String estatus) {
        this.estatus = estatus;
    }
    
}

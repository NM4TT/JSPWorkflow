/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package modelo;

import java.sql.*;

public abstract class EntidadQueryable {
    
    //admin@admin.com
    //1234
    
    //pepe@empleado.com
    //1234
    
    //Constantes que definen la conexion a la base de datos de la aplicacion
    private final String BD_USUARIO = "root";
    private final String BD_CLAVE = "1234";
    private final String BD_NOMBRE = "flujodetrabajo";
    private final String BD_DRIVER = "org.mariadb.jdbc.Driver";
    private final String BD_PROVEEDOR_DRIVER = "mariadb";
    private final String BD_PUERTO = "3305";
    
    
    
    private final String BD_URL = "jdbc:" + BD_PROVEEDOR_DRIVER + "://localhost:" + BD_PUERTO + "/" + BD_NOMBRE;
    
    
    
    public Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName(BD_DRIVER);
            conexion=DriverManager.getConnection(BD_URL,BD_USUARIO,BD_CLAVE);  
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("ERROR AL CONECTAR EN BASE DE DATOS\n" + e.getMessage());
        }
        return conexion;
    }
    
    public abstract boolean crearEnBd();
    public abstract boolean leerEnBd();
    public abstract boolean actualizarEnBd();
    public abstract boolean eliminarEnBd();
    public abstract void limpiarInstancia();
    
}

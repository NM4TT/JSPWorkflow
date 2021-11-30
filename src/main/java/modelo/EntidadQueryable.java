package modelo;
import java.sql.*;

public abstract class EntidadQueryable {
    
    //admin@admin.com
    //1234
    
    //pepe@empleado.com
    //1234
    
    //Constantes que definen la conexion a la base de datos de la aplicacion
    private static final String BD_USUARIO = "root";
    private static final String BD_CLAVE = "1234";
    private static final String BD_NOMBRE = "flujodetrabajo";
    private static final String BD_DRIVER = "org.mariadb.jdbc.Driver";
    private static final String BD_PROVEEDOR_DRIVER = "mariadb";
    private static final String BD_PUERTO = "3305";
    
    
    
    private static final String BD_URL = "jdbc:" + BD_PROVEEDOR_DRIVER + "://localhost:" + BD_PUERTO + "/" + BD_NOMBRE;
    
    
    
    public static Connection conectar() {
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

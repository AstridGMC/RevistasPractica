
package revistasPractica.Conector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conector {
    private String user = "root";
    private String password = "Astrid.201731318";
    private String stringConnection = "jdbc:mysql://localhost:3306/RevistasPractica";
    private Connection conexion = null;

    public Conector() {
        conection();
    }
    
    
    
    public void conection(){
         try{
            // Nos conectamos a la bd
            conexion= (Connection) DriverManager.getConnection(stringConnection, user, password);
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (conexion!=null){
                //JOptionPane.showMessageDialog(null,"Conexion establecida");
                System.out.println("coneccion establecida");
            }
        }
        // Si la conexion NO fue exitosa mostramos un mensaje de error
        catch ( SQLException e){
            System.out.println("coneccion no establecida"+e);
            JOptionPane.showMessageDialog(null, "El nombre de usuario y/o contrasenia no son validos.");
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
}

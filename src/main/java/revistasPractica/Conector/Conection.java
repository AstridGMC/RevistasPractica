
package revistasPractica.Conector;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
    private final String user = "root";
    private final String password = "Astrid.201731318";
    private final String host = "localhost";
    private final String port = "3306";
    private final String database = "revistasPractica";
    private String classname= "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://"+host+":"+port+"/"+database;
    private final String stringConnection ="jdbc:mysql://localhost/revistasPractica";
    private java.sql.Connection conexion  = null;
    
    public Conection() {
        conection();
    }
    
    
    
    public void conection(){
        try{
             Class.forName(classname);
            // Nos conectamos a la bd
            conexion=  DriverManager.getConnection(url, user ,password);
             System.out.println("conexion establecida");
            // Si la conexion fue exitosa mostramos un mensaje de conexion exitosa
            if (conexion!=null){
                //JOptionPane.showMessageDialog(null,"Conexion establecida");
                System.out.println("coneccion establecida");
            }
        }
        // Si la conexion NO fue exitosa mostramos un mensaje de error
         catch (ClassNotFoundException e) {
             System.out.println("ha fallado la coneccion"+e);
        }catch(SQLException e){
             System.out.println("ha fallado "+e);
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    
     public static void main (String[] args){
       Conection conexion = new Conection();
    }
}

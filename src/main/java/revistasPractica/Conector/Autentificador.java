package revistasPractica.Conector;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import revistasPractica.Conector.Conection;

public class Autentificador extends Conection {

   

    public int ValidarRango(Connection canexion, String usuario, String password) {
        PreparedStatement validarRango = null;
        ResultSet rs = null;
        System.out.println(usuario);
        System.out.println(password);
        try {
            String consulta1 = "SELECT rango FROM Usuario WHERE nombreUsuario= ? AND passwordUser= ?";
            validarRango = canexion.prepareStatement(consulta1);
            validarRango.setString(1, usuario);
            validarRango.setString(2, password);
            rs = validarRango.executeQuery();
            System.out.println(rs.first());
            String rango = rs.getString(1);
            System.out.println(rango);
            if ("Editor".equals(rango)) {
                System.out.println(rango);
                return 1;
            } else if ("Suscriptor".equals(rango)) {
                System.out.println(rango);
                return 2;
            } else if (rango.equals("Administrador")) {
                System.out.println(rango);
                return 3;
            }
        } catch (SQLException e) {
            System.out.println("rango nulo  " + e.getSQLState());
            return 0;
        } /*finally {

            try {
                if (getConexion() != null) {
                    getConexion().close();
                }
                if (validarRango != null) {
                    validarRango.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Autentificador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }*/

        return 0;
    }
    
    
    public boolean Registrar(Connection canexion, String cui, String usuario , String password,String rango ,String nombre, String apellido){
        PreparedStatement ps1 =null;
        try{
            String consulta ="INSERT INTO Usuario (cuiUsuario, nombreUsuario, passwordUser, rango, nombres, apellidos)"
                + " VALUES (?,?,?,?,?,?)";
            
            ps1= canexion.prepareStatement(consulta);
            ps1.setString(1, cui);
            ps1.setString(2, usuario);
            ps1.setString(3, password);
            ps1.setString(4, rango);
            ps1.setString(5, nombre);
            ps1.setString(6, apellido);
            
            if(ps1.executeUpdate()==1){
                return true;
            }
        }catch(Exception e){
            System.out.println("");
        }finally{
            
             try {
                if(getConexion()!=null) getConexion().close();
                if(ps1!= null) ps1.close();
            } catch (SQLException ex) {
                System.err.println("Error al guardar Datos" + ex);
            }
        }
        return false;
    }
   

}

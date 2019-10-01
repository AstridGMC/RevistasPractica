
package revistaspractica.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import revistasPractica.Conector.Conection;

public class Usuario {
    
    protected String nombre;
    protected String usuario;
    protected String rango;
    protected String password;
    protected String cui;
    protected String apellido;
    
    public Usuario() {
        
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    

    public Usuario(HttpServletRequest request) {
        usuario = request.getParameter("usuario");
        password = request.getParameter("password");
        cui=request.getParameter("cui");
        nombre = request.getParameter("nombre");
        apellido = request.getParameter("apellido");
        
        System.out.println(usuario);
         System.out.println(password);
    }
    
    public boolean Registrar(Connection conexion){
        PreparedStatement ps1 =null;
        PreparedStatement ps2 =null;
        boolean uno =false;
        try{
            String consulta ="INSERT INTO Usuario (cuiUsuario, nombreUsuario, passwordUser, rango, nombres, apellidos)"
                + " VALUES (?,?,?,?,?,?);";
            String consulta2 ="INSERT INTO Perfil (cuiUsuario) VALUES (?);";
            ps1= conexion.prepareStatement(consulta);
            ps2= conexion.prepareStatement(consulta2);
            ps1.setString(1, cui);
            ps1.setString(2, usuario);
            ps1.setString(3, password);
            ps1.setString(4, rango);
            ps1.setString(5, nombre);
            ps1.setString(6, apellido);
            ps2.setString(1, cui);
            if(ps1.executeUpdate()==1){
                uno= true;
                System.out.println(uno);
                if(uno==true){
                    if(ps2.executeUpdate()==1){
                        System.out.println("guardado");
                        return true;
                    }
                }
            }
        }catch(SQLException e){
            System.out.println("error " +e);
        }/*finally{
             try {
                if(conexion!=null) conexion.close();
                if(ps1!= null) ps1.close();
            } catch (SQLException ex) {
                System.err.println("Error al guardar Datos" + ex);
            }
        }
        */
        return false;
    }
    public String validarRango(Connection conexion) {
        PreparedStatement validarRango = null;
        
        System.out.println(usuario);
        System.out.println(password);
        try {
            String consulta1 = "SELECT rango FROM Usuario WHERE nombreUsuario= ? AND passwordUser= ? ;";
            validarRango = conexion.prepareStatement(consulta1);
            validarRango.setString(1, usuario);
            validarRango.setString(2, password);
            ResultSet rs = validarRango.executeQuery();
            System.out.println(rs.first());
            String mirango = rs.getString(1);
            System.out.println(mirango);
            return mirango;
        } catch (SQLException e) {
            System.out.println("rango nulo  " + e.getSQLState());
           return "rango nulo";
        }
    }
     public String ObtenerNombre(Connection conexion, String cui){
        PreparedStatement validarNombre = null;
        
        System.out.println(usuario);
        System.out.println(password);
        try {
            String consulta1 = "SELECT nombres , apellidos  FROM Usuario WHERE cuiUsuario= ?;";
            validarNombre = conexion.prepareStatement(consulta1);
            validarNombre.setString(1, cui);
            ResultSet rs = validarNombre.executeQuery();
            System.out.println(rs.first());
            String miNombre = rs.getString("nombres");
            String miApellido= rs.getString("apellidos");
            return miNombre + " "+ miApellido;
        } catch (SQLException e) {
            System.out.println("nombre nulo " + e);
            return null;
        }
    }
    public String ValidarNombre(Connection conexion){
        PreparedStatement validarNombre = null;
        
        System.out.println(usuario);
        System.out.println(password);
        try {
            String consulta1 = "SELECT * FROM Usuario WHERE nombreUsuario= ?;";
            validarNombre = conexion.prepareStatement(consulta1);
            validarNombre.setString(1, usuario);
            ResultSet rs = validarNombre.executeQuery();
            System.out.println(rs.first());
            String nombre = rs.getString(1);
            return "";
        } catch (SQLException e) {
            System.out.println("nombre nulo " + e.getSQLState());
            return null;
        }
    }
    
    public String obtenerCUI(Connection conexion){
        PreparedStatement validarRango = null;
        
        System.out.println(usuario);
        System.out.println(password);
        try {
            String consulta1 = "SELECT cuiUsuario FROM Usuario WHERE nombreUsuario= ? AND passwordUser= ? ;";
            validarRango = conexion.prepareStatement(consulta1);
            validarRango.setString(1, usuario);
            validarRango.setString(2, password);
            ResultSet rs = validarRango.executeQuery();
            System.out.println(rs.first());
            String cui = rs.getString(1);
            return cui;
        } catch (SQLException e) {
            System.out.println("cui nulo " + e.getSQLState());
            return null;
        }
    }
    
    public String obtenernombre(Connection conexion, String cui){
        PreparedStatement validarRango = null;
        
        System.out.println(usuario);
        System.out.println(password);
        try {
            String consulta1 = "SELECT * FROM Usuario WHERE cuiUsuario= ? ;";
            validarRango = conexion.prepareStatement(consulta1);
            validarRango.setString(1, cui);
            ResultSet rs = validarRango.executeQuery();
            System.out.println(rs.first());
            String minombre = rs.getString("nombres") +" "+ rs.getString("apellidos");
            return minombre;
        } catch (SQLException e) {
            System.out.println("nombre nulo " + e);
            return null;
        }
    }
     
   
    
    public Date obtenerFecha(Connection conexion, String cui){
        PreparedStatement ps1 =null;
         try {
            String consulta1 = "SELECT * FROM Suscripcion WHERE cuiUsuario= ? AND idRevista = ? ;";
            ps1 = conexion.prepareStatement(consulta1);
            ps1.setString(1, cui);
            ps1.setString(1,cui);
            ResultSet rs = ps1.executeQuery();
            System.out.println(rs.first());
            Date fecha = rs.getDate("fecha");
            return fecha;
        } catch (SQLException e) {
            System.out.println("nombre nulo " + e.getSQLState());
            return null;
        }
    }
    
     Date ahora = new Date();
    SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
    public void validarSuscripcion(Connection conexion, String cui){
            
    }
        
  /*
     public static void main (String[] args){
        try {
            Usuario user = new Usuario();
            Conection conexion = new Conection();
            usuario = "MarianoMorales";
            password = "Morales.123";
            String consulta1 = "SELECT rango FROM Usuario WHERE nombreUsuario = 'MarianoMorales' AND passwordUser= 'Morales123';";
            PreparedStatement validarRango = conexion.getConexion().prepareStatement(consulta1);
            ResultSet rs = validarRango.executeQuery();
            System.out.println(rs.first());
            String mirango = rs.getString(1);
            System.out.println(mirango);
            try {
                System.out.println(conexion.getConexion().isClosed());
            } catch (SQLException ex) {
                Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }*/
}

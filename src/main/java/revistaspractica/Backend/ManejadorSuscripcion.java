
package revistaspractica.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManejadorSuscripcion {
    private String fechaSuscripcion;
    private String cuiCliente;
    private int idRevista;
    private int costo;

    public String getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(String fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public String getCuiCliente() {
        return cuiCliente;
    }

    public void setCuiCliente(String cuiCliente) {
        this.cuiCliente = cuiCliente;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
    
    public boolean Suscribir(Connection conexion, String cui, int idRevista, String fecha) {
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        System.out.println(cui + "suscribir");
        boolean uno = false;
        System.out.println(cui+ "cui");
        try {
            String consulta = "INSERT INTO Suscripcion(cuiUsuario, idRevista, fechaSuscripcion)"
                    + " VALUES (?,?,'" + fecha + "');";
            ps2 = conexion.prepareStatement(consulta);
            ps2.setString(1, cui);
            ps2.setInt(2, idRevista);
            if (ps2.executeUpdate() == 1) {
                if(Pagar( conexion, cui, idRevista, fecha, ObtenerIDSuscripcion(conexion, cui, idRevista))==true){
                    System.out.println("guardado");
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
            return false;
        }
    }
    
    public int ObtenerIDSuscripcion(Connection conexion, String cui, int idRevista){
        PreparedStatement ps1 = null;
         try {
            String consulta1 = "SELECT idSuscripcion FROM Suscripcion WHERE cuiUsuario= ? AND idRevista = ? ;";
            ps1 = conexion.prepareStatement(consulta1);
            ps1.setString(1, cui);
            ps1.setInt(2,idRevista);
            ResultSet rs = ps1.executeQuery();
            System.out.println(rs.first());
            int idSuscripcion = rs.getInt("idSuscripcion");
            return idSuscripcion;
        } catch (SQLException e) {
            System.out.println("nombre nulo " + e.getSQLState());
            return 0;
        }
         
    }
    
    public boolean Pagar(Connection conexion, String cui, int idRevista, String fecha, int idSuscripcion){
        PreparedStatement ps1 = null;
        try {
            String consulta2 = "INSERT INTO Pagar (fechaPago, idSuscripcion, idRevista, cuiUsuario)"
                    + " VALUES ('" + fecha + "',?,?,?);";
            ps1 = conexion.prepareStatement(consulta2);
            ps1.setInt(1, idSuscripcion);
            ps1.setInt(2, idRevista);
            ps1.setString(3, cui);
            ps1.executeUpdate();
            return true;    
        } catch (SQLException e) {
            System.out.println("error " + e);
            return false;
        }
    }
}

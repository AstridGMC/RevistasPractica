
package revistaspractica.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Categoria {
    protected int nombre;
    protected String tipo = "categoria";

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Categoria() {
    }
     PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    ResultSet rs = null;
    
    public String ObtenerCategoria(Connection con, int idRevista){
        String sql = "SELECT nombre FROM Tiene WHERE tipo = 'Categoria' AND idRevista= ?;";
        try {
            ps1 = con.prepareStatement(sql);
            ps1.setInt(1, idRevista);
            rs = ps1.executeQuery();
            rs.first();
            String categoria = rs.getString("nombre");
           return categoria;
        } catch (SQLException e) {
            System.out.println("no se encontro revista " + e);
            return null;
        }
        
    }
}

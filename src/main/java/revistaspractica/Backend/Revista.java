package revistaspractica.Backend;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

public class Revista {

    private String revistaID;
    private String CuiUsuario;
    private int cuotaSuscripcion;
    private String nombre;
    private String descripcion;
    private String fecha;
    private int likes;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private InputStream pdf;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getLikes() {
        return likes;
    }

    public void setLinkes(int linkes) {
        this.likes = linkes;
    }

    public InputStream getPdf() {
        return pdf;
    }

    public void setPdf(InputStream pdf) {
        this.pdf = pdf;
    }

    public String getRevistaID() {
        return revistaID;
    }

    public void setRevistaID(String revistaID) {
        this.revistaID = revistaID;
    }

    public String getCuiUsuario() {
        return CuiUsuario;
    }

    public void setCuiUsuario(String CuiUsuario) {
        this.CuiUsuario = CuiUsuario;
    }

    public int getCuotaSuscripcion() {
        return cuotaSuscripcion;
    }

    public void setCuotaSuscripcion(int cuotaSuscripcion) {
        this.cuotaSuscripcion = cuotaSuscripcion;
    }

    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    ResultSet rs = null;

    public void SubirRevista(Connection conexion, String cui, HttpServletResponse response) {

        boolean uno = false;
        try {
            //Date miFecha = dateFormat.parse(fecha);
            String consulta = "INSERT INTO Crea (cuotaSuscripcion, fecha, cuiUsuario)"
                    + " VALUES (?,'"+fecha+"',?);";
            String consulta2 = "INSERT INTO Revista (nombreRevista, DescripcionRevista, documento, cuiEditor) VALUES (?,?,?,?);";
            ps2 = conexion.prepareStatement(consulta2);
            ps2.setString(1, nombre);
            ps2.setString(2, descripcion);
            ps2.setBlob(3, pdf);
            ps2.setString(4, cui);
            ps1 = conexion.prepareStatement(consulta);
            ps1.setInt(1, cuotaSuscripcion);
            ps1.setString(2, CuiUsuario);
            
            if ( ps2.executeUpdate()== 1) {
                uno = true;
                System.out.println(uno);
                if (uno == true) {
                    if (ps1.executeUpdate() == 1) {
                        System.out.println("guardado");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("error No se ha guardado el archivo" + e);
        }

    }

    public String ObtenerId(Connection conexion, String cui) {
        PreparedStatement sql = null;

        try {
            String consulta1 = "SELECT idRevista FROM Revista WHERE nombreRevista= ?;";
            sql = conexion.prepareStatement(consulta1);
            sql.setString(1, cui);
            ResultSet rs = sql.executeQuery();
            System.out.println(rs.first());
            String idRevista = rs.getString(1);
            return idRevista;
        } catch (SQLException e) {
            System.out.println("idRevista nulo " + e.getSQLState());
            return null;
        }
    }

    public void LeerRevista(Connection con, String cui, HttpServletResponse response) {
        String idRevista = ObtenerId(con, cui);
        String sql = "select * from Revista where idRevista= " + idRevista + ";";
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setContentType("document/*");

        try {
            outputStream = response.getOutputStream();
            ps1 = con.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();
            //rs.getBytes("revista");
            inputStream = rs.getBinaryStream("revista");
            System.out.println("obteniendo revista");
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i = 0;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (Exception e) {
            System.out.println("error leyendo revista" + e);
        }
    }

    public ArrayList<Revista> ListarRevistas(Connection con) {
        Usuario user = new Usuario();
        ArrayList<Revista> list = new ArrayList<>();
        String sql = "SELECT * FROM Revista INNER JOIN Crea ON Revista.idRevista = Crea.idRevista";
        try {
            ps1 = con.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();

            while (rs.next()) {
                revistaID = rs.getString("idRevista");
                CuiUsuario = user.obtenernombre(con, rs.getString("cuiUsuario"));
                descripcion = rs.getString("descripcionRevista");
                likes = rs.getInt("likes");
            }
        } catch (SQLException e) {
            System.out.println("no se encontro revista " + e);
        }
        return list;
    }
    
    public void obtenerIdRevista(){
        
    }
    
     public void Suscribir(Connection conexion, String cui, int idRevista,String fecha, int idSuscripcion){
        PreparedStatement ps1 =null;
        PreparedStatement ps2 =null;
        boolean uno =false;
        try{
            String consulta ="INSERT INTO Suscribe (cuiUsuario, idRevista, fecha)"
                + " VALUES (?,?,'"+ fecha+"');";
            String consulta2 ="INSERT INTO Pagar (fecha, idSuscripcion, idRevista, cuiUsuario)"
                + " VALUES ('"+fecha+"',?,?,?);";
            ps1= conexion.prepareStatement(consulta);
            ps1.setString(1, cui);
            ps1.setInt(2, idRevista);
            ps2.setInt(1, idSuscripcion);
            ps2.setInt(2, idSuscripcion);
            if(ps1.executeUpdate()==1){
                uno= true;
                System.out.println("guardado");
            }
        }catch(SQLException e){
            System.out.println("error " +e);
        }
    }
}

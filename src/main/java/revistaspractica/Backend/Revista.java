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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

public class Revista {

    private int revistaID;
    private String cuiUsuario;
    private String escritor;
    private int cuotaSuscripcion;
    private String nombre;
    private String descripcion;
    private String fecha;
    private String Categoria;
    private String comentario;
    private Double ganancia;
    private int likes;
    private ArrayList<Usuario> suscriptores;
    private ArrayList<String> comentarios;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private InputStream pdf;

    public Revista() {
    }
    
    

    public String getEscritor() {
        return escritor;
    }

    public void setEscritor(String escritor) {
        this.escritor = escritor;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public ArrayList<Usuario> getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(ArrayList<Usuario> suscriptores) {
        this.suscriptores = suscriptores;
    }

    
    
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

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public InputStream getPdf() {
        return pdf;
    }

    public void setPdf(InputStream pdf) {
        this.pdf = pdf;
    }

    public int getRevistaID() {
        return revistaID;
    }

    public void setRevistaID(int revistaID) {
        this.revistaID = revistaID;
    }

    public String getCuiUsuario() {
        return cuiUsuario;
    }

    public void setCuiUsuario(String cuiUsuario) {
        this.cuiUsuario = cuiUsuario;
    }

    public int getCuotaSuscripcion() {
        return cuotaSuscripcion;
    }

    public void setCuotaSuscripcion(int cuotaSuscripcion) {
        this.cuotaSuscripcion = cuotaSuscripcion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Double getGanancia() {
        return ganancia;
    }

    public void setGanancia(Double ganancia) {
        this.ganancia = ganancia;
    }

    public ArrayList<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<String> comentarios) {
        this.comentarios = comentarios;
    }
    
    

    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    ResultSet rs = null;
     ResultSet rs2 = null;

    public void SubirRevista(Connection conexion, String cui, HttpServletResponse response) {

        boolean uno = false;
        try {
            //Date miFecha = dateFormat.parse(fecha);
            String consulta = "INSERT INTO Crea (cuotaSuscripcion, fecha, cuiEditor)"
                    + " VALUES (?,'" + fecha + "',?);";
            String consulta2 = "INSERT INTO Revista (nombreRevista, DescripcionRevista, documento, cuiEditor) VALUES (?,?,?,?);";
            ps2 = conexion.prepareStatement(consulta2);
            ps2.setString(1, nombre);
            ps2.setString(2, descripcion);
            ps2.setBlob(3, pdf);
            ps2.setString(4, cui);
            ps1 = conexion.prepareStatement(consulta);
            ps1.setInt(1, cuotaSuscripcion);
            ps1.setString(2, cuiUsuario);

            if (ps2.executeUpdate() == 1) {
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

    public String guardarCategoria(Connection conexion, String categoria) {
        String consulta2 = "INSERT INTO CategoriaYEtiquetas (nombre, tipo) VALUES (?,?);";
        try {
            ps2 = conexion.prepareStatement(consulta2);
            ps2.setString(1, categoria);
            ps2.setString(2, "Categoria");
            ps2.executeUpdate();
            return "guardado";
        } catch (SQLException ex) {
            System.out.println("no se puede guardar categoria " + ex.getSQLState());
            return "noGuardado";
        }

    }

    public void agregarCategoria(Connection conexion, String nombreRevista, String nombreEtiqueta) {
        String consulta2 = "INSERT INTO Tiene (nombre, idRevista) VALUES (?,?);";
        try {
            ps2 = conexion.prepareStatement(consulta2);
            ps2.setString(1, nombreEtiqueta);
            ps2.setString(2, ObtenerId(conexion, nombreRevista));
            ps2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("no se puede agregar categoria    " + ex);
        }

    }

    public String ObtenerId(Connection conexion, String nombre) {
        PreparedStatement sql = null;

        try {
            String consulta1 = "SELECT idRevista FROM Revista WHERE nombreRevista= ?;";
            sql = conexion.prepareStatement(consulta1);
            sql.setString(1, nombre);
            ResultSet rs = sql.executeQuery();
            System.out.println(rs.first());
            String idRevista = rs.getString(1);
            return idRevista;
        } catch (SQLException e) {
            System.out.println("idRevista nulo " + e.getSQLState());
            return null;
        }
    }

    public void LeerRevista(Connection con, int idRevista, HttpServletResponse response) {

        //String idRevista = ObtenerId(con, cui);
        String sql = "select * from Revista where idRevista= " + idRevista + ";";
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setContentType("application/pdf");

        try {
            outputStream = response.getOutputStream();
            ps1 = con.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();
            //rs.getBytes("revista");
            inputStream = rs.getBinaryStream("documento");
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

    public Revista detallarRevista(Connection con, int idRevista, Revista miRevista) {
        Usuario user = new Usuario();
        Categoria categoria = new Categoria();
        String sql = "SELECT * FROM Revista INNER JOIN Crea ON Revista.idRevista = Crea.idRevista WHERE Revista.idRevista= ?;";

        try {
            ps1 = con.prepareStatement(sql);
            ps1.setInt(1, idRevista);
            rs = ps1.executeQuery();
            rs.first();
            miRevista.setNombre(rs.getString("nombreRevista"));
            miRevista.setEscritor(user.obtenernombre(con, rs.getString("cuiEditor")));
            miRevista.setCuiUsuario(rs.getString("cuiEditor"));
            miRevista.setCuotaSuscripcion(rs.getInt("cuotaSuscripcion"));
            miRevista.setDescripcion(rs.getString("descripcionRevista"));
            miRevista.setLikes(rs.getInt("likes"));
            miRevista.setCategoria(categoria.ObtenerCategoria(con, idRevista));
            miRevista.setRevistaID(idRevista);
            return miRevista;
        } catch (SQLException e) {
            System.out.println("error leyendo revista" + e);
            return null;
        }
    }

    public ArrayList<Revista> ListarRevistasEditor(Connection con, String cui) {
        Usuario user = new Usuario();
        Revista miRevista;
        ArrayList<Revista> list = new ArrayList<>();
        String sql = "SELECT * FROM Revista INNER JOIN Crea ON Revista.idRevista = Crea.idRevista WHERE Revista.cuiEditor = ?;";
        try {
            ps1 = con.prepareStatement(sql);
            ps1.setString(1, cui);
            rs = ps1.executeQuery();
            rs.first();
            System.out.println("lololo" + user.obtenernombre(con, cui));
            miRevista = new Revista();

            miRevista.setNombre(rs.getString("nombreRevista"));
            miRevista.setEscritor(user.obtenernombre(con, rs.getString("cuiEditor")));
            miRevista.setCuiUsuario(cui);
            miRevista.setCuotaSuscripcion(rs.getInt("cuotaSuscripcion"));
            miRevista.setDescripcion(rs.getString("descripcionRevista"));
            miRevista.setLikes(rs.getInt("likes"));
            list.add(miRevista);

            while (rs.next()) {

                miRevista = new Revista();
                miRevista.setNombre(rs.getString("nombreRevista"));
                miRevista.setEscritor(user.obtenernombre(con, rs.getString("cuiEditor")));
                miRevista.setCuiUsuario(cui);
                miRevista.setCuotaSuscripcion(rs.getInt("cuotaSuscripcion"));
                miRevista.setDescripcion(rs.getString("descripcionRevista"));
                miRevista.setLikes(rs.getInt("likes"));
                list.add(miRevista);
            }

        } catch (SQLException e) {
            System.out.println("no se encontro revista " + e);
        }
        return list;
    }

    public ArrayList<Revista> ListarRevistasSuscriptor(Connection con, String cui) {
        Usuario user = new Usuario();
        Revista miRevista;
        ArrayList<Revista> list = new ArrayList<>();
        String sql = "SELECT idRevista FROM Suscripcion WHERE cuiUsuario = ?";
        try {
            ps1 = con.prepareStatement(sql);
            ps1.setString(1, cui);
            rs2 = ps1.executeQuery();
       
            while (rs2.next()) {
                System.out.println(rs2.getRow());
                miRevista = new Revista();
                miRevista = detallarRevista(con, rs2.getInt("idRevista"), miRevista);
                miRevista.setRevistaID(rs.getInt("idRevista"));
                System.out.println(rs2.getInt("idRevista"));
                list.add(miRevista);
            }
        } catch (SQLException e) {
            System.out.println("no se encontro revista " + e);
        }
        return list;
    }

    public ArrayList<Revista> ListarRevistas(Connection con, String cui) {
        Revista miRevista;
        Suscriptor suscriptor = new Suscriptor();
        Usuario user = new Usuario();
        ArrayList<Revista> list = new ArrayList<>();
        String sql = "SELECT * FROM Revista INNER JOIN Crea ON Revista.idRevista = Crea.idRevista";
        try {
            ps1 = con.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();
            if ("Procesada".equals(rs.getString("estado"))) {
                miRevista = new Revista();
                miRevista.setRevistaID(rs.getInt("idRevista"));
                if (suscriptor.verificarSuscripcion(con, cui, miRevista)==true) {
                    miRevista.setNombre(rs.getString("nombreRevista"));
                    miRevista.setRevistaID(rs.getInt("idRevista"));
                    miRevista.setCuiUsuario(rs.getString("cuiEditor"));
                    miRevista.setEscritor(user.obtenernombre(con, rs.getString("cuiEditor")));
                    miRevista.setDescripcion(rs.getString("descripcionRevista"));
                    miRevista.setCuotaSuscripcion(rs.getInt("cuotaSuscripcion"));
                    miRevista.setLikes(rs.getInt("likes"));
                    list.add(miRevista);
                }
            }
            while (rs.next()) {
                if ("Procesada".equals(rs.getString("estado"))) {
                    miRevista = new Revista();
                    miRevista.setRevistaID(rs.getInt("idRevista"));
                    if (suscriptor.verificarSuscripcion(con, cui, miRevista)==true) {
                        
                        miRevista.setNombre(rs.getString("nombreRevista"));
                        miRevista.setCuiUsuario(rs.getString("cuiEditor"));
                        miRevista.setEscritor(user.obtenernombre(con, rs.getString("cuiEditor")));
                        miRevista.setDescripcion(rs.getString("descripcionRevista"));
                        miRevista.setCuotaSuscripcion(rs.getInt("cuotaSuscripcion"));
                        miRevista.setLikes(rs.getInt("likes"));
                        list.add(miRevista);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("no se encontro revista " + e);
        }
        return list;
    }

    /*
    public String obtenerIdRevista(String nombre, Connection con){
         String sql = "SELECT idRevista FROM  Revistas WHERE nombreRevista = ?;";
        try {
            ps1 = con.prepareStatement(sql);
            ps1.setString(1, nombre);
            rs = ps1.executeQuery();
            rs.first();
            return rs.getString("idRevista");
        } catch (SQLException e) {
            System.out.println("no se encontro revista " + e);
            return null;
        }
    }*/
    public ArrayList<String> ListarCategorias(Connection con) {
        ArrayList<String> listaCategorias = new ArrayList<>();
        String sql = "SELECT * FROM  CategoriaYEtiquetas WHERE tipo = 'Categoria';";
        try {
            ps1 = con.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();
            listaCategorias.add(rs.getString("nombre"));
            while (rs.next()) {
                listaCategorias.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println("no se encontro revista " + e);
        }
        return listaCategorias;
    }

    public ArrayList<Revista> LeerComentarios(Connection con, int idRevista) {
        Revista miRevista;
        ArrayList<Revista> comentariosRevista = new ArrayList<>();
        String sql = "SELECT * FROM  Comenta WHERE idRevista = ?;";
        try {

            Usuario user = new Usuario();
            ps1 = con.prepareStatement(sql);
            ps1.setInt(1, idRevista);
            rs = ps1.executeQuery();
            rs.first();
            miRevista = new Revista();
            miRevista.setComentario(rs.getString("comentario"));
            miRevista.setEscritor(user.ObtenerNombre(con, rs.getString("cuiUsuario")));
            miRevista.setRevistaID(idRevista);
            comentariosRevista.add(miRevista);

            while (rs.next()) {
                miRevista = new Revista();
                miRevista.setComentario(rs.getString("comentario"));
                miRevista.setEscritor(user.ObtenerNombre(con, rs.getString("cuiUsuario")));
                miRevista.setRevistaID(idRevista);
                comentariosRevista.add(miRevista);
            }
        } catch (SQLException e) {
            System.out.println("no se encontro revista " + e);
        }
        return comentariosRevista;
    }

    public void ComentarRevista(Connection con, String cui, String comentario, int idRevista) {
        String consulta1 = "INSERT INTO Comenta (cuiUsuario, comentario, idRevista) VALUES (?,?,?);";
        try {
            ps2 = con.prepareStatement(consulta1);
            ps2.setString(1, cui);
            ps2.setString(2, comentario);
            ps2.setInt(3, idRevista);
            ps2.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("no se puede agregar comentario" + ex);
        }
    }

}

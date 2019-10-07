package revistaspractica.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class Editor extends Usuario {

    Date fechaActual = new Date();

    public Editor(HttpServletRequest request) {
        rango = "Editor";
    }

    public Editor() {
    }

    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    ResultSet rs = null;

    public int ingresoTotal(Connection con, String cuiUsuario) {

        String sql = "SELECT SUM(cuotaSuscripcion) as Cobro FROM Pagar JOIN Crea ON Pagar.idRevista = Crea.idRevista WHERE cuiEditor=?";
        try {

            Usuario user = new Usuario();
            ps1 = con.prepareStatement(sql);
            ps1.setString(1, cuiUsuario);
            rs = ps1.executeQuery();
            rs.first();
            return rs.getInt("Cobro");

        } catch (SQLException e) {
            System.out.println("no se encontro comentarios de la revista " + e);
            return 0;
        }
    }

    public ArrayList<Revista> RevistaPopular(Connection con, String cuiUsuario) {
        Revista miRevista;
        ArrayList<Revista> suscripciones = new ArrayList<>();
        String sql = "SELECT * FROM Revista JOIN Crea ON Revista.idRevista = Crea.idRevista WHERE Revista.cuiEditor=? ORDER BY likes DESC;";
        try {

            Usuario user = new Usuario();
            ps1 = con.prepareStatement(sql);
            ps1.setString(1, cuiUsuario);
            rs = ps1.executeQuery();
            rs.first();
            miRevista = new Revista();
            miRevista.setEscritor(user.ObtenerNombre(con, rs.getString("cuiEditor")));
            miRevista.setCuiUsuario(rs.getString("cuiEditor"));
            miRevista.setNombre(rs.getString("nombreRevista"));
            miRevista.setRevistaID(rs.getInt("idRevista"));
            miRevista.setLikes(rs.getInt("likes"));
            miRevista.setFecha(rs.getDate("fecha").toString());
            suscripciones.add(miRevista);
            int i=0;
            while (rs.next()|| i<5) {
                miRevista = new Revista();
                miRevista.setEscritor(user.ObtenerNombre(con, rs.getString("cuiEditor")));
                miRevista.setCuiUsuario(rs.getString("cuiEditor"));
                miRevista.setNombre(rs.getString("nombreRevista"));
                miRevista.setRevistaID(rs.getInt("idRevista"));
                miRevista.setLikes(rs.getInt("likes"));
                miRevista.setFecha(rs.getDate("fecha").toString());
                suscripciones.add(miRevista);
                i++;
            }
        } catch (SQLException e) {
            System.out.println("no se encontro comentarios de la revista " + e);
        }
        return suscripciones;

    }

    public ArrayList<Revista> SuscripcionesEditor(Connection con, String cuiUsuario) {
        Revista miRevista;
        ArrayList<Revista> suscripciones = new ArrayList<>();
        String sql = "SELECT * FROM  Suscripcion left JOIN Revista ON Suscripcion.idRevista = Revista.idRevista WHERE cuiEditor =?;";
        try {

            Usuario user = new Usuario();
            ps1 = con.prepareStatement(sql);
            ps1.setString(1, cuiUsuario);
            rs = ps1.executeQuery();
            rs.first();
            miRevista = new Revista();
            miRevista.setEscritor(user.ObtenerNombre(con, rs.getString("cuiUsuario")));
            miRevista.setCuiUsuario(rs.getString("cuiUsuario"));
            miRevista.setNombre(rs.getString("nombreRevista"));
            miRevista.setRevistaID(rs.getInt("idRevista"));
            miRevista.setFecha(rs.getDate("fechaSuscripcion").toString());
            miRevista.setGanancia(ObtenerGananciaSuscripcion(con, rs.getInt("idRevista")));
            suscripciones.add(miRevista);

            while (rs.next()) {
                miRevista = new Revista();
                miRevista.setEscritor(user.ObtenerNombre(con, rs.getString("cuiUsuario")));
                miRevista.setCuiUsuario(rs.getString("cuiUsuario"));
                miRevista.setNombre(rs.getString("nombreRevista"));
                miRevista.setRevistaID(rs.getInt("idRevista"));
                miRevista.setFecha(rs.getDate("fechaSuscripcion").toString());
                suscripciones.add(miRevista);
            }
        } catch (SQLException e) {
            System.out.println("no se encontro comentarios de la revista " + e);
        }
        return suscripciones;
    }

    public int costoPorDia(Connection con, String cuiUsuario) {

        String sql = "SELECT SUM(cuotaSuscripcion) as Costo FROM Crea WHERE cuiEditor=? AND idRevista";
        try {
            Usuario user = new Usuario();
            ps1 = con.prepareStatement(sql);
            ps1.setString(1, cuiUsuario);
            rs = ps1.executeQuery();
            rs.first();
            return rs.getInt("Costo");
        } catch (SQLException e) {
            System.out.println("no se encontro comentarios de la revista " + e);
            return 0;
        }
    }

    public ArrayList<Revista> GananciasRevistas(Connection con, String cuiUsuario) {
        Revista miRevista;
        ArrayList<Revista> comentariosRevista = new ArrayList<>();
        String sql = "SELECT * FROM  Revista WHERE cuiEditor=?;";
        try {

            Usuario user = new Usuario();
            ps1 = con.prepareStatement(sql);
            ps1.setString(1, cuiUsuario);
            rs = ps1.executeQuery();
            rs.first();
            miRevista = new Revista();
            miRevista.setEscritor(user.ObtenerNombre(con, cuiUsuario));
            miRevista.setNombre(rs.getString("nombreRevista"));
            miRevista.setRevistaID(rs.getInt("idRevista"));
            miRevista.setGanancia(ObtenerGananciaSuscripcion(con, rs.getInt("idRevista")));
            comentariosRevista.add(miRevista);

            while (rs.next()) {
                miRevista = new Revista();
                miRevista.setEscritor(user.ObtenerNombre(con, cuiUsuario));
                miRevista.setNombre(rs.getString("nombreRevista"));
                miRevista.setRevistaID(rs.getInt("idRevista"));
                miRevista.setGanancia(ObtenerGananciaSuscripcion(con, rs.getInt("idRevista")));
                comentariosRevista.add(miRevista);
            }
        } catch (SQLException e) {
            System.out.println("no se encontro comentarios de la revista " + e);
        }
        return comentariosRevista;
    }

    public ArrayList<Revista> Comentarios(Connection con, String cuiUsuario) {
        Revista miRevista;
        ArrayList<Revista> comentariosRevista = new ArrayList<>();
        String sql = "SELECT * FROM  Comenta JOIN Revista ON Comenta.idRevista= Revista.idRevista WHERE cuiEditor=?;";
        try {

            Usuario user = new Usuario();
            ps1 = con.prepareStatement(sql);
            ps1.setString(1, cuiUsuario);
            rs = ps1.executeQuery();
            rs.first();
            miRevista = new Revista();
            miRevista.setComentario(rs.getString("comentario"));
            miRevista.setEscritor(user.ObtenerNombre(con, rs.getString("cuiUsuario")));
            miRevista.setNombre(rs.getString("nombreRevista"));
            miRevista.setRevistaID(rs.getInt("idRevista"));
            comentariosRevista.add(miRevista);

            while (rs.next()) {
                miRevista = new Revista();
                miRevista.setComentario(rs.getString("comentario"));
                miRevista.setEscritor(user.ObtenerNombre(con, rs.getString("cuiUsuario")));
                miRevista.setNombre(rs.getString("nombreRevista"));
                miRevista.setRevistaID(rs.getInt("idRevista"));
                comentariosRevista.add(miRevista);
            }
        } catch (SQLException e) {
            System.out.println("no se encontro comentarios de la revista " + e);
        }
        return comentariosRevista;
    }

    public Date ObtenerFechaRevista(Connection con, int idRevista) {

        String sql = "SELECT fecha FROM Crea WHERE idRevista= ?;";

        try {
            ps1 = con.prepareStatement(sql);
            ps1.setInt(1, idRevista);
            rs = ps1.executeQuery();
            rs.first();
            Date fechaRevista = rs.getDate("fecha");
            return fechaRevista;
        } catch (SQLException e) {
            System.out.println("error leyendo revista" + e);
            return null;
        }
    }

    public Double ObtenerGananciaSuscripcion(Connection con, int idRevista) {
        Pagar pago = new Pagar();
        pago.getCoboServicio();
        String sql = "SELECT * FROM Pagar JOIN Crea ON Pagar.idRevista = Crea.idRevista WHERE idRevista= ?;";

        try {
            ps1 = con.prepareStatement(sql);
            ps1.setInt(1, idRevista);
            rs = ps1.executeQuery();
            rs.first();
            int cuotaSuscripcion = rs.getInt("cuotaSuscripcion");
            Double descuento = rs.getInt("cuotaSuscripcion") * 0.1;
            Double ganancia = cuotaSuscripcion - descuento;
            return ganancia;
        } catch (SQLException e) {
            System.out.println("error leyendo revista" + e);
            return null;
        }
    }

    public int DiferenciaDias(Connection con, int idRevista) {
        int dias = (int) ((fechaActual.getTime() - ObtenerFechaRevista(con, idRevista).getTime()) / 86400000);
        return dias;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revistaspractica.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author astridmc
 */
public class Administrador extends Usuario {

    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;

    public ArrayList<Revista> RevistasPopularesFecha(Connection con, String fecha1, String fecha2) {
        Revista miRevista;
        ArrayList<Revista> RevistasPopulares = new ArrayList<>();
        String sql = "SELECT idRevista, COUNT(2) numeroSuscripciones FROM Suscripcion WHERE fechaSuscripcion BETWEEN'" + fecha1 + "' AND '" + fecha2 + "'GROUP BY idRevista ORDER BY numeroSuscripciones DESC;";
        try {

            Usuario user = new Usuario();
            ps1 = con.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();
            miRevista = new Revista();
            miRevista = miRevista.detallarRevista(con, rs.getInt("idRevista"), miRevista);
            miRevista.setSuscriptores(Suscriptores(con, rs.getInt("idRevista")));
            RevistasPopulares.add(miRevista);
            
            while (rs.next()) {
                miRevista = new Revista();
                miRevista = miRevista.detallarRevista(con, rs.getInt("idRevista"), miRevista);
                miRevista.setSuscriptores(Suscriptores(con, rs.getInt("idRevista")));
                RevistasPopulares.add(miRevista);
            }
        } catch (SQLException e) {
            System.out.println("no se encontro comentarios de la revista " + e);
        }
        return RevistasPopulares;
    }

    public ArrayList<Revista> RevistasPopulares(Connection con) {
        Revista miRevista;
        ArrayList<Revista> RevistasPopulares = new ArrayList<>();
        String sql = "SELECT * FROM Revista JOIN Crea ON Revista.idRevista = Crea.idRevista ORDER BY likes DESC;";
        try {

            Usuario user = new Usuario();
            ps1 = con.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();
            miRevista = new Revista();
            miRevista.setEscritor(user.ObtenerNombre(con, rs.getString("cuiEditor")));
            miRevista.setCuiUsuario(rs.getString("cuiEditor"));
            miRevista.setNombre(rs.getString("nombreRevista"));
            miRevista.setRevistaID(rs.getInt("idRevista"));
            miRevista.setLikes(rs.getInt("likes"));
            miRevista.setSuscriptores(Suscriptores(con, rs.getInt("idRevista")));
            RevistasPopulares.add(miRevista);
            while (rs.next()) {
                miRevista = new Revista();
                miRevista.setEscritor(user.ObtenerNombre(con, rs.getString("cuiEditor")));
                miRevista.setCuiUsuario(rs.getString("cuiEditor"));
                miRevista.setNombre(rs.getString("nombreRevista"));
                miRevista.setRevistaID(rs.getInt("idRevista"));
                miRevista.setLikes(rs.getInt("likes"));
                miRevista.setSuscriptores(Suscriptores(con, rs.getInt("idRevista")));
                RevistasPopulares.add(miRevista);

            }
        } catch (SQLException e) {
            System.out.println("no se encontro revistasPopulares de la revista " + e);
        }
        return RevistasPopulares;
    }

    public ArrayList<Usuario> Suscriptores(Connection con, int idRevista) {
        Usuario usuario;
        ArrayList<Usuario> suscriptores = new ArrayList<>();
        String sql = "SELECT * FROM Suscripcion LEFT JOIN  Usuario ON Suscripcion.cuiUsuario = Usuario.cuiUsuario WHERE Suscripcion.idRevista =?;";
        try {

            ps1 = con.prepareStatement(sql);
            System.out.println(idRevista);
            ps1.setInt(1, idRevista);
            rs1 = ps1.executeQuery();
            rs1.first();
            if (rs1.getString("cuiUsuario") != null) {
                usuario = new Usuario();
                usuario.setCui(rs1.getString("cuiUsuario"));
                usuario.setNombre(rs1.getString("nombres"));
                usuario.setApellido(rs1.getString("apellidos"));
                usuario.setUsuario(rs1.getString("nombreUsuario"));
                usuario.setFecha(rs1.getDate("fechaSuscripcion").toString());
                suscriptores.add(usuario);
                while (rs1.next()) {
                    usuario = new Usuario();

                    usuario.setCui(rs1.getString("cuiUsuario"));
                    usuario.setNombre(rs1.getString("nombres"));
                    usuario.setApellido(rs1.getString("apellidos"));
                    usuario.setUsuario(rs1.getString("nombreUsuario"));
                    usuario.setFecha(rs1.getDate("fechaSuscripcion").toString());
                    suscriptores.add(usuario);
                }

            }
        } catch (SQLException e) {
            System.out.println("no se encontro suscriptores de la revista " + e);
            usuario = new Usuario();
            usuario.setCui("sin suscriptores");
            usuario.setNombre(("sin suscriptores"));
            usuario.setApellido(("sin suscriptores"));
            usuario.setUsuario(("sin suscriptores"));
            usuario.setFecha(("sin suscriptores").toString());
            suscriptores.add(usuario);

        }
        return suscriptores;
    }

    public ArrayList<Revista> RevistasSinCosto(Connection con) {
        Revista revista;
        
        ArrayList<Revista> revistas = new ArrayList<>();
        Usuario user = new Usuario();
        String sql = "SELECT * FROM Revista JOIN  Crea ON Revista.idRevista = Crea.idRevista;";
        try {

            ps1 = con.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();
            if (rs.getString("estado").equals("No Procesada")) {
                revista = new Revista();
                revista.setEscritor(user.ObtenerNombre(con, rs.getString("cuiEditor")));
                revista.setNombre(rs.getString("nombreRevista"));
                revista.setFecha(rs.getDate("fecha").toString());
                revista.setRevistaID(rs.getInt("idRevista"));
                revista.setCuiUsuario(rs.getString("cuiEditor"));
                revistas.add(revista);
            }
            while (rs.next()) {
                if (rs.getString("estado").equals("No Procesada")) {
                    revista = new Revista();
                    revista.setEscritor(user.ObtenerNombre(con, rs.getString("cuiEditor")));
                    revista.setNombre(rs.getString("nombreRevista"));
                    revista.setFecha(rs.getDate("fecha").toString());
                    revista.setRevistaID(rs.getInt("idRevista"));
                    revista.setCuiUsuario(rs.getString("cuiEditor"));
                    revistas.add(revista);
                }
            }
        } catch (SQLException e) {
            System.out.println("no se encontro revistas sin Costo por dia " + e);
            revista = new Revista();
            revista.setEscritor("no hay revistas para asgignar costo");
            revistas.add(revista);
        }
        return revistas;
    }

    public ArrayList<Revista> RevistasMasComentadas(Connection con) {
        Revista revista;
        ArrayList<Revista> revistas = new ArrayList<>();
        Usuario user = new Usuario();
        int idRevista;
        String sql = "SELECT idRevista, COUNT(2) numeroComentarios FROM Comenta GROUP BY idRevista ORDER BY numeroComentarios DESC ;";
        try {

            ps1 = con.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();
            revista = new Revista();
            idRevista = rs.getInt("idRevista");
            revista = revista.detallarRevista(con, idRevista, revista);
            revista.setComentarios(LeerComentarios(con, idRevista));
            revistas.add(revista);
            while (rs.next()) {
                revista = new Revista();
                idRevista = rs.getInt("idRevista");
                revista = revista.detallarRevista(con, idRevista, revista);
                revista.setComentarios(LeerComentarios(con, idRevista));
                revistas.add(revista);
            }
        } catch (SQLException e) {
            System.out.println("no se encontro revistas Mas comentadas " + e);
        }
        return revistas;
    }

    public ArrayList<Revista> RevistasMasComentadasFecha(Connection con, String fecha1, String fecha2) {
        Revista revista;
        ArrayList<Revista> revistas = new ArrayList<>();
        int idRevista;
        String sql = "SELECT idRevista, COUNT(2) numeroComentarios FROM Comenta WHERE fechaComentario BETWEEN '" + fecha1 + "' AND '" + fecha2 + "'GROUP BY idRevista ORDER BY numeroComentarios DESC ;";
        try {
            ps1 = con.prepareStatement(sql);
            rs = ps1.executeQuery();
            rs.first();
            revista = new Revista();
            idRevista = rs.getInt("idRevista");
            revista = revista.detallarRevista(con, idRevista, revista);
            revista.setComentarios(LeerComentarios(con, idRevista));
            revistas.add(revista);
            while (rs.next()) {
                revista = new Revista();
                idRevista = rs.getInt("idRevista");
                revista = revista.detallarRevista(con, idRevista, revista);
                revista.setComentarios(LeerComentarios(con, idRevista));
                revistas.add(revista);
            }
        } catch (SQLException e) {
            System.out.println("no se encontro revistas sin Costo por dia " + e);
        }
        return revistas;
    }

    public ArrayList<String> LeerComentarios(Connection con, int idRevista) {
        String comentario;
        ArrayList<String> comentariosRevista = new ArrayList<>();
        String sql = "SELECT * FROM  Comenta WHERE idRevista = ?;";
        try {

            Usuario user = new Usuario();
            ps2 = con.prepareStatement(sql);
            ps2.setInt(1, idRevista);
            rs1 = ps2.executeQuery();
            rs1.first();
            comentario = rs1.getString("comentario");
            comentariosRevista.add(comentario);

            while (rs1.next()) {
                comentario = rs1.getString("comentario");
                comentariosRevista.add(comentario);
            }
        } catch (SQLException e) {
            System.out.println("no se encontro comentarios " + e);
        }
        return comentariosRevista;
    }
    
    public void AsignarCosto(Connection con, int idRevista, Double costoDia){
        System.out.println(idRevista);
        System.out.println(costoDia);
        String sql="UPDATE Crea SET costoDia = ? WHERE idRevista= '"+idRevista+"';";
        String sql2="UPDATE Crea SET Estado = 'Procesada' WHERE idRevista= '"+idRevista+"';";
        try {
            ps1=con.prepareStatement(sql);
            ps2=con.prepareStatement(sql2);
            ps1.setDouble(1, idRevista);
            ps1.executeUpdate();
            ps2.executeUpdate();
            
            System.out.println("costo Guardada");
        } catch (SQLException e) {
            System.out.println("error No se ha guardado elCosto"+ e);
        }
    
    }
}

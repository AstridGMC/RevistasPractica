package revistaspractica.Backend;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;

public class Perfil {
    
    private String descripcion;
    private String hobbies;
    private String gustos;
    private InputStream foto;
    private String cui;
    private String intereses;
    
    public Perfil() {
    }
    
    public Perfil(String descripcion, String hobbies, String gustos, InputStream foto) {
        this.descripcion = descripcion;
        this.hobbies = hobbies;
        this.gustos = gustos;
        this.foto = foto;
    }

    public String getIntereses() {
        return intereses;
    }

    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getHobbies() {
        return hobbies;
    }
    
    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
    
    public String getGustos() {
        return gustos;
    }
    
    public void setGustos(String gustos) {
        this.gustos = gustos;
    }
    
    public InputStream getFoto() {
        return foto;
    }
    
    public void setFoto(InputStream foto) {
        this.foto = foto;
    }
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public void obtenerFoto(Connection con, String cui, HttpServletResponse response) {
        String sql = "select * from Perfil where cuiUsuario= '" + cui + "';";
        InputStream inputStream = null;
        OutputStream outputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        response.setContentType("image/*");
        
        try {
            outputStream = response.getOutputStream();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.first();
            inputStream = rs.getBinaryStream("foto");
            System.out.println("obteniendo imagen");
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i=0;
            while ((i = bufferedInputStream.read()) != -1) {
                bufferedOutputStream.write(i);
            }
        } catch (Exception e) {
            System.out.println("error leyendo "+e);
        }
    }
    
    public void agregarImagen(Perfil user,Connection con){
        String sql="UPDATE  Perfil SET foto = ? WHERE cuiUsuario= '"+user.getCui()+"';";
        
        try {
            ps=con.prepareStatement(sql);
            ps.setBlob(1,user.getFoto());
            ps.executeUpdate();
            
            System.out.println("Foto Guardada");
        } catch (SQLException e) {
            System.out.println("error No se ha guardado la imagen"+ e);
        }
    }
    
    public void guardarPerfil(Perfil user,Connection con){
        String sql="UPDATE  Perfil SET descripcion = ?,hobbie = ?,"
                + "gustos = ?, intereses = ? WHERE cuiUsuario = '"+user.getCui()+"';";
        
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1,user.getDescripcion());
            ps.setString(2,user.getHobbies());
            ps.setString(3,user.getGustos());
            ps.setString(4,user.getIntereses());
            ps.executeUpdate();
            
            System.out.println("perfil Guardado");
        } catch (SQLException e) {
            System.out.println("error No se ha guardado el perfil"+ e);
        }
    }
    
    
    public Perfil VerPerfil(Connection con, String cui, Perfil miPerfil){
        String sql = "select * from Perfil where cuiUsuario= '" + cui + "';";
       
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.first();
            descripcion = rs.getString("descripcion");
            hobbies = rs.getString("hobbies");
            gustos= rs.getString("gustos");
            intereses= rs.getString("intereses");
            if(descripcion == null){
                descripcion= "no has ingresado aun tu descripcion";
            }
            if(hobbies  == null){
                gustos= "no has ingresado aun tus gustos";
            }
            if(intereses  == null){
                intereses= "no has ingresado aun tus intereses";
            }
            return miPerfil;
        } catch (SQLException e) {
            System.out.println("error leyendo Perfil"+e);
            return null;
        }
    }
}

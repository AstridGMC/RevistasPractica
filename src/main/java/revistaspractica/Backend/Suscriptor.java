
package revistaspractica.Backend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;


public class Suscriptor extends Usuario{

    public Suscriptor(HttpServletRequest request) {
        rango = "Suscriptor";
    }
    
     public Suscriptor() {
        
    }
     
    PreparedStatement ps1 = null;
    ResultSet rs = null;

    
    public boolean verificarSuscripcion(Connection con, String cui, Revista revista){
        Revista miRevista = new Revista();
        ArrayList<Revista> list = miRevista.ListarRevistasSuscriptor(con, cui);
        int i=list.size();
        for (int j = 0; j < list.size(); j++) {//Itera elementos del segundo ArrayList
                    if (list.get(j).getRevistaID()== revista.getRevistaID()) { //Compara si los valores son iguales.
                        i++;
                        System.out.println("no   " + list.get(j).getRevistaID());
                    }else if (list.get(j).getRevistaID()!= revista.getRevistaID()) { 
                        
                        System.out.println("SI  " + list.get(j).getRevistaID());
                    }
        }
        if(i==list.size()){
            return true;
        }else{
            return false;
        }
    }
    
    public void DarMeGusta(Connection con, int idRevista){
        String sql="UPDATE  Revista SET likes= likes +1 WHERE idRevista= '"+idRevista+"';";
        
        try {
            ps1=con.prepareStatement(sql);
            ps1.executeUpdate();
            
            System.out.println("meGusta");
        } catch (SQLException e) {
            System.out.println("error No se podido guardar el like "+ e);
        }
    }
    
       public void DarNoMeGusta(Connection con, int idRevista){
        String sql="UPDATE  Revista SET likes= likes -1 WHERE idRevista= '"+idRevista+"';";
        
        try {
            ps1=con.prepareStatement(sql);
            ps1.executeUpdate();
            
            System.out.println("meNoGusta");
        } catch (SQLException e) {
            System.out.println("error No se podido guardar el no me Gusta "+ e);
        }
    }
    
}

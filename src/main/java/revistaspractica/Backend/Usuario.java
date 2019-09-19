
package revistaspractica.Backend;

import javax.servlet.http.HttpServletRequest;
import revistasPractica.Conector.Autentificador;

public class Usuario {
    
    protected String nombre;
    protected String usuario;
    protected String rango;
    protected String password;

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

    public Usuario(HttpServletRequest request) {
        usuario = request.getParameter("usuario");
        password = request.getParameter("password");
        
        System.out.println(usuario);
         System.out.println(password);
    }
    
    public String validarRango(){
        Autentificador autentificador = new Autentificador();
        
        if (autentificador.ValidarRango(usuario, password) == 1) {
            return "editor.jsp" ;
        } else if (autentificador.ValidarRango(usuario, password) == 2) {
            return "suscriptor.jsp";
        } else if (autentificador.ValidarRango(usuario, password) == 3) {
            return"admin.jsp";
        } else {
            return "DocumentosWeb/LogIn.jsp";
        }
    }
}

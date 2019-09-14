
package revistaspractica.Backend;

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
    
    
}

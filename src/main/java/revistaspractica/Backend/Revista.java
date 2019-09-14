
package revistaspractica.Backend;

import java.util.Date;


public class Revista {
    
    private String nombre;
    private String Descripcion;
    private Date fecha;
    private int linkes;
    private int pdf;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getLinkes() {
        return linkes;
    }

    public void setLinkes(int linkes) {
        this.linkes = linkes;
    }

    public int getPdf() {
        return pdf;
    }

    public void setPdf(int pdf) {
        this.pdf = pdf;
    }
    
    
}

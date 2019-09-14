
package revistaspractica.Backend;

import java.util.Date;

public class manejadorSuscripcion {
    private Date fechaSuscripcion;
    private String cuiCliente;
    private int idRevista;
    private int costo;

    public Date getFechaSuscripcion() {
        return fechaSuscripcion;
    }

    public void setFechaSuscripcion(Date fechaSuscripcion) {
        this.fechaSuscripcion = fechaSuscripcion;
    }

    public String getCuiCliente() {
        return cuiCliente;
    }

    public void setCuiCliente(String cuiCliente) {
        this.cuiCliente = cuiCliente;
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
    
    
}

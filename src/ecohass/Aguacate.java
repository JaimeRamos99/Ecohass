
package ecohass;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jaime
 */
public class Aguacate {
    private int id;
    private int zona;
    private int niveldecrecimiento;
    private int producido;
    private int altura;
    private String fechadesiembra;

    public String getFechadesiembra() {
        return fechadesiembra;
    }

    public void setFechadesiembra(String fechadesiembra) {
        this.fechadesiembra = fechadesiembra;
    }
    private ArrayList<String> comentarios = new ArrayList<String>();

    public Aguacate(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getZona() {
        return zona;
    }

    public int getNiveldecrecimiento() {
        return niveldecrecimiento;
    }

    public int getProducido() {
        return producido;
    }

    public int getAltura() {
        return altura;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public void setNiveldecrecimiento(int niveldecrecimiento) {
        this.niveldecrecimiento = niveldecrecimiento;
    }

    public void setProducido(int producido) {
        this.producido = producido;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
}

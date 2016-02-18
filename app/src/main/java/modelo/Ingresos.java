package modelo;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by jmio on 17/2/2016.
 */
public class Ingresos {
    private Integer _idIng;
    private String nombreIng;
    private double valorIng;
    private String fechaIng;

    public Ingresos() {
    }

    public Ingresos(Integer _idIng, String nombreIng, double valorIng, String fechaIng) {
        this._idIng = _idIng;
        this.nombreIng = nombreIng;
        this.valorIng = valorIng;
        this.fechaIng = fechaIng;
    }

    public Integer get_idIng() {
        return _idIng;
    }

    public void set_idIng(Integer _idIng) {
        this._idIng = _idIng;
    }

    public String getNombreIng() {
        return nombreIng;
    }

    public void setNombreIng(String nombreIng) {
        this.nombreIng = nombreIng;
    }

    public double getValorIng() {
        return valorIng;
    }

    public void setValorIng(double valorIng) {
        this.valorIng = valorIng;
    }

    public String getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(String fechaIng) {
        this.fechaIng = fechaIng;
    }
}

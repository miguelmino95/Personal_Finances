package modelo;

/**
 * Created by jmio on 17/2/2016.
 */
public class Gastos {
    private Integer _idGast;
    private String nombreGast;
    private double valorGast;
    private String fechaGast;

    public Gastos() {
    }

    public Gastos(Integer _idGast, String nombreGast, double valorGast, String fechaGast) {
        this._idGast = _idGast;
        this.nombreGast = nombreGast;
        this.valorGast = valorGast;
        this.fechaGast = fechaGast;
    }

    public Integer get_idGast() {
        return _idGast;
    }

    public void set_idGast(Integer _idGast) {
        this._idGast = _idGast;
    }

    public String getNombreGast() {
        return nombreGast;
    }

    public void setNombreGast(String nombreGast) {
        this.nombreGast = nombreGast;
    }

    public double getValorGast() {
        return valorGast;
    }

    public void setValorGast(double valorGast) {
        this.valorGast = valorGast;
    }

    public String getFechaGast() {
        return fechaGast;
    }

    public void setFechaGast(String fechaGast) {
        this.fechaGast = fechaGast;
    }
}

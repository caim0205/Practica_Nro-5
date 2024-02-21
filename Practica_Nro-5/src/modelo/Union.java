package modelo;

/**
 *
 * @author caim2
 */
public class Union {

    private Integer origen;
    private Integer destino;

    public Union(Integer origen, Integer destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public Integer getOrigen() {
        return origen;
    }

    public void setOrigen(Integer origen) {
        this.origen = origen;
    }

    public Integer getDestino() {
        return destino;
    }

    public void setDestino(Integer destino) {
        this.destino = destino;
    }

}

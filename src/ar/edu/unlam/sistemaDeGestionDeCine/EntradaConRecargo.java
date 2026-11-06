package ar.edu.unlam.sistemaDeGestionDeCine;

public class EntradaConRecargo extends Entrada{
	
	private Double porcentajeRecargo;

    public EntradaConRecargo(Funcion funcion, Asiento asiento, Double precioBase, Double porcentajeRecargo) {
        super(funcion, asiento, precioBase);
        this.porcentajeRecargo = porcentajeRecargo;
    }
    
    @Override
    public Double calcularPrecioVenta() {
        return this.precioBase + (this.precioBase * this.porcentajeRecargo / 100.0);
    }
    
}

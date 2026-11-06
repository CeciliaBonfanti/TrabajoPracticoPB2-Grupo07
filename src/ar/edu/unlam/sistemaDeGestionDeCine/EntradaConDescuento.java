package ar.edu.unlam.sistemaDeGestionDeCine;

public class EntradaConDescuento extends Entrada{

	private Double porcentajeDescuento;

    public EntradaConDescuento(Funcion funcion, Asiento asiento, Double precioBase, Double porcentajeDescuento) {
        super(funcion, asiento, precioBase);
        this.porcentajeDescuento = porcentajeDescuento;
    }
    
    @Override
    public Double calcularPrecioVenta() {
        return this.precioBase - (this.precioBase * this.porcentajeDescuento / 100.0);
    }
    
}

package ar.edu.unlam.sistemaDeGestionDeCine;

public abstract class Entrada implements Vendible{
private static Integer contadorCodigos = 1; // Para el autoincremental
    
    private Integer codigo;
    private Funcion funcion;
    private Asiento asiento;
    protected Double precioBase;

    public Entrada(Funcion funcion, Asiento asiento, Double precioBase) {
        this.codigo = contadorCodigos++;
        this.funcion = funcion;
        this.asiento = asiento;
        this.precioBase = precioBase;
    }
    
    public Integer getCodigo() {
        return codigo;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public Asiento getAsiento() {
        return asiento;
    }
    
    public static void reiniciarContador() {
        contadorCodigos = 1; // Resetear el estático
    }

    // El método calcularPrecioVenta queda abstracto o implementado según convenga
    @Override
    public abstract Double calcularPrecioVenta();
    
}

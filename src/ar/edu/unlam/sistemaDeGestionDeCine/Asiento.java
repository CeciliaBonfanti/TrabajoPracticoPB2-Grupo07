package ar.edu.unlam.sistemaDeGestionDeCine;

public class Asiento implements Comparable<Asiento> {

	private Integer fila;
	private Integer butaca;
	private Boolean estaOcupado;

	public Asiento(Integer fila, Integer butaca) {
		this.fila = fila;
		this.butaca = butaca;
		this.estaOcupado = false;
	}

	public Integer getFila() {
		return this.fila;
	}

	public Integer getButaca() {
		return this.butaca;
	}

	public Boolean getOcupado() {
		return this.estaOcupado;
	}

	public void ocupar() {
		this.estaOcupado = true;
	}

	@Override
	public int compareTo(Asiento o) {

		int compararFila = this.fila.compareTo(o.fila);

		if (compararFila != 0) {
			return compararFila;
		}

		return this.butaca.compareTo(o.butaca);
	}
}

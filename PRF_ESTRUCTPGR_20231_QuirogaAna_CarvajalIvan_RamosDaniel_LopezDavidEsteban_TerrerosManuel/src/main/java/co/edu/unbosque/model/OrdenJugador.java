package co.edu.unbosque.model;

public class OrdenJugador {
	private String nombre;
	private OrdenJugador siguiente;
	private OrdenJugador anterior;

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public OrdenJugador getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(OrdenJugador siguiente) {
		this.siguiente = siguiente;
	}

	public OrdenJugador getAnterior() {
		return anterior;
	}

	public void setAnterior(OrdenJugador anterior) {
		this.anterior = anterior;
	}

}

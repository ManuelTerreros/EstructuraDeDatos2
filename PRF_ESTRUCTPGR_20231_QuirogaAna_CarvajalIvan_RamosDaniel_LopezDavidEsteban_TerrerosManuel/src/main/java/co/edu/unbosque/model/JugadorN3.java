package co.edu.unbosque.model;

public class JugadorN3 {

	private Carta carta;
	private JugadorN3 siguiente;
	private JugadorN3 anterior;
	
	public JugadorN3(Carta carta) {
		this.carta=carta;
		siguiente=null;
		anterior=null;
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

	public JugadorN3 getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(JugadorN3 siguiente) {
		this.siguiente = siguiente;
	}

	public JugadorN3 getAnterior() {
		return anterior;
	}

	public void setAnterior(JugadorN3 anterior) {
		this.anterior = anterior;
	}
}

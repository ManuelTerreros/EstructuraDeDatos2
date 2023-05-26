package co.edu.unbosque.model;

public class JugadorN1 {

	private Carta carta;
	private JugadorN1 siguiente;
	private JugadorN1 anterior;
	
	public JugadorN1(Carta carta) {
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

	public JugadorN1 getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(JugadorN1 siguiente) {
		this.siguiente = siguiente;
	}

	public JugadorN1 getAnterior() {
		return anterior;
	}

	public void setAnterior(JugadorN1 anterior) {
		this.anterior = anterior;
	}
}

package co.edu.unbosque.model;

public class JugadorN2 {
	private Carta carta;
	private JugadorN2 siguiente;
	private JugadorN2 anterior;
	
	public JugadorN2(Carta carta) {
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

	public JugadorN2 getSiguiente() {
		return siguiente;
	}

	public void setSiguiente(JugadorN2 siguiente) {
		this.siguiente = siguiente;
	}

	public JugadorN2 getAnterior() {
		return anterior;
	}

	public void setAnterior(JugadorN2 anterior) {
		this.anterior = anterior;
	}

}

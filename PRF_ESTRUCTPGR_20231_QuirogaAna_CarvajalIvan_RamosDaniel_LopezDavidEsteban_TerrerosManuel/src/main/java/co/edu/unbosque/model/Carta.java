package co.edu.unbosque.model;

public class Carta {

	private int numero;
	private String color;
	
	public Carta(int numero, String color) {
		this.numero = numero;
		this.color = color;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Carta [numero=" + numero + ", color=" + color + "]\n";
	}
}

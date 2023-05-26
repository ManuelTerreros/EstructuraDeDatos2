package co.edu.unbosque.model;

import java.util.ArrayList;

public class ListaCartasJugador1 {
	JugadorN1 inicio;
	JugadorN1 fin;

	public ListaCartasJugador1() {
		inicio = fin = null;
	}

	public boolean vacia() {
		return inicio == null;
	}

	public void agregar(Carta carta) {
		JugadorN1 newNode = new JugadorN1(carta);

		if (inicio == null) {
			inicio = fin = newNode;
		} else {
			fin.setSiguiente(newNode);
			newNode.setAnterior(fin);
			fin = newNode;
		}
	}

	public ArrayList<Carta> mostrar() {
		ArrayList<Carta> lista = new ArrayList<Carta>();
		if (!vacia()) {
			JugadorN1 aux = inicio;

			while (aux != null) {
				lista.add(aux.getCarta());
				aux = aux.getSiguiente();
			}

			return lista;
		}
		
		return null;
	}
	
	public boolean buscarCarta(Carta carta) {
        JugadorN1 aux = inicio;
        int numero = carta.getNumero();
		String color = carta.getColor();
        while (aux != null) {
            if (aux.getCarta().getNumero()==numero && aux.getCarta().getColor().equals(color)) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

	public void eliminarCarta(Carta carta) {
		JugadorN1 aux = inicio;

		int numero = carta.getNumero();
		String color = carta.getColor();
		
		while (aux != null) {
			if (aux.getCarta().getNumero()==numero && aux.getCarta().getColor().equals(color)) {
				if (aux == inicio) {
					inicio = aux.getSiguiente();
					if (inicio != null) {
						inicio.setAnterior(null);
					}
				} else if (aux == fin) {
					fin = aux.getAnterior();
					fin.setSiguiente(null);
				} else {
					aux.getAnterior().setSiguiente(aux.getSiguiente());

					aux.getSiguiente().setAnterior(aux.getAnterior());
				}
				
				break;
			}

			aux = aux.getSiguiente();
		}
	}

}

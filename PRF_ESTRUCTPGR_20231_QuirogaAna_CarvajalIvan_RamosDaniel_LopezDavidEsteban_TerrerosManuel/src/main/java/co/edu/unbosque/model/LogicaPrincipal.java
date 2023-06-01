
package co.edu.unbosque.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import org.primefaces.PrimeFaces;

public class LogicaPrincipal {

	private PosicionJugador posJugador;
	private ArrayList<Carta> creacion;
	private Stack<Carta> montoRobar, montoDescartar;
	private String[] color;
	private String jug1, jug2, jug3;
	private ListaCartasJugador1 lista1;
	private ListaCartasJugador2 lista2;
	private ListaCartasJugador3 lista3;

	public LogicaPrincipal() {
		posJugador = new PosicionJugador();
		creacion = new ArrayList<>();
		color = new String[4];
		montoRobar = new Stack<Carta>();
		montoDescartar = new Stack<Carta>();
		lista1 = new ListaCartasJugador1();
		lista2 = new ListaCartasJugador2();
		lista3 = new ListaCartasJugador3();
		jug1 = jug2 = jug3 = "";
		generarCartaNumero();

	}

	public void elegirJugador(String juga1, String juga2, String juga3) {

		this.jug1 = juga1;
		this.jug2 = juga2;
		this.jug3 = juga3;
		System.out.println(jug1 + jug2 + jug3);

		List<String> jugadores = new ArrayList<>();
		jugadores.add(jug1);
		jugadores.add(jug2);
		jugadores.add(jug3);

		Collections.shuffle(jugadores, new Random());
		jug1 = jugadores.get(0);
		jug2 = jugadores.get(1);
		jug3 = jugadores.get(2);

	}

	public Carta sacarCarta() {
		Carta carta = montoRobar.pop();
		System.out.println(carta);
		return carta;

	}

	public void generarCartaNumero() {
		color[0] = "Amarillo";
		color[1] = "Rojo";
		color[2] = "Azul";
		color[3] = "Verde";
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) {
					Carta c1 = new Carta(j, color[i]);
					creacion.add(c1);
				} else {
					Carta c2 = new Carta(j, color[i]);
					Carta c3 = new Carta(j, color[i]);
					creacion.add(c2);
					creacion.add(c3);

				}
			}

			// las cartas con 10 equivalen a toma dos.
			// las cartas con 11 equivalen a reversa.
			// las cartas con 12 equivalen a pierde turno.

			for (int k = 10; k < 13; k++) {
				Carta c2 = new Carta(k, color[i]);
				Carta c3 = new Carta(k, color[i]);
				creacion.add(c2);
				creacion.add(c3);
			}
			// las cartas con 13 equivalen a cambio de color.
			// las cartas con 14 equivalen a toma cuatro.

			for (int l = 13; l < 15; l++) {
				Carta c1 = new Carta(l, "Negro");
				creacion.add(c1);
			}
		}

		int pos;
		int nCartas = creacion.size();

		for (int i = 0; i < nCartas; i++) {
			pos = (int) Math.floor(Math.random() * nCartas);
			while (montoRobar.contains(creacion.get(pos))) {
				pos = (int) Math.floor(Math.random() * nCartas);
			}
			montoRobar.push(creacion.get(pos));
		}
	}

	public void distribuirCartasYJugadores() {

		for (int i = 0; i < 7; i++) {
			lista1.agregar(montoRobar.pop());
			lista2.agregar(montoRobar.pop());
			lista3.agregar(montoRobar.pop());
		}

		posJugador.agregarInicio(jug1);
		posJugador.agregarInicio(jug2);
		posJugador.agregarInicio(jug3);

	}

	public void masDosmasCuatroCartas(int numero, String nombre) {

		if (jug1.equals(nombre)) {
			for (int i = 0; i < numero; i++) {
				lista1.agregar(montoRobar.pop());
			}
		} else if (jug2.equals(nombre)) {
			for (int j = 0; j < numero; j++) {
				lista2.agregar(montoRobar.pop());
			}
		} else if (jug3.equals(nombre)) {
			for (int k = 0; k < numero; k++) {
				lista3.agregar(montoRobar.pop());
			}
		}

	}

	public void robarCarta(String nombre, Carta car) {
		if (jug1.equals(nombre)) {
			lista1.agregar(car);
		} else if (jug2.equals(nombre)) {
			lista2.agregar(car);
		} else if (jug3.equals(nombre)) {
			lista3.agregar(car);
		}
	}

	public String cambioColor() {

		Random r = new Random();
		String colorAleatorio = color[r.nextInt(4)];

		return colorAleatorio;
	}

	// Este metodo sirve para poder modificar la pila de cartas de el jugador que
	// este seleccionando cartas
	public Carta elegirCarta(String nombre, Carta carta) {
//		int numero = carta.getNumero();
//		String color = carta.getColor();
		boolean encontrado = false;

		if (jug1.equals(nombre)) {
			if (lista1.buscarCarta(carta)) {
				lista1.eliminarCarta(carta);
			}
		}
		if (jug2.equals(nombre)) {
			if (lista2.buscarCarta(carta)) {
				lista2.eliminarCarta(carta);
			}
		}
		if (jug3.equals(nombre)) {
			if (lista3.buscarCarta(carta)) {
				lista3.eliminarCarta(carta);
			}
		}
		return carta;
	}

	// este metodo agrega cartas a la pila de el centro que es la de descartar, les
	// dejo a eleccion que quieren que retorne la pila o el array para que sepan
	// cual fue
	// la ultima carta que se agrego y conforme a eso escojer la imagen
	public void agregarPilaDescartar(Carta carta) {
		montoDescartar.push(carta);
	}

	public Carta quitarPrimeraCartaPilaRobar() {
		return montoRobar.pop();
	}

	public Carta consultarPilaDescartar() {
		Carta nue = montoDescartar.peek();
		return nue;
	}

	public ArrayList<Carta> mostrarCartasJugador(String nombre) {
		
		if (jug1.equals(nombre)) {
			return lista1.mostrar();
		} else if (jug2.equals(nombre)) {
			return lista2.mostrar();
		} else if (jug3.equals(nombre)) {
			return lista3.mostrar();
		}
		
		return null;

	}

	// METODOS PARA EL SENTIDO HORARIO DE LOS
	// JUGADORES----------------------------------------------------------------------------

	public String consultarHorarioAnterior(String nombre) {
		String ant = posJugador.jugadorAnterior(nombre);
		return ant;
	}

	public String consultarHorarioSiguiente(String nombre) {
		String sig = posJugador.jugadorSiguiente(nombre);
		return sig;
	}

	public String consultarHorarioSaltaTurno(String nombre) {
		String omi = posJugador.saltarTurno(nombre);
		return omi;
	}

	// METODOS PARA EL SENTIDO ANTI-HORARIO DE LOS
	// JUGADORES------------------------------------------------------------------------

	public String consultarContrarioAnterior(String nombre) {
		String ant = posJugador.jugadorSiguiente(nombre);
		return ant;
	}

	public String consultarContrarioSiguiente(String nombre) {
		String sig = posJugador.jugadorAnterior(nombre);
		return sig;
	}

	public String consultarContrarioSaltaTurno(String nombre) {
		String omi = posJugador.saltarTurnoContrario(nombre);
		return omi;
	}

	public String getJug1() {
		return jug1;
	}

	public void setJug1(String jug1) {
		this.jug1 = jug1;
	}

	public String getJug2() {
		return jug2;
	}

	public void setJug2(String jug2) {
		this.jug2 = jug2;
	}

	public String getJug3() {
		return jug3;
	}

	public void setJug3(String jug3) {
		this.jug3 = jug3;
	}

	public Stack<Carta> getMontoRobar() {
		return montoRobar;
	}

	public void setMontoRobar(Stack<Carta> montoRobar) {
		this.montoRobar = montoRobar;
	}

	public Stack<Carta> getMontoDescartar() {
		return montoDescartar;
	}

	public void setMontoDescartar(Stack<Carta> montoDescartar) {
		this.montoDescartar = montoDescartar;
	}

}

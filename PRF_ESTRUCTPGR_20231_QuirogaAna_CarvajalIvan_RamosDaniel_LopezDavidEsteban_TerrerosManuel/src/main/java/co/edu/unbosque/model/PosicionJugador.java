package co.edu.unbosque.model;

public class PosicionJugador {

	OrdenJugador inicio;
	OrdenJugador fin;
	String jugador, jugadorAnt, jugadorSig;

	
	public PosicionJugador() {
		inicio=fin=null; 
		jugador=null;
		jugadorAnt=null;
	}
	
	public boolean vacia() {
		return inicio==null;
	}
	
	public void agregarInicio(String nombre) {
		OrdenJugador nuevo = new OrdenJugador();

		nuevo.setNombre(nombre);
		if(vacia()) {
			inicio=nuevo;
			inicio.setSiguiente(inicio);
			//inicio.siguiente=inicio;
			inicio.setAnterior(fin);
			//inicio.anterior=fin;
			fin=nuevo;
		}else {
			fin.setSiguiente(nuevo);
			//fin.siguiente=nuevo;
			nuevo.setSiguiente(inicio);
			//nuevo.siguiente=inicio;
			fin=nuevo;
			inicio.setAnterior(fin);
			//inicio.anterior=fin;
		}
	}
	
	public String buscar(String nombre) {
		OrdenJugador actual = new OrdenJugador();
		actual=fin;
		boolean encontrado = false;
		do {
			if(actual.getNombre().equals(nombre)) {
				encontrado = true;
				jugador = actual.getNombre();
			}
			actual = actual.getSiguiente();
		}while(actual!=fin);	
		if(encontrado==true) {
			return jugador;
		} 

			return null;
	}
	
	public String jugadorSiguiente(String v) {
		OrdenJugador actual = new OrdenJugador();
		actual=inicio;
		boolean encontrado = false;
		do {
			if(actual.getNombre().equals(v)) {
				encontrado = true;
				jugador = actual.getNombre();
				jugadorSig = actual.getSiguiente().getNombre();
			}
			actual = actual.getSiguiente();
		}while(actual!=inicio);	
		
		if(encontrado==true) {
			return jugadorSig;
		}
		return null;
	}
	
	public String jugadorAnterior(String nombre) {
		OrdenJugador actual = new OrdenJugador();
		OrdenJugador anterior = new OrdenJugador();
		actual=inicio;
		anterior = fin;
		boolean encontrado = false;
		do {
			if(actual.getNombre().equals(nombre)) {
				encontrado = true;
				jugador = actual.getNombre();
				jugadorAnt = anterior.getNombre();
			}
			anterior = anterior.getSiguiente();
			actual = actual.getSiguiente();
			
		}while(actual!=inicio);	
		
		if(encontrado==true) {
			return jugadorAnt;
		}
		
		return null;
	}
	
	
	public void mostrar() {
		OrdenJugador actual = new OrdenJugador();
		actual=inicio;
		if(!vacia()) {
			do {
				System.out.println(actual.getNombre());
				actual = actual.getSiguiente();
			}while(actual!=inicio);
		}
	}
	
	public String saltarTurno(String nombre) {
		
		String jug = jugadorSiguiente(nombre);
		String n = jug;
		String jugadorOmitido = jugadorSiguiente(n);
		
		return jugadorOmitido;
	}
	
	public String saltarTurnoContrario(String nombre) {
		String jug = jugadorAnterior(nombre);
		String n = jug;
		String jugadorOmitido = jugadorAnterior(n);
		
		return jugadorOmitido;
	}
	
}

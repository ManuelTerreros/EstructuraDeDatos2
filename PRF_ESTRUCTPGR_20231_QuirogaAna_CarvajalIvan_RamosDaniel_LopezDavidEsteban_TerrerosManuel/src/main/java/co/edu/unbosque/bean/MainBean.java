package co.edu.unbosque.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped; // o el alcance que desees utilizar


import co.edu.unbosque.model.Carta;
import co.edu.unbosque.model.LogicaPrincipal;

@ApplicationScoped
@ManagedBean
public class MainBean implements Serializable{
	
	private static final long serialVersionUID = -2152389656664659476L;
	private String usuario1="", usuario2="", aprendiz="",cart1="", cart2="",cart3="",cart4="",cart5="", usuarioJugando="";
	private LogicaPrincipal log=new LogicaPrincipal();
	private int turn= 1;
	private Carta car1=new Carta(0, null), car2=new Carta(0, null), car3=new Carta(0, null), car4=new Carta(0, null), car5=new Carta(0, null);
	private int contCarMax=0 , contCarMin=0, resta=1, respaldo=contCarMin, consta=0;
	private ArrayList<Carta> cartas= new ArrayList<>();
	private ArrayList<Carta> movCartas = new ArrayList<>();
	private boolean direccionReloj = true;
	
	
	public String navegar() {
		log.elegirJugador(usuario1, usuario2, aprendiz);
		log.distribuirCartasYJugadores();
		primUsuarioJugando();
		System.out.println(usuarioJugando);
		hacerMontoCartaJugador();
		ponerImagenBotonesJugador();
		log.agregarPilaDescartar(log.quitarPrimeraCartaPilaRobar());
		System.out.println("------***************------");
		System.out.println(usuario1);
		System.out.println(log.mostrarCartasJugador(usuario1));
		System.out.println(usuario2);
		System.out.println(log.mostrarCartasJugador(usuario2));
		System.out.println(aprendiz);
		System.out.println(log.mostrarCartasJugador(aprendiz));
		System.out.println("------------***************-----------");	
		System.out.println(cartas.get(0).getColor()+ cartas.get(0).getNumero());	
		
		

		return "juego.xhtml";
		
		
	}
	
	
	
	
	public String getUsuarioJugando() {
		return usuarioJugando;
	}




	public void setUsuarioJugando(String usuarioJugando) {
		this.usuarioJugando = usuarioJugando;
	}




	public String mostrarUltimaCartaMazo() {
		Carta car = log.consultarPilaDescartar();
		String res = "";
		 // No me va a dejar a hacer la conversion a booleano
		res=cambiarAImagen(car);
		
		return res;
	}
	
	public void ponerImagenBotonesJugador() {
		car1=cartas.get(0);
		car2=cartas.get(1);
		car3=cartas.get(2);
		car4=cartas.get(3);
		car5=cartas.get(4);
		cart1 = cambiarAImagen(car1);
		cart2 = cambiarAImagen(car2);
		cart3 = cambiarAImagen(car3);
		cart4 = cambiarAImagen(car4);
		cart5 = cambiarAImagen(car5);
	}
	
	
	
	public String adelantarCartasJugador() {
		
		
		if(contCarMin==contCarMax-1) {
			contCarMin = -1;
		}
		
		
		contCarMin =contCarMin+1;
		car1 = car2;
		car2= car3;
		car3 = car4;
		car4 = car5;
		car5 = cartas.get(contCarMin);
		
		cart1 = cambiarAImagen(car1);
		cart2 = cambiarAImagen(car2);
		cart3 = cambiarAImagen(car3);
		cart4 = cambiarAImagen(car4);
		cart5 = cambiarAImagen(car5);
		System.out.println("El boton funciona");
		System.out.println("carta1"+car1);
		System.out.println("carta2"+car2);
		System.out.println("carta3"+car3);
		System.out.println("carta4"+car4);
		System.out.println("carta5"+car5);


		
		return "";
		
	}
	
	public String atrasCartasJugador() {

		
		int resp, otro;
		
		if(resta==contCarMax-1) {
			resta =1;
		}
		
		otro = consta-resta;
		
		resp = contCarMax + otro;
		
		car5=car4;
		car4=car3;
		car3=car2;
		car2=car1;
		car1=cartas.get(resp);
		
		
		resta++;
		
		
		return "";
		
	}
	
	public String robarCartaMazoRobar() {
		Carta cr =log.sacarCarta();
		log.robarCarta(usuarioJugando, cr);
		System.out.println("Cartas jugador con una robada");
		System.out.println(log.mostrarCartasJugador(usuarioJugando));
		cartas = log.mostrarCartasJugador(usuarioJugando);
		contCarMax = cartas.size();
	
		return "";
	}
	
	public String elegirCartaC1() {
		
		System.out.println("Nooooooooooooooo");
		if(log.getMontoDescartar().peek().getColor().equals(car1.getColor()) || log.getMontoDescartar().peek().getNumero()==car1.getNumero() ) {
		log.elegirCarta(usuarioJugando, car1);
		log.agregarPilaDescartar(car1);
		comprobarSiSeUsaComodin(car1);
		if(pierdeTurno(car1)==false) {
		String usunuevo=pasarAlOtroTurno();
		cartas=log.mostrarCartasJugador(usunuevo);
		contCarMax=cartas.size();
		ponerImagenBotonesJugador();}
		
		System.out.println("------***************------");
		System.out.println(usuario1);
		System.out.println(log.mostrarCartasJugador(usuario1));
		System.out.println(usuario2);
		System.out.println(log.mostrarCartasJugador(usuario2));
		System.out.println(aprendiz);
		System.out.println(log.mostrarCartasJugador(aprendiz));
		System.out.println("------------***************-----------");	
		}else {
			
		}
		System.out.println("siiiiiiiiiiiii");
		
		return "";
	}
	public String elegirCartaC2() {
		
		System.out.println("Nooooooooooooooo");
		if(log.getMontoDescartar().peek().getColor().equals(car2.getColor()) || log.getMontoDescartar().peek().getNumero()==car2.getNumero() ) {
		log.elegirCarta(usuarioJugando, car2);
		log.agregarPilaDescartar(car2);
		cartas = log.mostrarCartasJugador(usuarioJugando);
		contCarMax = cartas.size();
		ponerImagenBotonesJugador();

		}else {
			
		}
		System.out.println("siiiiiiiiiiii");
		
		return "";
	}
	public String elegirCartaC3() {
	
	System.out.println("Nooooooooooooooo");
	if(log.getMontoDescartar().peek().getColor().equals(car3.getColor()) || log.getMontoDescartar().peek().getNumero()==car3.getNumero() ) {
	log.elegirCarta(usuarioJugando, car3);
	log.agregarPilaDescartar(car3);
	cartas = log.mostrarCartasJugador(usuarioJugando);
	contCarMax = cartas.size();
	ponerImagenBotonesJugador();

	}else {
		
	}
	System.out.println("siiiiiiiiiiii");
	
	return "";
}
	public String elegirCartaC4() {
	
	System.out.println("Nooooooooooooooo");
	if(log.getMontoDescartar().peek().getColor().equals(car4.getColor()) || log.getMontoDescartar().peek().getNumero()==car4.getNumero() ) {
	log.elegirCarta(usuarioJugando, car4);
	log.agregarPilaDescartar(car4);
	cartas = log.mostrarCartasJugador(usuarioJugando);
	contCarMax = cartas.size();
	ponerImagenBotonesJugador();

	}else {
		
	}
	System.out.println("siiiiiiiiiiii");
	
	return "";
}
	public String elegirCartaC5() {
	
	System.out.println("Nooooooooooooooo");
	if(log.getMontoDescartar().peek().getColor().equals(car5.getColor()) || log.getMontoDescartar().peek().getNumero()==car5.getNumero() ) {
	log.elegirCarta(usuarioJugando, car5);
	log.agregarPilaDescartar(car5);
	cartas = log.mostrarCartasJugador(usuarioJugando);
	contCarMax = cartas.size();
	ponerImagenBotonesJugador();

	}else {
		
	}
	System.out.println("siiiiiiiiiiii");
	
	return "";
}
	
	

	//Este metodo funciona para mostrar cualquiera de las imagenes de las cartas
	public String cambiarAImagen(Carta carta) {
	String res = "";
	String rem = cambiarNumeroComodin(carta);
		if(carta != null) {
			switch (carta.getColor()){
			case "Amarillo":
				if(carta.getNumero()<=9) {
				res = "Cartas/Amarillo/"+carta.getNumero()+carta.getColor()+".jpg";
				}else {
					res = "Cartas/Amarillo/"+rem+carta.getColor()+".jpg";
				}
				break;
		    case "Rojo":
		    	if(carta.getNumero()<=9) {
		    	res = "Cartas/Rojo/"+carta.getNumero()+carta.getColor()+".jpg";}
		    	else {
					res = "Cartas/Rojo/"+rem+carta.getColor()+".jpg";
				}
		    	break;
		    case "Azul":
		    	if(carta.getNumero()<=9) {
		    	res = "Cartas/Azul/"+carta.getNumero()+carta.getColor()+".jpg";}
		    	else {
					res = "Cartas/Azul/"+rem+carta.getColor()+".jpg";
				}
		    	break;
		    case "Verde":
		    	if(carta.getNumero()<=9) {
		    	res = "Cartas/Verde/"+carta.getNumero()+carta.getColor()+".jpg";}
		    	else {
					res = "Cartas/Verde/"+rem+carta.getColor()+".jpg";
				}
		    	break;
		    case "Negro":
		    	res = "Cartas/Negro/"+cambiarNumeroComodin(carta)+".jpg";
		    	break;
			default:
				res="";
			}
//			
		}
		
		return res;
	}
	
	
	//Este cambia los numeros como 11 o 13 por los que necesitan las imagenes
	public String cambiarNumeroComodin(Carta carta) {
		String res = "";
		int num = carta.getNumero();
		if(carta.getNumero()>9) {
				switch (num){
				case 10: 
					res="+2";
					break;
				case 11: 
					res="reversa";
					break;
				case 12: 
					res="cancelar";
					break;
				case 13:
					res="ColorNegro";
					break;
				case 14:
					res="+4Negro";
					break;
				default: 
					res="";
			}
				}
	
		return res;
	}
	
	public void hacerMontoCartaJugador() {
		cartas = log.mostrarCartasJugador(usuarioJugando);
		contCarMax=cartas.size();
		contCarMin = 4;
	}
	
	public void comprobarSiSeUsaComodin(Carta car) {
		if(car.getNumero()==14) {
			if(direccionReloj==true) {
			log.masDosmasCuatroCartas(4, log.consultarHorarioSiguiente(usuarioJugando));}else {
				log.masDosmasCuatroCartas(4, log.consultarHorarioAnterior(usuarioJugando));
			}

		}
		
		if(car.getNumero()==13) {
			
			log.agregarPilaDescartar(new Carta(0,log.cambioColor()));
			
		}
		
		if(car.getNumero()==11) {
			if(direccionReloj==true) {
				direccionReloj=false;
			}else {
				direccionReloj=true;
			}
		}
		
		if(car.getNumero()==10) {
			if(direccionReloj==true) {
				log.masDosmasCuatroCartas(2, log.consultarHorarioSiguiente(usuarioJugando));}else {
					log.masDosmasCuatroCartas(2, log.consultarHorarioAnterior(usuarioJugando));
				}
		}
		
	}
	
	public boolean pierdeTurno(Carta car) {
		boolean siEs=false;
		if(car.getNumero()==12) {
			siEs=true;
			if(direccionReloj==true) {
				usuarioJugando =log.consultarHorarioSaltaTurno(usuarioJugando);
				cartas=log.mostrarCartasJugador(usuarioJugando);
				contCarMax=cartas.size();
				ponerImagenBotonesJugador();
				}else {
					usuarioJugando=log.consultarContrarioSaltaTurno(usuarioJugando);
					cartas=log.mostrarCartasJugador(usuarioJugando);
					contCarMax=cartas.size();
					ponerImagenBotonesJugador();
				}
		}
		return siEs;
	}
	
	//public String mostrarBotonCarta() {
		//if(contCarMin ==0) {
			//return car.get(1).getNumero()+car.get(1).getColor();
	//	}
	//}
	
	public String pasarAlOtroTurno() {
		if(direccionReloj==true) {
			usuarioJugando=usuarioJugandoHorario(usuarioJugando);
			return usuarioJugando;
		}else {
			usuarioJugando=usuarioJugandoAntiHorario(usuarioJugando);
			return usuarioJugando;
		}
	}
	
	
	
	public String primUsuarioJugando() {
		usuarioJugando = log.getJug1();
		return usuarioJugando;
	}
	
	public String usuarioJugandoAntiHorario(String nom) {
		String nomNue = log.consultarContrarioSiguiente(nom);
		return nomNue;
	}

	public String usuarioJugandoHorario(String nom) {
		String nomNue = log.consultarHorarioSiguiente(nom);
		return nomNue;
	}

	public String getUsuario1() {
		System.out.println("---->"+usuario1+ "<----");
		return usuario1;
	}


	public void setUsuario1(String usuario1) {
		this.usuario1 = usuario1;
	}

	public String getUsuario2() {
		return usuario2;
	}

	public void setUsuario2(String usuario2) {
		this.usuario2 = usuario2;
	}

	public String getAprendiz() {
		return aprendiz;
	}

	public void setAprendiz(String aprendiz) {
		this.aprendiz = aprendiz;
	}

	public LogicaPrincipal getLog() {
		return log;
	}

	public void setLog(LogicaPrincipal log) {
		this.log = log;
	}

	public String getCart1() {
		return cart1;
	}

	public void setCart1(String cart1) {
		this.cart1 = cart1;
	}

	public String getCart2() {
		return cart2;
	}

	public void setCart2(String cart2) {
		this.cart2 = cart2;
	}

	public String getCart3() {
		return cart3;
	}

	public void setCart3(String cart3) {
		this.cart3 = cart3;
	}

	public String getCart4() {
		return cart4;
	}

	public void setCart4(String cart4) {
		this.cart4 = cart4;
	}

	public String getCart5() {
		return cart5;
	}

	public void setCart5(String cart5) {
		this.cart5 = cart5;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public Carta getCar1() {
		return car1;
	}

	public void setCar1(Carta car1) {
		this.car1 = car1;
	}

	public Carta getCar2() {
		return car2;
	}

	public void setCar2(Carta car2) {
		this.car2 = car2;
	}

	public Carta getCar3() {
		return car3;
	}

	public void setCar3(Carta car3) {
		this.car3 = car3;
	}

	public Carta getCar4() {
		return car4;
	}

	public void setCar4(Carta car4) {
		this.car4 = car4;
	}

	public Carta getCar5() {
		return car5;
	}

	public void setCar5(Carta car5) {
		this.car5 = car5;
	}

	public int getContCarMax() {
		return contCarMax;
	}

	public void setContCarMax(int contCarMax) {
		this.contCarMax = contCarMax;
	}

	public int getContCarMin() {
		return contCarMin;
	}

	public void setContCarMin(int contCarMin) {
		this.contCarMin = contCarMin;
	}

	public ArrayList<Carta> getCartas() {
		return cartas;
	}

	public void setCartas(ArrayList<Carta> cartas) {
		this.cartas = cartas;
	}
	
	

}


package co.edu.unbosque.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import co.edu.unbosque.model.Carta;
import co.edu.unbosque.model.LogicaPrincipal;

@ManagedBean
public class MainBean implements Serializable{
	
	private static final long serialVersionUID = -2152389656664659476L;
	private String usuario1="", usuario2="", aprendiz="",cart1="", cart2="",cart3="",cart4="",cart5="";
	private LogicaPrincipal log=new LogicaPrincipal();
	private int turn= 1;
	private Carta car1=new Carta(0, null), car2=new Carta(0, null), car3=new Carta(0, null), car4=new Carta(0, null), car5=new Carta(0, null);
	private int contCarMax , contCarMin;
	private ArrayList<Carta> cartas= new ArrayList<>();
	private ArrayList<Carta> movCartas = new ArrayList<>();
	
	
	public String navegar() {
		log.elegirJugador(usuario1, usuario2, aprendiz);
		log.distribuirCartasYJugadores();
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
	
	
	//TODAVIA NO FUNCIONA
	public String adelantarCartasJugador() {
		
		System.out.println("Hola");
		contCarMin= contCarMin +1;
		car1 = car2;
		car2= car3;
		car4 = car5;
		car5 = movCartas.get(contCarMin);
		
		cart1 = cambiarAImagen(car1);
		cart2 = cambiarAImagen(car2);
		cart3 = cambiarAImagen(car3);
		cart4 = cambiarAImagen(car4);
		cart5 = cambiarAImagen(car5);
		System.out.println("El boton funciona");
		
		if(contCarMin==contCarMax) {
			contCarMin = 0;
		}
		
		return "juego.xhtml";
		
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
		cartas = log.mostrarCartasJugador(usuarioJugando());
		contCarMax=cartas.size();
		contCarMin = 5;
	}
	
	//public String mostrarBotonCarta() {
		//if(contCarMin ==0) {
			//return car.get(1).getNumero()+car.get(1).getColor();
	//	}
	//}
	
	public String usuarioJugando() {
		if(turn == 1) {
			return log.getJug1();
		}else {
		if(turn ==2) {
			return log.getJug2();
		}else {
			return log.getJug3();
		}
		}
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

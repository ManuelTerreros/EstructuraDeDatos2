package co.edu.unbosque.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import org.primefaces.PrimeFaces;
import org.primefaces.context.PrimeRequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped; // o el alcance que desees utilizar
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import co.edu.unbosque.model.Carta;
import co.edu.unbosque.model.LogicaPrincipal;

@ApplicationScoped
@ManagedBean
public class MainBean implements Serializable {

	private static final long serialVersionUID = -2152389656664659476L;
	private String usuario1 = "", usuario2 = "", aprendiz = "", cart1 = "", cart2 = "", cart3 = "", cart4 = "",
			cart5 = "", usuarioJugando = "";
	private int numCarUsu1 = 0, numCarUsu2 = 0, numCarUsu3 = 0;
	private LogicaPrincipal log = new LogicaPrincipal();
	private int turn = 1;
	private Carta car1 = new Carta(0, null), car2 = new Carta(0, null), car3 = new Carta(0, null),
			car4 = new Carta(0, null), car5 = new Carta(0, null);
	private int contCarMax = 0, contCarMin = 0, resta = 1, respaldo = contCarMin, consta = 0;
	private ArrayList<Carta> cartas = new ArrayList<>();
	private boolean direccionReloj = true, ganador = false;
	private Carta cartaRobada = new Carta(0, null);
	private String cartaRob = "", cartasJug = "", color = "";
	private int oportunidades = 0;

	public String navegar() {
		log.elegirJugador(usuario1, usuario2, aprendiz);
		log.distribuirCartasYJugadores();
		numCarUsu1 = log.mostrarCartasJugador(usuario1).size();
		numCarUsu2 = log.mostrarCartasJugador(usuario2).size();
		numCarUsu3 = log.mostrarCartasJugador(aprendiz).size();
		primUsuarioJugando();
		cartaRob = " ";
		System.out.println(usuarioJugando);
		hacerMontoCartaJugador();
		ponerImagenBotonesJugador();
		log.agregarPilaDescartar(log.quitarPrimeraCartaPilaRobar());
		cartasJug = cartasJugando();
		if (log.consultarPilaDescartar().getColor().equals("Negro")) {
			color = log.cambioColor();
		} else {
			color = log.consultarPilaDescartar().getColor();
		}
		oportunidades = 0;

		System.out.println("------*****------");
		System.out.println(usuario1);
		System.out.println(log.mostrarCartasJugador(usuario1));
		System.out.println(usuario2);
		System.out.println(log.mostrarCartasJugador(usuario2));
		System.out.println(aprendiz);
		System.out.println(log.mostrarCartasJugador(aprendiz));
		System.out.println("------------*****-----------");
		System.out.println(cartas.get(0).getColor() + cartas.get(0).getNumero());

		return "juego.xhtml";

	}

	public String cartasJugando() {
		String res = "";

		for (int i = 0; i < cartas.size(); i++) {
			if (cartas.get(i).getNumero() <= 9) {
				res = res + cartas.get(i).getNumero() + " " + cartas.get(i).getColor() + "; ";
			} else {
				String ayuda = cambiarNumeroComodin(cartas.get(i));
				res = res + ayuda + " " + cartas.get(i).getColor() + "; ";
			}
		}

		return res;
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
		res = cambiarAImagen(car);

		return res;
	}

	public void ponerImagenBotonesJugador() {
		if (contCarMax >= 5) {
			car1 = cartas.get(0);
			car2 = cartas.get(1);
			car3 = cartas.get(2);
			car4 = cartas.get(3);
			car5 = cartas.get(4);
			cart1 = cambiarAImagen(car1);
			cart2 = cambiarAImagen(car2);
			cart3 = cambiarAImagen(car3);
			cart4 = cambiarAImagen(car4);
			cart5 = cambiarAImagen(car5);
		} else {
			if (contCarMax == 4) {
				car1 = cartas.get(0);
				car2 = cartas.get(1);
				car3 = cartas.get(2);
				car4 = cartas.get(3);
				car5 = null;
				cart1 = cambiarAImagen(car1);
				cart2 = cambiarAImagen(car2);
				cart3 = cambiarAImagen(car3);
				cart4 = cambiarAImagen(car4);
				cart5 = cambiarAImagen(car5);
			}
			if (contCarMax == 3) {
				car1 = cartas.get(0);
				car2 = cartas.get(1);
				car3 = cartas.get(2);
				car5 = null;
				car4 = null;
				cart1 = cambiarAImagen(car1);
				cart2 = cambiarAImagen(car2);
				cart3 = cambiarAImagen(car3);
				cart4 = cambiarAImagen(car4);
				cart5 = cambiarAImagen(car5);
			}
			if (contCarMax == 2) {
				car1 = cartas.get(0);
				car2 = cartas.get(1);
				car5 = null;
				car4 = null;
				car3 = null;
				cart1 = cambiarAImagen(car1);
				cart2 = cambiarAImagen(car2);
				cart3 = cambiarAImagen(car3);
				cart4 = cambiarAImagen(car4);
				cart5 = cambiarAImagen(car5);
			}
			if (contCarMax == 1) {
				car1 = cartas.get(0);
				car5 = null;
				car4 = null;
				car3 = null;
				car2 = null;
				cart1 = cambiarAImagen(car1);
				cart2 = cambiarAImagen(car2);
				cart3 = cambiarAImagen(car3);
				cart4 = cambiarAImagen(car4);
				cart5 = cambiarAImagen(car5);
			}
			if (contCarMax == 0) {
				car5 = null;
				car4 = null;
				car3 = null;
				car2 = null;
				car1 = null;
				cart1 = cambiarAImagen(car1);
				cart2 = cambiarAImagen(car2);
				cart3 = cambiarAImagen(car3);
				cart4 = cambiarAImagen(car4);
				cart5 = cambiarAImagen(car5);
			}

		}
	}

	public String adelantarCartasJugador() {

		System.out.println("Numero contCarMin" + contCarMin);
		System.out.println("Numero contCarMax" + contCarMax);
		if (contCarMin == contCarMax - 1 || contCarMin>=contCarMax) {
			contCarMin = -1;
		}

		contCarMin = contCarMin + 1;
		car1 = car2;
		car2 = car3;
		car3 = car4;
		car4 = car5;
		car5 = cartas.get(contCarMin);

		cart1 = cambiarAImagen(car1);
		cart2 = cambiarAImagen(car2);
		cart3 = cambiarAImagen(car3);
		cart4 = cambiarAImagen(car4);
		cart5 = cambiarAImagen(car5);

		return "";

	}

	public String robarCartaMazoRobar() {

		if (oportunidades == 1) {
			opor();
		} else {

			cartaRobada = log.sacarCarta();
			System.out.println("Carta robada cartaRobada" + cartaRobada.getNumero() + " " + cartaRobada.getColor());
			cartaRob = cartaRobada.getNumero() + " " + cartaRobada.getColor();
			System.out.println("Carta robada cartaRob" + cartaRob);
			log.robarCarta(usuarioJugando, cartaRobada);

			System.out.println("Cartas jugador con una robada");
			System.out.println(log.mostrarCartasJugador(usuarioJugando));
			cartas = log.mostrarCartasJugador(usuarioJugando);
			contCarMax = cartas.size();
			cartasJug = cartasJugando();
			numCarUsu1 = log.mostrarCartasJugador(usuario1).size();
			numCarUsu2 = log.mostrarCartasJugador(usuario2).size();
			numCarUsu3 = log.mostrarCartasJugador(aprendiz).size();

			oportunidades = oportunidades + 1;

			if (contCarMax >= 5) {
				car1 = cartas.get(0);
				car2 = cartas.get(1);
				car3 = cartas.get(2);
				car4 = cartas.get(3);
				car5 = cartaRobada;
				cart1 = cambiarAImagen(car1);
				cart2 = cambiarAImagen(car2);
				cart3 = cambiarAImagen(car3);
				cart4 = cambiarAImagen(car4);
				cart5 = cambiarAImagen(car5);
			} else {
				if (contCarMax == 4) {
					car1 = cartas.get(0);
					car2 = cartas.get(1);
					car3 = cartas.get(2);
					car4 = cartaRobada;
					car5 = null;
					cart1 = cambiarAImagen(car1);
					cart2 = cambiarAImagen(car2);
					cart3 = cambiarAImagen(car3);
					cart4 = cambiarAImagen(car4);
					cart5 = cambiarAImagen(car5);
				}
				if (contCarMax == 3) {
					car1 = cartas.get(0);
					car2 = cartas.get(1);
					car3 = cartaRobada;
					car5 = null;
					car4 = null;
					cart1 = cambiarAImagen(car1);
					cart2 = cambiarAImagen(car2);
					cart3 = cambiarAImagen(car3);
					cart4 = cambiarAImagen(car4);
					cart5 = cambiarAImagen(car5);
				}
				if (contCarMax == 2) {
					car1 = cartas.get(0);
					car2 = cartaRobada;
					car5 = null;
					car4 = null;
					car3 = null;
					cart1 = cambiarAImagen(car1);
					cart2 = cambiarAImagen(car2);
					cart3 = cambiarAImagen(car3);
					cart4 = cambiarAImagen(car4);
					cart5 = cambiarAImagen(car5);
				}

			}

		}

		return "";
	}

	public String opor() {
		if (oportunidades == 1) {
			String usunuevo = pasarAlOtroTurno();
			cartas = log.mostrarCartasJugador(usunuevo);
			contCarMax = cartas.size();
			ponerImagenBotonesJugador();
			cartasJug = cartasJugando();
			oportunidades = 0;
			return "";
		}
		return "";
	}

	public boolean comprobarGanador() {
		if (contCarMax == 1) {
			ganador = true;
		}
		return ganador;
	}

	public String elegirCartaC1() {
		if (comprobarGanador() == false) {
			if (log.getMontoDescartar().peek().getColor().equals(car1.getColor())
					|| log.getMontoDescartar().peek().getNumero() == car1.getNumero() || car1.getColor().equals(color)
					|| car1.getColor().equals("Negro")) {

				color = car1.getColor();
				log.elegirCarta(usuarioJugando, car1);
				log.agregarPilaDescartar(car1);

				comprobarSiSeUsaComodin(car1);
				numCarUsu1 = log.mostrarCartasJugador(usuario1).size();
				numCarUsu2 = log.mostrarCartasJugador(usuario2).size();
				numCarUsu3 = log.mostrarCartasJugador(aprendiz).size();

				if (pierdeTurno(car1) == false) {
					String usunuevo = pasarAlOtroTurno();
					cartas = log.mostrarCartasJugador(usunuevo);
					contCarMax = cartas.size();
					ponerImagenBotonesJugador();
					cartasJug = cartasJugando();
				}

				System.out.println("------*****------");
				System.out.println(usuario1);
				System.out.println(log.mostrarCartasJugador(usuario1));
				System.out.println(usuario2);
				System.out.println(log.mostrarCartasJugador(usuario2));
				System.out.println(aprendiz);
				System.out.println(log.mostrarCartasJugador(aprendiz));
				System.out.println("------------*****-----------");
			} else {
				opor();

			}
		} else {
			return "ganador.xhtml";
		}

		return "";
	}

	public String elegirCartaC2() {
		if (comprobarGanador() == false) {
			if (log.getMontoDescartar().peek().getColor().equals(car2.getColor())
					|| log.getMontoDescartar().peek().getNumero() == car2.getNumero() || car2.getColor().equals(color)
					|| car2.getColor().equals("Negro")) {

				color = car2.getColor();
				log.elegirCarta(usuarioJugando, car2);
				log.agregarPilaDescartar(car2);

				comprobarSiSeUsaComodin(car2);
				numCarUsu1 = log.mostrarCartasJugador(usuario1).size();
				numCarUsu2 = log.mostrarCartasJugador(usuario2).size();
				numCarUsu3 = log.mostrarCartasJugador(aprendiz).size();
				if (pierdeTurno(car2) == false) {
					String usunuevo = pasarAlOtroTurno();
					cartas = log.mostrarCartasJugador(usunuevo);
					contCarMax = cartas.size();
					ponerImagenBotonesJugador();
					cartasJug = cartasJugando();
				}

				System.out.println("------*****------");
				System.out.println(usuario1);
				System.out.println(log.mostrarCartasJugador(usuario1));
				System.out.println(usuario2);
				System.out.println(log.mostrarCartasJugador(usuario2));
				System.out.println(aprendiz);
				System.out.println(log.mostrarCartasJugador(aprendiz));
				System.out.println("------------*****-----------");
			} else {
				opor();

			}
		} else {
			return "ganador.xhtml";
		}

		return "";
	}

	public String elegirCartaC3() {
		if (comprobarGanador() == false) {

			if (log.getMontoDescartar().peek().getColor().equals(car3.getColor())
					|| log.getMontoDescartar().peek().getNumero() == car3.getNumero() || car3.getColor().equals(color)
					|| car3.getColor().equals("Negro")) {

				color = car3.getColor();
				log.elegirCarta(usuarioJugando, car3);
				log.agregarPilaDescartar(car3);

				comprobarSiSeUsaComodin(car3);
				numCarUsu1 = log.mostrarCartasJugador(usuario1).size();
				numCarUsu2 = log.mostrarCartasJugador(usuario2).size();
				numCarUsu3 = log.mostrarCartasJugador(aprendiz).size();
				if (pierdeTurno(car3) == false) {
					String usunuevo = pasarAlOtroTurno();
					cartas = log.mostrarCartasJugador(usunuevo);
					contCarMax = cartas.size();
					ponerImagenBotonesJugador();
					cartasJug = cartasJugando();
				}

				System.out.println("------*****------");
				System.out.println(usuario1);
				System.out.println(log.mostrarCartasJugador(usuario1));
				System.out.println(usuario2);
				System.out.println(log.mostrarCartasJugador(usuario2));
				System.out.println(aprendiz);
				System.out.println(log.mostrarCartasJugador(aprendiz));
				System.out.println("------------*****-----------");
			} else {
				opor();

			}
		} else {
			return "ganador.xhtml";
		}

		return "";
	}

	public String elegirCartaC4() {
		if (comprobarGanador() == false) {
			if (log.getMontoDescartar().peek().getColor().equals(car4.getColor())
					|| log.getMontoDescartar().peek().getNumero() == car4.getNumero() || car4.getColor().equals(color)
					|| car4.getColor().equals("Negro")) {

				color = car4.getColor();
				log.elegirCarta(usuarioJugando, car4);
				log.agregarPilaDescartar(car4);

				comprobarSiSeUsaComodin(car4);
				numCarUsu1 = log.mostrarCartasJugador(usuario1).size();
				numCarUsu2 = log.mostrarCartasJugador(usuario2).size();
				numCarUsu3 = log.mostrarCartasJugador(aprendiz).size();
				if (pierdeTurno(car4) == false) {
					String usunuevo = pasarAlOtroTurno();
					cartas = log.mostrarCartasJugador(usunuevo);
					contCarMax = cartas.size();
					ponerImagenBotonesJugador();
					cartasJug = cartasJugando();
				}

				System.out.println("------*****------");
				System.out.println(usuario1);
				System.out.println(log.mostrarCartasJugador(usuario1));
				System.out.println(usuario2);
				System.out.println(log.mostrarCartasJugador(usuario2));
				System.out.println(aprendiz);
				System.out.println(log.mostrarCartasJugador(aprendiz));
				System.out.println("------------*****-----------");
			} else {
				opor();

			}
		} else {
			return "ganador.xhtml";
		}

		return "";
	}

	public String elegirCartaC5() {

		if (comprobarGanador() == false) {
			if (log.getMontoDescartar().peek().getColor().equals(car5.getColor())
					|| log.getMontoDescartar().peek().getNumero() == car5.getNumero() || car5.getColor().equals(color)
					|| car5.getColor().equals("Negro")) {

				color = car5.getColor();
				log.elegirCarta(usuarioJugando, car5);
				log.agregarPilaDescartar(car5);

				comprobarSiSeUsaComodin(car5);
				numCarUsu1 = log.mostrarCartasJugador(usuario1).size();
				numCarUsu2 = log.mostrarCartasJugador(usuario2).size();
				numCarUsu3 = log.mostrarCartasJugador(aprendiz).size();
				if (pierdeTurno(car5) == false) {
					String usunuevo = pasarAlOtroTurno();
					cartas = log.mostrarCartasJugador(usunuevo);
					contCarMax = cartas.size();
					ponerImagenBotonesJugador();
					cartasJug = cartasJugando();
				}

				System.out.println("------*****------");
				System.out.println(usuario1);
				System.out.println(log.mostrarCartasJugador(usuario1));
				System.out.println(usuario2);
				System.out.println(log.mostrarCartasJugador(usuario2));
				System.out.println(aprendiz);
				System.out.println(log.mostrarCartasJugador(aprendiz));
				System.out.println("------------*****-----------");
			} else {
				opor();

			}
		} else {
			return "ganador.xhtml";
		}

		return "";
	}

	// Este metodo funciona para mostrar cualquiera de las imagenes de las cartas
	public String cambiarAImagen(Carta carta) {
		String res = "";
		String rem = cambiarNumeroComodin(carta);

		if (carta != null) {
			switch (carta.getColor()) {
			case "Amarillo":
				if (carta.getNumero() <= 9) {
					res = "Cartas/Amarillo/" + carta.getNumero() + carta.getColor() + ".jpg";
				} else {
					res = "Cartas/Amarillo/" + rem + carta.getColor() + ".jpg";
				}
				break;
			case "Rojo":
				if (carta.getNumero() <= 9) {
					res = "Cartas/Rojo/" + carta.getNumero() + carta.getColor() + ".jpg";
				} else {
					res = "Cartas/Rojo/" + rem + carta.getColor() + ".jpg";
				}
				break;
			case "Azul":
				if (carta.getNumero() <= 9) {
					res = "Cartas/Azul/" + carta.getNumero() + carta.getColor() + ".jpg";
				} else {
					res = "Cartas/Azul/" + rem + carta.getColor() + ".jpg";
				}
				break;
			case "Verde":
				if (carta.getNumero() <= 9) {
					res = "Cartas/Verde/" + carta.getNumero() + carta.getColor() + ".jpg";
				} else {
					res = "Cartas/Verde/" + rem + carta.getColor() + ".jpg";
				}
				break;
			case "Negro":
				res = "Cartas/Negro/" + cambiarNumeroComodin(carta) + ".jpg";
				break;
			default:
				res = "";
			}
//			
		} else {
			res = "Img/respaldoCarta.png";
		}

		return res;
	}

	// Este cambia los numeros como 11 o 13 por los que necesitan las imagenes
	public String cambiarNumeroComodin(Carta carta) {
		String res = "";
		if (carta != null) {
			int num = carta.getNumero();
			if (carta.getNumero() > 9) {
				switch (num) {
				case 10:
					res = "+2";
					break;
				case 11:
					res = "reversa";
					break;
				case 12:
					res = "cancelar";
					break;
				case 13:
					res = "ColorNegro";
					break;
				case 14:
					res = "+4Negro";
					break;
				default:
					res = "";
				}
			}
		}

		return res;
	}

	public void hacerMontoCartaJugador() {
		cartas = log.mostrarCartasJugador(usuarioJugando);
		contCarMax = cartas.size();
		contCarMin = 4;
	}

	public void comprobarSiSeUsaComodin(Carta car) {
		if (car.getNumero() == 14) {
			color = log.cambioColor();
			if (direccionReloj == true) {
				log.masDosmasCuatroCartas(4, log.consultarHorarioAnterior(usuarioJugando));
			} else {
				log.masDosmasCuatroCartas(4, log.consultarHorarioSiguiente(usuarioJugando));

			}

		}

		if (car.getNumero() == 13) {
			color = log.cambioColor();
		}

		if (car.getNumero() == 11) {
			if (direccionReloj == true) {
				direccionReloj = false;
			} else {
				direccionReloj = true;
			}
		}

		if (car.getNumero() == 10) {
			if (direccionReloj == true) {
				log.masDosmasCuatroCartas(2, log.consultarHorarioAnterior(usuarioJugando));
			} else {
				log.masDosmasCuatroCartas(2, log.consultarHorarioSiguiente(usuarioJugando));
			}
		}

	}

	public boolean pierdeTurno(Carta car) {
		boolean siEs = false;
		if (car.getNumero() == 12) {
			siEs = true;
			if (direccionReloj == true) {
				usuarioJugando = log.consultarContrarioSaltaTurno(usuarioJugando);
				cartas = log.mostrarCartasJugador(usuarioJugando);
				contCarMax = cartas.size();
				ponerImagenBotonesJugador();
				cartasJug = cartasJugando();
			} else {
				usuarioJugando = log.consultarHorarioSaltaTurno(usuarioJugando);
				cartas = log.mostrarCartasJugador(usuarioJugando);
				contCarMax = cartas.size();
				ponerImagenBotonesJugador();
				cartasJug = cartasJugando();
			}
		}
		return siEs;
	}

	// public String mostrarBotonCarta() {
	// if(contCarMin ==0) {
	// return car.get(1).getNumero()+car.get(1).getColor();
	// }
	// }

	public String pasarAlOtroTurno() {
		cartaRob = " ";
		if (direccionReloj == true) {
			usuarioJugando = usuarioJugandoAntiHorario(usuarioJugando);

			return usuarioJugando;
		} else {
			usuarioJugando = usuarioJugandoHorario(usuarioJugando);
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
	
	public String volverIndex() {
		return "index.xhtml";
	}

	public String getUsuario1() {
		System.out.println("---->" + usuario1 + "<----");
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

	public int getNumCarUsu1() {
		return numCarUsu1;
	}

	public void setNumCarUsu1(int numCarUsu1) {
		this.numCarUsu1 = numCarUsu1;
	}

	public int getNumCarUsu2() {
		return numCarUsu2;
	}

	public void setNumCarUsu2(int numCarUsu2) {
		this.numCarUsu2 = numCarUsu2;
	}

	public int getNumCarUsu3() {
		return numCarUsu3;
	}

	public void setNumCarUsu3(int numCarUsu3) {
		this.numCarUsu3 = numCarUsu3;
	}

	public String getCartaRob() {
		return cartaRob;
	}

	public void setCartaRob(String cartaRob) {
		this.cartaRob = cartaRob;
	}

	public String getCartasJug() {
		return cartasJug;
	}

	public void setCartasJug(String cartasJug) {
		this.cartasJug = cartasJug;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}

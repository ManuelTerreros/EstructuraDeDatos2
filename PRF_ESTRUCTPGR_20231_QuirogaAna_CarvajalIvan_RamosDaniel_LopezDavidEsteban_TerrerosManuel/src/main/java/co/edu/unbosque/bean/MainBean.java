package co.edu.unbosque.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import co.edu.unbosque.model.Carta;
import co.edu.unbosque.model.LogicaPrincipal;

@ManagedBean
public class MainBean implements Serializable{
	
	private static final long serialVersionUID = -2152389656664659476L;
	private String usuario1="", usuario2="", aprendiz="";
	private LogicaPrincipal log;
	
	public String navegar() {
		log = new LogicaPrincipal();
		log.setJug1(usuario1);
		log.setJug2(usuario2);
		log.setJug3(aprendiz);
		log.distribuirCartasYJugadores();
		System.out.println(log.getJug1());
		System.out.println(log.mostrarCartasJugador(usuario1));
		System.out.println(log.getJug2());
		System.out.println(log.mostrarCartasJugador(usuario2));
		System.out.println(log.getJug3());
		System.out.println(log.mostrarCartasJugador(aprendiz));
		log.agregarPilaDescartar(log.quitarPrimeraCartaPilaRobar());
		

		return "juego.xhtml";
	}
	
	public String mostrarUltimaCartaMazo() {
		Carta car = log.consultarPilaDescartar();
		String res = "";
		if(car != null) {
			if(car.getColor().equalsIgnoreCase("Amarillo")) {
			res="Cartas/Amarillo/"+car.getNumero()+car.getColor()+".jpg";
			}
			if(car.getColor().equalsIgnoreCase("Rojo")) {
				res="Cartas/Rojo/"+car.getNumero()+car.getColor()+".jpg";
				}
			if(car.getColor().equalsIgnoreCase("Azul")) {
				res="Cartas/Azul/"+car.getNumero()+car.getColor()+".jpg";
				}
			if(car.getColor().equalsIgnoreCase("Verde")) {
				res="Cartas/Verde/"+car.getNumero()+car.getColor()+".jpg";
				}
			if(car.getColor().equalsIgnoreCase("Negro")) {
				res="Cartas/Negro/"+car.getNumero()+car.getColor()+".jpg";
				}
			System.out.println(res);
		}
		return res;
	}


	public String getUsuario1() {
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
	
	

}

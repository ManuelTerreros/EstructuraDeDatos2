package co.edu.unbosque.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import co.edu.unbosque.model.LogicaPrincipal;

@ManagedBean
public class MainBean implements Serializable{
	
	private static final long serialVersionUID = -2152389656664659476L;
	private String usuario1="", usuario2="", aprendiz="";
	private LogicaPrincipal log;
	
	public String navegar() {

		return "juego.xhtml";
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

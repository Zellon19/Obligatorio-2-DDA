package edu.ctc.obligatorio2.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Turno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (length=50)
	private String tipo;
	
	//Falta esto
	private Chofer chofer;
	
	//Falta esto
	private Coche coche;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	
	}
	
	public Chofer getChofer() {
		return chofer;
	}
	public void setChofer(Chofer chofer) {
		this.chofer = chofer;
	
	}
	
	public Coche getCoche() {
		return coche;
	}
	public void setCoche(Coche coche) {
		this.coche = coche;
	}
	
	

}

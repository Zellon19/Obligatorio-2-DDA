package edu.ctc.obligatorio2.entity;


import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Table (name = "choferes")
public class Chofer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (length=50)
	private String nombre;
	
	@Column (length=50)
	private String apellido;
	
	@Column (length=20, nullable = false, unique = true)
	private String telefono;
	
	@Column (length=8, nullable = false, unique = true)
	private String cedula;
	
	private ArrayList<Turno> listaTurnos = new ArrayList<Turno>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	
	@OneToMany(mappedBy = "turnos")
	public ArrayList<Turno> getListaTurnos(){
		return listaTurnos;
	}
	
	public Chofer(int id, String nombre, String apellido, String telefono, String cedula) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.cedula = cedula;
	}
	
	public Chofer(){}

}

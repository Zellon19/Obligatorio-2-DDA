package edu.ctc.obligatorio2.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
@Table (name = "choferes")
public class Chofer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (length=50)
	private String nombre;
	
	@Column (length=50)
	private String apellido;
	
	@Column (length=20, nullable = false, unique = true)
	private String telefono;
	
	@Column (length=8, nullable = false, unique = true)
	private String cedula;
	
	
	@Column (nullable = false)
	private TipoChofer tipoChofer;
	
	public TipoChofer getTipoChofer() {
		return tipoChofer;
	}
	public void setTipoChofer(TipoChofer tipoChofer) {
		this.tipoChofer = tipoChofer;
	}

	@ManyToMany(mappedBy = "listaChoferesEnElTurno", cascade = CascadeType.ALL)
	private List<Turno> listaTurnos = new ArrayList<Turno>();
	
	@OneToMany(mappedBy = "listaViajesDelChofer", cascade = CascadeType.ALL)
	private List<Viaje> listaViajesDelChofer = new ArrayList<Viaje>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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

	public List<Turno> getListaTurnos(){
		return listaTurnos;
	}

	public Chofer(Long id, String nombre, String apellido, String telefono, String cedula, TipoChofer tipoChofer) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.cedula = cedula;
		this.tipoChofer = tipoChofer;
	}
	
	public Chofer(){}

	@Override
	public String toString() {
		return id + " " + nombre + " " + apellido +
				" " + telefono +
				" " + cedula + " " + tipoChofer.Tipo();
	}
}

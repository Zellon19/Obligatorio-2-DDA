package edu.ctc.obligatorio2.entity;
import edu.ctc.obligatorio2.entity.Turno;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

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

	@Column(nullable = false)
	private TipoChofer tipoChofer;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<Turno> listaTurnos = new ArrayList<Turno>();
	
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

	public TipoChofer getTipoChofer() {
		return tipoChofer;
	}

	public void setTipoChofer(TipoChofer tipoChofer) {
		this.tipoChofer = tipoChofer;
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
				" " + cedula +
				"" + tipoChofer;
	}
}

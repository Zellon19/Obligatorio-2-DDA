package edu.ctc.obligatorio2.entity;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

@Entity
@Table(name = "viajes")
public class Viaje {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (length=100)
	private String direccion;
	
	@Column
	private float kmRecorridos;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime fechaHora;
	
	@Column
	private float precio;
	
	@ManyToOne
	@JoinColumn(name = "choferes")
	private Chofer chofer;
	
	@ManyToOne
	@JoinColumn(name = "coches")
	private Coche coche;
	
	@ManyToOne
	@JoinColumn(name = "turnos")
	private Turno turno;

	/////////////////////////////////////////

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lista_viajes_del_chofer_id")
	private Chofer listaViajesDelChofer;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lista_turnos_viaje_id")
	private Turno listaTurnosViaje;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "lista_coche_en_el_viaje_id")
	private Coche listaCocheEnElViaje;

	public Coche getListaCocheEnElViaje() {
		return listaCocheEnElViaje;
	}

	public void setListaCocheEnElViaje(Coche listaCocheEnElViaje) {
		this.listaCocheEnElViaje = listaCocheEnElViaje;
	}

	public Turno getListaTurnosViaje() {
		return listaTurnosViaje;
	}

	public void setListaTurnosViaje(Turno listaTurnosViaje) {
		this.listaTurnosViaje = listaTurnosViaje;
	}

	public Chofer getListaViajesDelChofer() {
		return listaViajesDelChofer;
	}

	public void setListaViajesDelChofer(Chofer listaViajesDelChofer) {
		this.listaViajesDelChofer = listaViajesDelChofer;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public float getKmRecorridos() {
		return kmRecorridos;
	}
	public void setKmRecorridos(float kmRecorridos) {
		this.kmRecorridos = kmRecorridos;
	}
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public void setfechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}

	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
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

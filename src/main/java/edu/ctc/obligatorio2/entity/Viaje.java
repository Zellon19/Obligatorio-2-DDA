package edu.ctc.obligatorio2.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
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

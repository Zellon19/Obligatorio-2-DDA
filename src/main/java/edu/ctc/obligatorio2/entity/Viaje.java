package edu.ctc.obligatorio2.entity;

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
	private int id;
	
	@Column (length=100)
	private String direccion;
	
	@Column
	private float kmRecorridos;
	
	@Column
	private LocalDateTime fechaHora;
	
	@Column
	private float precio;
	
	@ManyToOne
	@JoinColumn(name = "turnos")
	private Turno turno;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	
	
}

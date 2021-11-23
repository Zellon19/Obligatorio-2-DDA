package edu.ctc.obligatorio2.entity;

import java.util.List;

import javax.persistence.JoinTable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "turnos")
public class Turno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (length=50)
	private String tipo;
	
	@OneToMany
	private List<Viaje> listaViajes;
	
	@ManyToMany
	@JoinTable(
			name = "chofer_turno",
			joinColumns = @JoinColumn(name = "chofer_id"),
			inverseJoinColumns = @JoinColumn(name = "id"))
	private List<Chofer> listaChoferesEnElTurno;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	
	}

}

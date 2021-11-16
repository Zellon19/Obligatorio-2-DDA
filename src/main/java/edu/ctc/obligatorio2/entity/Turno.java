package edu.ctc.obligatorio2.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "turnos")
public class Turno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column (length=50)
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name = "idChofer")
	private Chofer chofer;
	
	@ManyToOne
	@JoinColumn(name = "idCoche")
	private Coche coche;
	
	@OneToMany
	private List<Viaje> listaViajes;
	
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

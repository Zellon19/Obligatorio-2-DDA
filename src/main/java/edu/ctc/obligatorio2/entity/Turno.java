package edu.ctc.obligatorio2.entity;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "turnos")
public class Turno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (length=50)
	private String tipo;
	
	@OneToMany(mappedBy = "listaTurnosViaje", cascade = CascadeType.ALL)
	private List<Viaje> listaTurnosViajes;
	
	@ManyToMany
	@JoinTable(
			name = "chofer_turno",
			joinColumns = @JoinColumn(name = "chofer_id"),
			inverseJoinColumns = @JoinColumn(name = "id"))
	private List<Chofer> listaChoferesEnElTurno;
	
	@ManyToMany
	@JoinTable(
			name = "coche_turno",
			joinColumns = @JoinColumn(name = "coche_id"),
			inverseJoinColumns = @JoinColumn(name = "id"))
	private List<Coche> listaCochesEnElTurno;
	
	
	public List<Chofer> getListaChoferes(){
		return listaChoferesEnElTurno;
	}
	
	public List<Coche> getListaCoches(){
		return listaCochesEnElTurno;
	}
	
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

	public Turno() {
	}

	@Override
	public String toString() {
		return id + tipo ;
	}
}

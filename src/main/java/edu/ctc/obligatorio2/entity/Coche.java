package edu.ctc.obligatorio2.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "coches")
@Inheritance(strategy=InheritanceType.JOINED)

public class Coche {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (length=7, nullable = false, unique = true)
	private String matricula;
	
	@Column (nullable = false)
	private TipoCoche tipoCoche;

	public TipoCoche getTipoCoche() {
		return tipoCoche;
	}
	public void setTipoCoche(TipoCoche tipoCoche) {
		this.tipoCoche = tipoCoche;
	}

	@ManyToMany(mappedBy = "listaCochesEnElTurno", cascade = CascadeType.ALL)
	private List<Turno> listaTurnos = new ArrayList<Turno>();
	
	@OneToMany(mappedBy = "listaCocheEnElViaje",cascade = CascadeType.ALL)
	private List<Viaje> viajes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	@Override
	public String toString() {
		return id + " " + matricula;
	}

	public Coche() {
	}

	public Coche(Long id, String matricula, TipoCoche tipoCoche) {
		this.id = id;
		this.matricula = matricula;
		this.tipoCoche = tipoCoche;
	}

}

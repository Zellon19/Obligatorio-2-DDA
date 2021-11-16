package edu.ctc.obligatorio2.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import net.bytebuddy.implementation.bind.annotation.Super;

@Entity
@Table(name="furgones")
public class Furgon extends Coche {

	public Furgon() {
		super();
	}

	
}

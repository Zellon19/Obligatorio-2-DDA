package edu.ctc.obligatorio2.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sedanes")
public class Sedan extends Coche{

	public Sedan() {
		super();
	}

}

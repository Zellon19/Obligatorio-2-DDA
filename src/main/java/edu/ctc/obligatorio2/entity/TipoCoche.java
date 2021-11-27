package edu.ctc.obligatorio2.entity;

public enum TipoCoche {
	Sedan("sedan"),
	Furgon("furgon");
	
	private String tipo;
	
	TipoCoche(String pTipo){
		this.tipo = pTipo;
	}
	
	public String Tipo() {
		return tipo;
	}
}

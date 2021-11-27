package edu.ctc.obligatorio2.entity;

public enum TipoChofer {
	Suplente("suplente"),
	Titular("titular");
	
	private String tipo;
	
	TipoChofer(String pTipo){
		this.tipo = pTipo;
	}
	
	public String Tipo() {
		return tipo;
	}
}

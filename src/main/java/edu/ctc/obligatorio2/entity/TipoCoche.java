package edu.ctc.obligatorio2.entity;

public enum TipoCoche {
    sedan("sedan"),
    furgon("furgon");

    private String nombre;

    TipoCoche(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

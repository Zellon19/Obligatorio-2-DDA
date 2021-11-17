package edu.ctc.obligatorio2.entity;

public enum TipoChofer {
    titular("titular"),
    suplente("suplente");

    private String nombre;

    TipoChofer(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}

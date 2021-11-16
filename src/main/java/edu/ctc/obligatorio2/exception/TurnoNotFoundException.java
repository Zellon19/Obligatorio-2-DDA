package edu.ctc.obligatorio2.exception;

public class TurnoNotFoundException extends RuntimeException{
    public TurnoNotFoundException(String message) {
        super(message);
    }
}

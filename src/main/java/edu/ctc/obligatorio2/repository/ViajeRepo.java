package edu.ctc.obligatorio2.repository;

import edu.ctc.obligatorio2.entity.Viaje;

import java.util.Optional;

public interface ViajeRepo {
    void deleteViajeById(Long id);
    Optional<Viaje> findViajeById(Long id);
}

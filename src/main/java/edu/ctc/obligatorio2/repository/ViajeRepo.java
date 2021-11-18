package edu.ctc.obligatorio2.repository;

import edu.ctc.obligatorio2.entity.Viaje;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ViajeRepo extends JpaRepository<Viaje, Long>{
    void deleteViajeById(Long id);
    Optional<Viaje> findViajeById(Long id);
}

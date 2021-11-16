package edu.ctc.obligatorio2.repository;

import edu.ctc.obligatorio2.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TurnoRepo extends JpaRepository<Turno, Long> {

        void deleteTurnoById(Long id);
        Optional<Turno> findTurnoById(Long id);


}
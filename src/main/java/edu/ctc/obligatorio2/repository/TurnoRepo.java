package edu.ctc.obligatorio2.repository;

import edu.ctc.obligatorio2.entity.Coche;
import edu.ctc.obligatorio2.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurnoRepo extends JpaRepository<Turno, Long> {

        void deleteTurnoById(Long id);
        Optional<Turno> findTurnoById(Long id);
}
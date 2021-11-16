package edu.ctc.obligatorio2.repository;

import edu.ctc.obligatorio2.entity.Coche;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CocheRepo extends JpaRepository<Coche, Long> {

        void deleteCocheById(Long id);
        Optional<Coche> findCocheById(Long id);


}
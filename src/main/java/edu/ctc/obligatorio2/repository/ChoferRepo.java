package edu.ctc.obligatorio2.repository;

import edu.ctc.obligatorio2.entity.Chofer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ChoferRepo extends JpaRepository<Chofer, Long> {

        void deleteChoferById(Long id);
        Optional<Chofer> findChoferById(Long id);


}

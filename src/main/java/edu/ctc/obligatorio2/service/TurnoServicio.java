package edu.ctc.obligatorio2.service;

import edu.ctc.obligatorio2.entity.Coche;
import edu.ctc.obligatorio2.entity.Turno;
import edu.ctc.obligatorio2.exception.TurnoNotFoundException;
import edu.ctc.obligatorio2.repository.TurnoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TurnoServicio {
    private final TurnoRepo turnoRepo;

    @Autowired
    public TurnoServicio(TurnoRepo turnoRepo) {
        this.turnoRepo = turnoRepo;
    }

    public Turno addTurno(Turno turno){
        return turnoRepo.save(turno);
    }

    public List<Turno> findAllTurnos(){
        return turnoRepo.findAll();
    }

    public Turno findTurnoById(Long id){
        return turnoRepo.findTurnoById(id)
                .orElseThrow(()-> new TurnoNotFoundException("El turno con el id:"+ id + "no fue encontrado"));
    }

    public Turno updateTurno(Turno turno){
        return turnoRepo.save(turno);
    }

    public void deleteTurno(Long id){
        turnoRepo.deleteTurnoById(id);
    }

    //Consulta 1
    public List<Coche> cochesParaUnTurno(Long id){
        List<Coche> coches = turnoRepo.cochesParaUnTurno(id);
        return coches;
    }
}

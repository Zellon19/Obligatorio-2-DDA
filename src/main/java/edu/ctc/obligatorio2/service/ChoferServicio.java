package edu.ctc.obligatorio2.service;

import edu.ctc.obligatorio2.entity.Chofer;
import edu.ctc.obligatorio2.exception.ChoferNotFoundException;
import edu.ctc.obligatorio2.repository.ChoferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChoferServicio {
    private final ChoferRepo choferRepo;

    @Autowired
    public ChoferServicio(ChoferRepo choferRepo) {
        this.choferRepo = choferRepo;
    }

    public Chofer addChofer(Chofer chofer){
        return choferRepo.save(chofer);
    }

    public List<Chofer> findAllChoferes(){
        return choferRepo.findAll();
    }

    public Chofer findChoferById(Long id){
        return choferRepo.findChoferById(id)
                .orElseThrow(()-> new ChoferNotFoundException("El chofer con el id:"+ id + "no fue encontrado"));
    }

    public Chofer updateChofer(Chofer chofer){
        return choferRepo.save(chofer);
    }

    public void deleteChofer(Long id){
        choferRepo.deleteChoferById(id);
    }
}

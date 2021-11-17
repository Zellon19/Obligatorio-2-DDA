package edu.ctc.obligatorio2.service;

import edu.ctc.obligatorio2.entity.Coche;
import edu.ctc.obligatorio2.exception.CocheNotFoundException;
import edu.ctc.obligatorio2.repository.CocheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CocheServicio {
    private final CocheRepo cocheRepo;

    @Autowired
    public CocheServicio(CocheRepo cocheRepo) {
        this.cocheRepo = cocheRepo;
    }

    public Coche addCoche(Coche coche){
        return cocheRepo.save(coche);
    }

    public List<Coche> findAllCoches(){
        return cocheRepo.findAll();
    }

    public Coche findCocheById(Long id){
        return cocheRepo.findCocheById(id)
                .orElseThrow(()-> new CocheNotFoundException("El coche con el id:"+ id + "no fue encontrado"));
    }

    public Coche updateCoche(Coche coche){
        return cocheRepo.save(coche);
    }

    public void deleteCoche(Long id){
        cocheRepo.deleteCocheById(id);
    }
}

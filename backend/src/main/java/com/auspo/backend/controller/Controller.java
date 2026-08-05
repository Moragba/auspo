package com.auspo.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auspo.backend.model.AltersKlasse;
import com.auspo.backend.model.Disziplin;
import com.auspo.backend.model.Verband;
import com.auspo.backend.repo.AltersKlasseRepo;
import com.auspo.backend.repo.DisziplinRepo;
import com.auspo.backend.repo.VerbandRepo;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class Controller {

    private final VerbandRepo verbandRepo;
    private final AltersKlasseRepo altersKlasseRepo;
    private final DisziplinRepo disziplinRepo;
    
    public Controller(VerbandRepo verbandRepo, AltersKlasseRepo altersKlasseRepo, DisziplinRepo disziplinRepo) {
        this.verbandRepo = verbandRepo;
        this.altersKlasseRepo = altersKlasseRepo;
        this.disziplinRepo = disziplinRepo;
    }

    
   
    
    @GetMapping("/verbaende")
    public List<Verband> getVerbaende() {
        return verbandRepo.findAll();
    }

    @GetMapping("/altersklassen")
    public List<AltersKlasse> getAltersklassen() {
        return altersKlasseRepo.findAll();
    }

    @GetMapping("/disziplinen")
    public List<Disziplin> getDisziplinen() {
        return disziplinRepo.findAll();
    }
}

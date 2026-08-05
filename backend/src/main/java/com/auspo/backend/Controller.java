package com.auspo.backend;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auspo.backend.model.Verband;
import com.auspo.backend.repo.VerbandRepo;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class Controller {

    private final VerbandRepo verbandRepo;
    
    public Controller(VerbandRepo verbandRepo) {
        this.verbandRepo = verbandRepo;
    }

    
    @GetMapping("/verbaende")
    public List<Verband> getVerbaende() {
        return verbandRepo.findAll();
    }
}

package com.auspo.backend.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.auspo.backend.model.Verband;
import com.auspo.backend.repo.VerbandRepo;

@Component
public class VerbandInit implements CommandLineRunner {
    private final VerbandRepo verbandRepo;
    
    public VerbandInit(VerbandRepo verbandRepo) {
        this.verbandRepo = verbandRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Dateninitialisierung 'Verband-Table' gestartet...");

        Verband dsb = new Verband();
        dsb.setName("Deutscher Schützenbund");
        dsb.setKuerzel("DSB");
        verbandRepo.save(dsb);

        Verband nssv = new Verband();
        nssv.setName("Nationale Schützenverband");
        nssv.setKuerzel("NSSV");
        verbandRepo.save(nssv);

        System.out.println("Dateninitialisierung 'Verband-Table' abgeschlossen.");
        
    }
    
}

package com.auspo.backend.data;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.auspo.backend.model.Disziplin;
import com.auspo.backend.repo.DisziplinRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Component
public class DisziplinInit implements CommandLineRunner {
    private final DisziplinRepo disziplienRepo;
    private final ObjectMapper objectMapper;
    private List<Disziplin> disziplinenList;
    
    public DisziplinInit(DisziplinRepo disziplienRepo) {
        this.disziplienRepo = disziplienRepo;
        this.objectMapper = new ObjectMapper();
    }

    @PostConstruct 
    public void loadJsonData() {
        try {
            // Lädt die Datei aus src/main/resources/
            InputStream inputStream = new ClassPathResource("dsb_disziplinen.json").getInputStream();
            
            // Konvertiert das JSON-Array direkt in eine List<Disziplin>
            disziplinenList = objectMapper.readValue(inputStream, new TypeReference<List<Disziplin>>(){});
            
            

            System.out.println(disziplinenList.size() + " Disziplinen erfolgreich aus JSON geladen!");
        } catch (Exception e) {
            System.err.println("Fehler beim Laden der JSON-Datei:");
            e.printStackTrace();
        }

        for (Disziplin disziplin : disziplinenList) {
            disziplienRepo.save(disziplin);
        }
    }

    @Override
    public void run(String... args) throws Exception {      
    }
    
}

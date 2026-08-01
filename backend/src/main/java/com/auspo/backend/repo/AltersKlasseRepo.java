package com.auspo.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auspo.backend.model.AltersKlasse;

@Repository
public interface AltersKlasseRepo extends JpaRepository<AltersKlasse, Long> {
    
}

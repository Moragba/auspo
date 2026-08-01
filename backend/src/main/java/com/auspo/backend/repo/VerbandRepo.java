package com.auspo.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auspo.backend.model.Verband;
@Repository
public interface VerbandRepo extends JpaRepository<Verband, Long> {
    
}

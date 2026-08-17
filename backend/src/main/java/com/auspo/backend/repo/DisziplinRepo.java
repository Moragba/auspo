package com.auspo.backend.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auspo.backend.model.Disziplin;

@Repository
public interface DisziplinRepo extends JpaRepository<Disziplin, Long> {
    List<Disziplin> findByVerbandIgnoreCase(String verband);
}

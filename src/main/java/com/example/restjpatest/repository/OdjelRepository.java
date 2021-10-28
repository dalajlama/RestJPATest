package com.example.restjpatest.repository;

import com.example.restjpatest.dto.Odjel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OdjelRepository extends JpaRepository<Odjel, Long> {
}

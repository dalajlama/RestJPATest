package com.example.restjpatest.repository;

import com.example.restjpatest.DTO.Zaposlenik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZaposlenikRepository extends JpaRepository<Zaposlenik, Long> {

}

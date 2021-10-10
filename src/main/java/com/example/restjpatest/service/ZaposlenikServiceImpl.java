package com.example.restjpatest.service;

import com.example.restjpatest.DTO.Zaposlenik;
import com.example.restjpatest.repository.ZaposlenikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ZaposlenikServiceImpl implements ZaposlenikService{

    @Autowired
    ZaposlenikRepository zaposlenikRepository;

    @Override
    public List<Zaposlenik> dohvatiSveZaposlenike() {
        System.out.println("Dohvati sve");
        return zaposlenikRepository.findAll();
    }
}

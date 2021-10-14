package com.example.restjpatest.service;

import com.example.restjpatest.DTO.Zaposlenik;
import com.example.restjpatest.repository.ZaposlenikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ZaposlenikServiceImpl implements ZaposlenikService{

    //ukoliko ne stavimo final ovdje će biti null pointer
    final ZaposlenikRepository zaposlenikRepository;
    /*
     Anotacija @RequiredArgsConstructor nam kreira ovaj konstruktor u pozadini a koji je potreban
     da se ne mora raditi @Autowired nad ZaposlenikRepository
    @Autowired
    public ZaposlenikServiceImpl(ZaposlenikRepository zaposlenikRepository) {
        this.zaposlenikRepository = zaposlenikRepository;
    }*/

    @Override
    public List<Zaposlenik> dohvatiSveZaposlenike() {
        System.out.println("Dohvati sve");
        return zaposlenikRepository.findAll();
    }
}

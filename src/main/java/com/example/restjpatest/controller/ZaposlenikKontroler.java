package com.example.restjpatest.controller;

import com.example.restjpatest.DTO.Zaposlenik;
import com.example.restjpatest.service.ZaposlenikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ZaposlenikKontroler {
 @Autowired
 ZaposlenikService zaposlenikservice;

    @GetMapping("/zaposlenik")
    public List<Zaposlenik> dohvatiSveZaposlenike(){
        System.out.println("Dohvaćam sve zaposlenike!");
        return zaposlenikservice.dohvatiSveZaposlenike();
    }
}
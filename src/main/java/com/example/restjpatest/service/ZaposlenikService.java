package com.example.restjpatest.service;

import com.example.restjpatest.DTO.Zaposlenik;

import java.util.List;

public interface ZaposlenikService {
    List<Zaposlenik> dohvatiSveZaposlenike();
}

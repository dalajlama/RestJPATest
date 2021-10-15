package com.example.restjpatest.service;

import com.example.restjpatest.DTO.Zaposlenik;

import java.util.List;
import java.util.Optional;

public interface ZaposlenikService {
    // dohvati zaposlenika prema id-u
    Optional<Zaposlenik> dohvatiZaposlenika(Long id);
    // dohvati sve zaposlenike
    List<Zaposlenik> dohvatiSveZaposlenike();
    // spremi zaposlenika u bazu
    Zaposlenik spremiZaposlenika(Zaposlenik zaposlenik);
    // obriši zaposlenika prema id-u
    String obrisiZaposlenika(Long id);
    // azuriranje zaposlenika prema id-u
    Zaposlenik azurirajZaposlenika(Zaposlenik zaposlenik);
}

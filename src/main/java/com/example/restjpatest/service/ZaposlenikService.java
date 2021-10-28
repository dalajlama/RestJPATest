package com.example.restjpatest.service;

import com.example.restjpatest.dto.Zaposlenik;

import java.util.List;
import java.util.Optional;

public interface ZaposlenikService {
    // dohvati zaposlenika prema id-u
    Optional<Zaposlenik> dohvatiZaposlenika(Long id);
    // dohvati sve zaposlenike prema broju stranice i velicini stranice
    List<Zaposlenik> dohvatiSveZaposlenike(int pageNumber, int pageSize);
    // spremi zaposlenika u bazu
    Zaposlenik spremiZaposlenika(Zaposlenik zaposlenik);
    // obriši zaposlenika prema id-u
    String obrisiZaposlenika(Long id);
    // azuriranje zaposlenika prema id-u
    Zaposlenik azurirajZaposlenika(Zaposlenik zaposlenik);
    // Dohvacanje zaposlenika po imenu
    List<Zaposlenik> dohvatiZaposlenikaPoImenu(String ime);
    // Dohvacanje zaposlenika po imenu i lokaciji
    List<Zaposlenik> dohvatiZaposlenikaPoImenuILokaciji(String ime, String lokacija);
    // Dohvati zaposlenika koji u imenu imaju xxx
    List<Zaposlenik> dohvatiZaposlenikaKojiUimenuImaju(String imeSadrzi);
    // Dohvati zaposlenika kojemu je ime kao xxx
    List<Zaposlenik> dohvatiZaposlenikaKojemujeImeKao(String imeKao);
    // Dohvati zaposlenika koji rade u određenom odjelu JPA
    List<Zaposlenik> dohvatiZapslenikaPremaOdjelu(String imeOdjela);
    //Dohvati zaposlenika koji rade u određenom odjelu JPQL
    List<Zaposlenik> dohvatiZapslenikaPremaOdjeluJpql(String imeOdjela);


}

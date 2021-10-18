package com.example.restjpatest.service;

import com.example.restjpatest.DTO.Zaposlenik;
import com.example.restjpatest.repository.ZaposlenikRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
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
    public Optional<Zaposlenik> dohvatiZaposlenika(Long id) {
        log.info("Dohvaćam zaposlenika  id: {}",id );
        return zaposlenikRepository.findById(id);
    }

    @Override
    public List<Zaposlenik> dohvatiSveZaposlenike() {
        log.info("Dohvati sve zaposlenike");
        return zaposlenikRepository.findAll();
    }

    @Override
    public Zaposlenik spremiZaposlenika(Zaposlenik zaposlenik) {
        log.info("Spremam detalje u bazu za zaposlenika: {} ", zaposlenik.getIme());
        return zaposlenikRepository.save(zaposlenik);
    }

    @Override
    public String obrisiZaposlenika(Long id) {
        log.info("Brišem zaposlenika id: {} ", id);
        zaposlenikRepository.deleteById(id);
        return "Zaposlenik " + id + " obrisan!";
    }

    @Override
    public Zaposlenik azurirajZaposlenika(Zaposlenik zaposlenik) {
        log.info("Vršim update Zaposlenika: {}  pod id:  {}", zaposlenik.getIme(), zaposlenik.getId());
        return zaposlenikRepository.save(zaposlenik);
    }
}

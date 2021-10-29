package com.example.restjpatest.service;

import com.example.restjpatest.dto.Zaposlenik;
import com.example.restjpatest.repository.ZaposlenikRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<Zaposlenik> dohvatiSveZaposlenike(int pageNumber, int pageSize) {
        log.info("Dohvati sve zaposlenike po stranicama");
        // Pageable pages = PageRequest.of(pageNumber, pageSize);
        //moze se dodati sortiranje po nekom fieldu/stupcu
        // Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
        // moze se dodati sortiranje i po vise fielda/stupca
        Pageable pages = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id", "ime");
        return zaposlenikRepository.findAll(pages).getContent();
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

    @Override
    public List<Zaposlenik> dohvatiZaposlenikaPoImenu(String ime) {
       // return zaposlenikRepository.findByIme(ime);
        return null;
    }
    @Override
    public List<Zaposlenik> dohvatiZaposlenikaPoImenuILokaciji(String ime, String lokacija) {
       // return zaposlenikRepository.findByImeAndLokacija(ime,lokacija);
        return null;
    }

    @Override
    public List<Zaposlenik> dohvatiZaposlenikaKojiUimenuImaju(String imeSadrzi) {
        // implementiran i sorting mehanizam
        //return zaposlenikRepository.findByImeContaining(imeSadrzi,Sort.by(Sort.Direction.DESC, "id"));
        return null;
    }

    @Override
    public List<Zaposlenik> dohvatiZaposlenikaKojemujeImeKao(String imeKao) {
        //return zaposlenikRepository.findByImeLike("%" + imeKao + "%");
        return null;
    }

    @Override
    public List<Zaposlenik> dohvatiZapslenikaPremaOdjelu(String imeOdjela) {
        //return zaposlenikRepository.findByOdjelImeOdjela(imeOdjela);
        return null;
    }

    @Override
    public List<Zaposlenik> dohvatiZapslenikaPremaOdjeluJpql(String imeOdjela) {
        System.out.println("ime odjela service impl = " + imeOdjela);
        //return zaposlenikRepository.dovatiZaposlenikePoImenuOdjela(imeOdjela);
        return null;
    }

}

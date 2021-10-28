package com.example.restjpatest.repository;

import com.example.restjpatest.dto.Zaposlenik;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZaposlenikRepository extends PagingAndSortingRepository<Zaposlenik, Long> {
        // findByName = SELECT * FROM zaposlenik WHERE ime = oliver
        List<Zaposlenik> findByIme(String ime);
        // SELECT * FROM zasposlenik WHERE ime="oliver" AND lokacija="Osijek"
        List<Zaposlenik> findByImeAndLokacija(String ime, String lokacija);
        // SELECT * FROM zaposlenik WHERE name LIKE "%ive%"
        List<Zaposlenik> findByImeContaining(String kaoIme, Sort sort);
        // za Like se prilikom poziva repositorya moraju koristiti wildcards zaposlenikRepository.findByImeLike("%" + imeKao + "%"); oduzimanjem i dodavanjem wildcarda možemo i filtrirati slično kao EndsWith i Begins Withs*/
        List<Zaposlenik> findByImeLike(String kaoIme);
        // ImeOdjela je ime varijable u Odjel klasi (String imeOdjela) a koja gleda na stupac  @Column(name = "ime_odjela")
        // Ukoliko stavimo findByOdjelIme_odjela bacilo bi grešku da ne može pronaci ime
        // jer findByOdjel znaci pronadji u tablici Odjel stupac naziva ime jer JPA gleda _ kao u javi.
        // pa bi trazio odjel.ime.odjela stoga moramo dati Column name za taj stupac a dodjeliti varijablu po želji
        List<Zaposlenik> findByOdjelImeOdjela(String imeOdjela);
}

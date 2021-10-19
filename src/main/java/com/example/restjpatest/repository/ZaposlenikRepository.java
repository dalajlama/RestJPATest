package com.example.restjpatest.repository;

import com.example.restjpatest.DTO.Zaposlenik;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        // iza svake OR AND ili nešto slicno treba prosljediti argument
        List<Zaposlenik> findByImeLikeOrImeIs(String kaoIme, String orIme);
        // Pisanje vlastitih JPQL querya
        // ako zelimo koristiti neko drugo ime (koje nije ima stupca) korisitimo @Param("ime stupca") novo ime
        // SELECT anotacija @Query uvijek pocinje sa FROM i onda ime tablice i ostali upiti
        @Query("FROM Zaposlenik WHERE  ime = :ime OR lokacija = :nestonesto")
        List<Zaposlenik> pronadjiZaposlenikaPoImenuIliLokaciji(String ime, @Param("nestonesto") String lokacijaZaposlenika);
        // Delete anotacija @Query pocinje sa delete
        // delete vraca integer kao broj polja koja su obrisana..takodjer umjesto integer mozemo staviti void
        // za create, update ili delete radnje mora se dodati @Modifying i @Transactional
        @Transactional
        @Modifying
        @Query("DELETE FROM Zaposlenik WHERE ime = :ime")
        Integer obrisiZaposlenikePodImenom(String ime);


}

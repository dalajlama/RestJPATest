package com.example.restjpatest.controller;

import com.example.restjpatest.dto.Odjel;
import com.example.restjpatest.dto.Zaposlenik;
import com.example.restjpatest.repository.OdjelRepository;
import com.example.restjpatest.repository.ZaposlenikRepository;
import com.example.restjpatest.request.ZaposlenikRequest;
import com.example.restjpatest.service.ZaposlenikService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//@RequestMapping("/api/v1")  da bi svi kontroleri koristili isti mapping moramo ga prebaciti u app porperties
@RequiredArgsConstructor
@RestController // zamjenjuje @Controler + @ResponseBody
public class ZaposlenikKontroler {
    //@Autowired
    final ZaposlenikService zaposlenikservice;
// Privremeno preskaćemo service layer i autowiramo odjel i zaposlenik repository
    @Autowired
    private OdjelRepository odjelRepository;

    @Autowired
    private ZaposlenikRepository zaposlenikRepository;

 // Ukoliko imamo ovaj constructor nije nam potreban  @Autowired (preporučeno je)
    /* lombok @RequiredArgsConstructor on u pozadini kreira ovo dolje
    public ZaposlenikKontroler(ZaposlenikService zaposlenikservice) {
        this.zaposlenikservice = zaposlenikservice;
    }*/

    // Ukoliko nema u app propertisima možemo pružiti default value sa : v2
    @Value("${app.version: v2}")
    private String version;
    @Value("${app.name}")
    private String appName;

    @GetMapping("/version")
    public String dohvatiDetaljeAplikacije(){
        return "Aplikacija: " + appName + " Verzija: " + version;
    }



    //localhost:9092/context-path iz app propertiesa/zaposlenici
    @GetMapping("/zaposlenici")
    // Ovo može bit i bez ResponseEntity pa ce vracati genericki status
    // ovako možemo kontrolirati što se vraca
    public ResponseEntity <List<Zaposlenik>> dohvatiSveZaposlenike(@RequestParam int pageNumber, @RequestParam int pageSize){
        return new ResponseEntity<>(zaposlenikservice.dohvatiSveZaposlenike(pageNumber, pageSize), HttpStatus.OK);
    }

    //dohvat jednog zaposlenika pomoću path Varijable
    //localhost:9092/context-path iz app propertiesa/zaposlenik/1
    @GetMapping("/zaposlenik/{id}")
    public  ResponseEntity <Optional<Zaposlenik>> dohvatiZaposlenika(@PathVariable("id") Long id){
        return new ResponseEntity<>(zaposlenikservice.dohvatiZaposlenika(id), HttpStatus.OK) ;
    }


    @PutMapping("/zaposlenik")
    public String dodajNovogZaposlenika(@RequestParam("imeNovogZaposlenika") String ime, @RequestParam String prezime){
        //ukoliko je request param isti kao varijabla ne mora se stavljati posebno ime za requestparam
        //gore u primjeru prezime
        return "Doajem novog zaposlenika " +  ime + " " + prezime;
     }

     //Update Zaposlenika
    @PutMapping("/zaposlenik/{id}")
    public ResponseEntity <Zaposlenik> updateZaposlenika(@PathVariable Long id, @RequestBody Zaposlenik zaposlenik){
        zaposlenik.setId(id);
        return  new ResponseEntity<>(zaposlenikservice.azurirajZaposlenika(zaposlenik), HttpStatus.OK) ;
    }
    // Kreiranje zaposlenika
     @PostMapping("/zaposlenik")
     public ResponseEntity <String> spremiZaposlenika(@Valid @RequestBody ZaposlenikRequest zaposlenikRequest){

        Zaposlenik zaposlenik = new Zaposlenik(zaposlenikRequest);
        zaposlenik.setIme(zaposlenikRequest.getIme_zaposlenika());
        zaposlenikRepository.save(zaposlenik);

        for(String imeOdjela : zaposlenikRequest.getIme_odjela()){
            Odjel odjel = new Odjel();
            odjel.setIme_odjela(imeOdjela);
            odjel.setZasposlenik(zaposlenik);
            odjelRepository.save(odjel);
        }
        return new ResponseEntity<>(" Zaposlenik uspješno dodan!", HttpStatus.CREATED);
     }

    //brisanje zaposlenika pomoću requestParametara
    @DeleteMapping("/zaposlenik")
    public ResponseEntity <String>  obrisiZaposlenika(@RequestParam Long id){
        return new ResponseEntity<>(zaposlenikservice.obrisiZaposlenika(id), HttpStatus.NO_CONTENT);
    }
    @GetMapping("/zaposlenik/filterPoImenu")
    public ResponseEntity<List <Zaposlenik>> dohvatiZaposlenikePoImenu(@RequestParam String ime){
        return new ResponseEntity<>(zaposlenikservice.dohvatiZaposlenikaPoImenu(ime), HttpStatus.OK);
    }

    @GetMapping("/zapsolenik/filterPoImenuILokaciji")
    public ResponseEntity<List<Zaposlenik>> dohvatiZaposlenikePoImenuILokaciji(@RequestParam String ime, @RequestParam String lokacija){
        return new ResponseEntity<>(zaposlenikservice.dohvatiZaposlenikaPoImenuILokaciji(ime, lokacija), HttpStatus.OK);
    }

    @GetMapping("/zapsolenik/filterPoImenuSadrzi")
    public ResponseEntity<List<Zaposlenik>> dohvatiZaposlenikePoImenuKojeSadrzi(@RequestParam String imeSadrzi){
        return new ResponseEntity<>(zaposlenikservice.dohvatiZaposlenikaKojiUimenuImaju(imeSadrzi), HttpStatus.OK);
    }

    @GetMapping("/zapsolenik/filterPoImenuKao")
    public ResponseEntity<List<Zaposlenik>> dohvatiZaposlenikePoImenuKao(@RequestParam String imeKao){
        return new ResponseEntity<>(zaposlenikservice.dohvatiZaposlenikaKojemujeImeKao("%" + imeKao + "%"), HttpStatus.OK);
    }

    // Dohvacanje zaposlenika prema odjelu pomocu finder metoda JPA
    @GetMapping("/zaposlenik")
    public ResponseEntity<List<Zaposlenik>> dohvatiZaposlenikeOdjela(@RequestParam String imeOdjela){
        return new ResponseEntity<>(zaposlenikservice.dohvatiZapslenikaPremaOdjelu(imeOdjela), HttpStatus.OK);
    }

    //dohvacanje zaposlenika prema odjelu pomocu JPQL-a iza jpql ne smije biti /
    @GetMapping("/zaposlenik/jpql")
    public ResponseEntity<List<Zaposlenik>> dohvatiZaposlenikeOdjelaJpql(@RequestParam String imeOdjela){
        System.out.println("ime odjela controler = " + imeOdjela);
        return new ResponseEntity<List<Zaposlenik>>(zaposlenikservice.dohvatiZapslenikaPremaOdjeluJpql(imeOdjela), HttpStatus.OK);
    }
}
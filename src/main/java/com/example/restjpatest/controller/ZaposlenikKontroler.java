package com.example.restjpatest.controller;

import com.example.restjpatest.DTO.Zaposlenik;
import com.example.restjpatest.service.ZaposlenikService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.HttpParser;
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
        return new ResponseEntity<List<Zaposlenik>>(zaposlenikservice.dohvatiSveZaposlenike(pageNumber, pageSize), HttpStatus.OK);
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
     public ResponseEntity <Zaposlenik> spremiZaposlenika(@Valid @RequestBody Zaposlenik zaposlenik){
        return  new ResponseEntity<>(zaposlenikservice.spremiZaposlenika(zaposlenik), HttpStatus.CREATED);
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
}
package com.example.restjpatest.controller;

import com.example.restjpatest.DTO.Zaposlenik;
import com.example.restjpatest.service.ZaposlenikService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    public List<Zaposlenik> dohvatiSveZaposlenike(){
        return zaposlenikservice.dohvatiSveZaposlenike();
    }

    //dohvat jednog zaposlenika pomoću path Varijable
    //localhost:9092/context-path iz app propertiesa/zaposlenik/1
    @GetMapping("/zaposlenik/{id}")
    public Optional<Zaposlenik> dohvatiZaposlenika(@PathVariable("id") Long id){
        return zaposlenikservice.dohvatiZaposlenika(id);
    }



    @PutMapping("/zaposlenik")
    public String dodajNovogZaposlenika(@RequestParam("imeNovogZaposlenika") String ime, @RequestParam String prezime){
        //ukoliko je request param isti kao varijabla ne mora se stavljati posebno ime za requestparam
        //gore u primjeru prezime
        return "Doajem novog zaposlenika " +  ime + " " + prezime;
     }

    @PutMapping("/zaposlenik/{id}")
    public Zaposlenik updateZaposlenika(@PathVariable Long id, @RequestBody Zaposlenik zaposlenik){
        zaposlenik.setId(id);
        return zaposlenikservice.azurirajZaposlenika(zaposlenik );
    }

     @PostMapping("/zaposlenik")
     public Zaposlenik spremiZaposlenika(@Valid @RequestBody Zaposlenik zaposlenik){
        return zaposlenikservice.spremiZaposlenika(zaposlenik);
     }

    //brisanje zaposlenika pomoću requestParametara
    @DeleteMapping("/zaposlenik")
    public String  obrisiZaposlenika(@RequestParam Long id){
        return zaposlenikservice.obrisiZaposlenika(id);
    }
}
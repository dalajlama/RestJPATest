package com.example.restjpatest.controller;

import com.example.restjpatest.DTO.Zaposlenik;
import com.example.restjpatest.service.ZaposlenikService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        System.out.println("Dohvaćam sve zaposlenike!");
        return zaposlenikservice.dohvatiSveZaposlenike();
    }

    //dohvat jednog zaposlenika pomoću path Varijable
    //localhost:9092/context-path iz app propertiesa/zaposlenik/1
    @GetMapping("/zaposlenik/{id}")
    public String dohvatiZaposlenik(@PathVariable("id") Long id){
        return "Dohvaćam zaposlenika sa id-jem : " + id;
    }
    //dohvat zaposlenika pomoću requestParametara
    @DeleteMapping("/zaposlenici")
    public String obrisiZaposlenika(@RequestParam("id") Long id){
        return "Brišem zaposlenika pod id-jem: " + id;
    }

    @PutMapping("/zaposlenik")
    public String dodajNovogZaposlenika(@RequestParam("imeNovogZaposlenika") String ime, @RequestParam String prezime){
        //ukoliko je request param isti kao varijabla ne mora se stavljati posebno ime za requestparam
        //gore u primjeru prezime
        return "Doajem novog zaposlenika " +  ime + " " + prezime;
     }

    @PutMapping("/zaposlenik/{id}")
    public Zaposlenik updateZaposlenika(@PathVariable Long id, @RequestBody Zaposlenik zaposlenik){
        System.out.println("Vršim update Zaposlenika: "  +" ID: " + id+ " Ime: " + zaposlenik.getIme());
        return zaposlenik;
    }

     @PostMapping("/zaposlenik")
     public String spremiZaposlenika(@RequestBody Zaposlenik zaposlenik){
        return "Spremam detalje u bazu za zaposlenika: " + zaposlenik.getIme();
     }
}
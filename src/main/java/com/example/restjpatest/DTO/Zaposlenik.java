package com.example.restjpatest.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "zaposlenik")
@RequiredArgsConstructor
public class Zaposlenik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    //@JsonProperty mapira navedeno(puno_ime) u ime
    //onda se u requestu mora poslati puno_ime umjesto ime
    @JsonProperty("puno_ime")
    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    //@JsonIgnore sakriva određeni field u responsu koji se vraća korisniku
    @JsonIgnore
    @Column(name = "godine")
    private Long godine;

    @Column(name = "lokacija")
    private String lokacija;

    @Column(name = "email")
    private String email;

    @Column(name = "odjel")
    private String odjel;

}
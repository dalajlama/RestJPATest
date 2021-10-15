package com.example.restjpatest.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "zaposlenik")
@RequiredArgsConstructor
public class Zaposlenik {

    @Id
    // ovo govori da se ID generira automatski
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
    // isto tako ga zanemaruje i prilikom updatea
    // @JsonIgnore
    @Column(name = "godine")
    private Long godine;

    @Column(name = "lokacija")
    private String lokacija;

    @Column(name = "email")
    private String email;

    @Column(name = "odjel")
    private String odjel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Zaposlenik that = (Zaposlenik) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
package com.example.restjpatest.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;
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
    // imena kolona nisu potrebna osim ako želimo mapirati na neko drugo ime
    // @Column(name="id")
    private Long id;

    // @JsonProperty mapira navedeno(puno_ime) u ime
    // onda se u requestu mora poslati puno_ime umjesto ime
    // Imena kolona nisu potrebna ukoliko su imena u tablici ista kao varijabla
    @JsonProperty("puno_ime")
    // @Column(name = "ime")
    @NotNull(message = "Ime ne smije biti prazno!")
    private String ime;

    // @Column(name = "prezime")
    private String prezime;

    //@JsonIgnore sakriva određeni field u responsu koji se vraća korisniku
    // isto tako ga zanemaruje i prilikom updatea
    // @JsonIgnore
    // @Column(name = "godine")
    // = 0L daje defaultnu vrijednost ukoliko nije providana
    private Long godine = 0L;

    // @Column(name = "lokacija")
    private String lokacija;

    // @Column(name = "email")
    @Email(message = "Uneseni mail nije validan!")
    private String email;

    // @Column(name = "odjel")
    // dodatno se može dodati da se vraca custom poruka
    @NotNull(message = "Odjel ne smije biti prazno polje!")
    private String odjel;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

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
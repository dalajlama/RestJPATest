package com.example.restjpatest.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "zaposlenik")
@RequiredArgsConstructor
public class Zaposlenik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "godine")
    private Long godine;

    @Column(name = "lokacija")
    private String lokacija;

    @Column(name = "email")
    private String email;

    @Column(name = "odjel")
    private String odjel;

}
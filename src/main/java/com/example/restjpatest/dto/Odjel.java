package com.example.restjpatest.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "odjel")
@RequiredArgsConstructor
public class Odjel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;
    // Ovdje je potreban Column name zbog findByOdjelImeOdjela iz ZaposlenikRepository
    @Column(name = "ime_odjela")
    private String imeOdjela;

}

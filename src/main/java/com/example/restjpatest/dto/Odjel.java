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

    private String ime_odjela;

    @ManyToOne
    @JoinColumn(name ="zaposlenik_id")
    private Zaposlenik zasposlenik;

}

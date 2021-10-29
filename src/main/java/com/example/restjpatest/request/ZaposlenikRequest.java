package com.example.restjpatest.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ZaposlenikRequest {
    private String ime_zaposlenika;
    private List<String> ime_odjela;
}

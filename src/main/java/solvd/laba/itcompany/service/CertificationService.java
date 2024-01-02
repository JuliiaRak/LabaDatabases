package solvd.laba.itcompany.service;

import solvd.laba.itcompany.domain.Certification;

import java.util.List;

public interface CertificationService {

    void create(Certification certification);

    List<Certification> findAll();

}

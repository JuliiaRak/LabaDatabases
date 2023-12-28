package solvd.laba.itcompany.service;

import solvd.laba.itcompany.domain.Certification;

import java.util.List;

public interface CertificationService {
    void create(Certification certification);

    void update(Certification certification);

    Certification findById(Long certificationId);

    List<Certification> findAll();

    void deleteById(Long certificationId);

}

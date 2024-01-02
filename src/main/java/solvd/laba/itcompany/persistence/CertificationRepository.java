package solvd.laba.itcompany.persistence;

import solvd.laba.itcompany.domain.Certification;

import java.util.List;

public interface CertificationRepository {

    void create(Certification certification);

    List<Certification> findAll();

}

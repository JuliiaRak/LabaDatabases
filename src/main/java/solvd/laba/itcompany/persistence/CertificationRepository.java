package solvd.laba.itcompany.persistence;

import solvd.laba.itcompany.domain.Certification;
import solvd.laba.itcompany.domain.Department;

import java.util.List;

public interface CertificationRepository {

    void create(Certification certification);

    void update(Certification certification);

    Certification findById(Long certificationId);

    List<Certification> findAll();

    void deleteById(Long certificationId);

}

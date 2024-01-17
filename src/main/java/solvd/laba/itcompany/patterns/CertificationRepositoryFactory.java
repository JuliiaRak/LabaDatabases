package solvd.laba.itcompany.patterns;

import solvd.laba.itcompany.persistence.CertificationRepository;
import solvd.laba.itcompany.persistence.SkillRepository;
import solvd.laba.itcompany.persistence.impl.CertificationJdbsRepository;
import solvd.laba.itcompany.persistence.impl.CertificationMyBatisRepository;

public class CertificationRepositoryFactory extends AbstractFactory {

    @Override
    CertificationRepository getCertificationRepository(String type) {
        if (type == null || type.isBlank()) {
            return null;
        }
        if (type.equalsIgnoreCase("JDBC")) {
            return new CertificationJdbsRepository();
        } else if (type.equalsIgnoreCase("MyBatis")) {
            return new CertificationMyBatisRepository();
        } else return null;
    }

    @Override
    SkillRepository getSkillRepository(String type) {
        return null;
    }
}

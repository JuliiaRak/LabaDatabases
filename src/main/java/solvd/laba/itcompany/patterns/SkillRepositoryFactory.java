package solvd.laba.itcompany.patterns;

import solvd.laba.itcompany.persistence.CertificationRepository;
import solvd.laba.itcompany.persistence.SkillRepository;
import solvd.laba.itcompany.persistence.impl.SkillJdbsRepository;
import solvd.laba.itcompany.persistence.impl.SkillMyBatisRepository;

public class SkillRepositoryFactory extends AbstractFactory {

    @Override
    CertificationRepository getCertificationRepository(String type) {
        return null;
    }

    @Override
    SkillRepository getSkillRepository(String type) {
        if (type == null || type.isBlank()) {
            return null;
        }
        if (type.equalsIgnoreCase("JDBC")) {
            return new SkillJdbsRepository();
        } else if (type.equalsIgnoreCase("MyBatis")) {
            return new SkillMyBatisRepository();
        } else return null;
    }
}

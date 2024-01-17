package solvd.laba.itcompany.patterns;

import solvd.laba.itcompany.persistence.CertificationRepository;
import solvd.laba.itcompany.persistence.SkillRepository;

public abstract class AbstractFactory {
    abstract CertificationRepository getCertificationRepository(String type);
    abstract SkillRepository getSkillRepository(String type);
}

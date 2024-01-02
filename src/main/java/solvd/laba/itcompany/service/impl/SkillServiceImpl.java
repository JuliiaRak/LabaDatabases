package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Skill;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.SkillRepository;
import solvd.laba.itcompany.persistence.impl.SkillJdbsRepository;
import solvd.laba.itcompany.service.SkillService;

public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(){
        skillRepository = new SkillJdbsRepository();
    }

    @Override
    public void create(Skill skill) {
        try {
            skillRepository.create(skill);
        } catch (Exception e) {
            throw new ServiceException("Failed to create skill", e);
        }
    }
}
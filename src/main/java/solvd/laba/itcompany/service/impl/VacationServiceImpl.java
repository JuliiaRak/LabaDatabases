package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Vacation;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.VacationRepository;
import solvd.laba.itcompany.persistence.impl.VacationJdbsRepository;
import solvd.laba.itcompany.persistence.impl.VacationMyBatisRepository;
import solvd.laba.itcompany.service.VacationService;

public class VacationServiceImpl implements VacationService {
    private final VacationRepository vacationRepository;

    public VacationServiceImpl() {
        vacationRepository = new VacationMyBatisRepository();
    }

    @Override
    public void create(Vacation vacation) {
        try {
            vacationRepository.create(vacation);
        } catch (Exception e) {
            throw new ServiceException("Failed to create vacation", e);
        }
    }
}

package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Certification;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.CertificationRepository;
import solvd.laba.itcompany.persistence.impl.CertificationJdbsRepository;
import solvd.laba.itcompany.service.CertificationService;

import java.util.List;

public class CertificationServiceImpl implements CertificationService {

    private final CertificationRepository certificationRepository;

    public CertificationServiceImpl() {
        certificationRepository = new CertificationJdbsRepository();
    }

    @Override
    public void create(Certification certification) {
        try {
            certificationRepository.create(certification);
        } catch (Exception e) {
            throw new ServiceException("Failed to create certification", e);
        }
    }

    @Override
    public List<Certification> findAll() {
        try {
            return certificationRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to find certifications", e);
        }
    }

}

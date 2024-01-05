package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Service;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.ServiceRepository;
import solvd.laba.itcompany.persistence.impl.ServiceJdbsRepository;
import solvd.laba.itcompany.persistence.impl.ServiceMyBatisRepository;
import solvd.laba.itcompany.service.ServiceService;

import java.util.List;

public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl() {
        serviceRepository = new ServiceMyBatisRepository();
    }

    @Override
    public void create(Service service) {
        try {
            serviceRepository.create(service);
        } catch (Exception e) {
            throw new ServiceException("Failed to create service", e);
        }
    }

    @Override
    public List<Service> findAll() {
        try {
            return serviceRepository.findAll();
        } catch (Exception e) {
            throw new ServiceException("Failed to find services", e);
        }
    }
}


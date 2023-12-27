package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.Service;
import solvd.laba.itcompany.persistence.ServiceRepository;

import java.util.List;

public class ServiceJdbsRepository implements ServiceRepository {
    @Override
    public void create(Service service) {

    }

    @Override
    public Service findById(Long serviceId) {
        return null;
    }

    @Override
    public List<Service> findAll() {
        return null;
    }
}

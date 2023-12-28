package solvd.laba.itcompany.service;

import solvd.laba.itcompany.domain.Service;

import java.util.List;

public interface ServiceService {

    void create(Service service);

    Service findById(Long serviceId);

    List<Service> findAll();

}

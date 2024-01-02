package solvd.laba.itcompany.persistence;

import solvd.laba.itcompany.domain.Service;

import java.util.List;

public interface ServiceRepository {

    void create(Service service);

    List<Service> findAll();

}

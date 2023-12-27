package solvd.laba.itcompany.persistence;

import solvd.laba.itcompany.domain.ContactOfClient;

import java.util.List;

public interface ContactOfClientRepository {

    void create(ContactOfClient contactOfClient);

    ContactOfClient findById(Long contactOfClientId);

    List<ContactOfClient> findAll();

}

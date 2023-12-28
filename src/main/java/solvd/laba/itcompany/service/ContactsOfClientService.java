package solvd.laba.itcompany.service;

import solvd.laba.itcompany.domain.ContactOfClient;

import java.util.List;

public interface ContactsOfClientService {

    void create(ContactOfClient contactOfClient);

    ContactOfClient findById(Long contactOfClientId);

    List<ContactOfClient> findAll();

}

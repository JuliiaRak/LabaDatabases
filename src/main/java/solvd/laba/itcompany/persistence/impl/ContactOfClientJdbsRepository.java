package solvd.laba.itcompany.persistence.impl;

import solvd.laba.itcompany.domain.ContactOfClient;
import solvd.laba.itcompany.persistence.ContactOfClientRepository;

import java.util.List;

public class ContactOfClientJdbsRepository implements ContactOfClientRepository {
    @Override
    public void create(ContactOfClient contactOfClient) {

    }

    @Override
    public ContactOfClient findById(Long contactOfClientId) {
        return null;
    }

    @Override
    public List<ContactOfClient> findAll() {
        return null;
    }
}

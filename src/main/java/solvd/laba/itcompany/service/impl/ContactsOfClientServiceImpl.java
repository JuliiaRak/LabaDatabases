package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.ContactOfClient;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.ContactOfClientRepository;
import solvd.laba.itcompany.persistence.impl.ContactOfClientJdbsRepository;
import solvd.laba.itcompany.service.ContactsOfClientService;

public class ContactsOfClientServiceImpl implements ContactsOfClientService {
    private final ContactOfClientRepository contactOfClientRepository;

    public ContactsOfClientServiceImpl() {
        contactOfClientRepository = new ContactOfClientJdbsRepository();
    }

    @Override
    public void create(ContactOfClient contactOfClient) {
        try {
            contactOfClientRepository.create(contactOfClient);
        } catch (Exception e) {
            throw new ServiceException("Failed to create contact of client", e);
        }
    }

}

package solvd.laba.itcompany.service;

import solvd.laba.itcompany.domain.Client;

public interface ClientService {

    void create(Client client);

    Client findById(Long clientId);

}

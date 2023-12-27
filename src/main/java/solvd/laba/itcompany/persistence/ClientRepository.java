package solvd.laba.itcompany.persistence;

import solvd.laba.itcompany.domain.Client;

public interface ClientRepository {

    void create(Client client);

    Client findById(Long clientId);

}

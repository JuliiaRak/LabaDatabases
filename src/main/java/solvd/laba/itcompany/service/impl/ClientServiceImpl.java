package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Client;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.CertificationRepository;
import solvd.laba.itcompany.persistence.ClientRepository;
import solvd.laba.itcompany.persistence.impl.CertificationJdbsRepository;
import solvd.laba.itcompany.persistence.impl.ClientJbdsRepository;
import solvd.laba.itcompany.service.ClientService;

public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl() {
        clientRepository = new ClientJbdsRepository();
    }

    @Override
    public void create(Client client) {
        try {
            clientRepository.create(client);
        } catch (Exception e) {
            throw new ServiceException("Failed to create client", e);
        }
    }

    @Override
    public Client findById(Long clientId) {
        try {
            return clientRepository.findById(clientId);
        } catch (Exception e) {
            throw new ServiceException("Failed to find client by id" + clientId, e);
        }
    }
}

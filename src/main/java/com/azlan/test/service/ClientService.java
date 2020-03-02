package com.azlan.test.service;

import com.azlan.test.model.Client;

import java.util.List;

public interface ClientService {
    Client getClient(Long id);

    List<Client> getClients();

    void createClient(Client client);

    List <Client> findByFirstName(String firstName);
}

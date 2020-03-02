package com.azlan.test.service;

import com.azlan.test.mapper.ClientMapper;
import com.azlan.test.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Client getClient(final Long id) {
        return clientMapper.selectOne(id);
    }

    @Override
    public List<Client> getClients() {
        return clientMapper.findAll();
    }

    @Override
    public void createClient(Client client) {
        clientMapper.insertClient(client);
    }

    @Override
    public List<Client> findByFirstName(String firstName) {
        return clientMapper.findByFirstName(firstName);
    }
}

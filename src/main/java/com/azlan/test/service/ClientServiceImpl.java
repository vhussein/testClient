package com.azlan.test.service;

import com.azlan.test.exception.CustomExceptionHandler;
import com.azlan.test.mapper.ClientMapper;
import com.azlan.test.model.Account;
import com.azlan.test.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Value("#{environment['ACCOUNT_SERVICE_ENDPOINT'] ?: 'localhost:8080'}")
    private String accServiceEndpoint;

    @Value("#{environment['PROTOCOL'] ?: 'http'}")
    private String protocol;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public Client getClient(final Long id) {

        Client client = clientMapper.selectOne(id);
        if(client == null)
            return null;

        client.setAccounts(getAccounts(id));
        return client;
    }

    @Override
    public List<Client> getClients() {

        List<Client> clientList = clientMapper.findAll();

        for (Client client: clientList) {
            client.setAccounts(getAccounts(client.getId()));
        }
        return clientList;
    }

    @Override
    public void createClient(Client client) throws CustomExceptionHandler {

        Long clientId;

        try {
            clientMapper.insertClient(client);
            clientId = client.getId();
        }
        catch (Exception ex){
            log.error("Exception:" + ex.toString());
            throw new CustomExceptionHandler("Error creating client: " + ex.getMessage());
        }

        for (Account acc: client.getAccounts()) {
            acc.setClientId(clientId);
            createAccount(acc);
        }
    }

    @Override
    public List<Client> findByFirstName(String firstName) {
        List<Client> clientList = clientMapper.findByFirstName(firstName);
        for (Client client: clientList) {
            client.setAccounts(getAccounts(client.getId()));
        }
        return clientList;
    }

    private List<Account> getAccounts(long clientId){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Account[]> resp = restTemplate.getForEntity(protocol + "://" + accServiceEndpoint + "/accounts/" + clientId, Account[].class);
        Account[] accounts = resp.getBody();
        return Arrays.asList(Objects.requireNonNull(accounts));
    }

    private void createAccount(Account account){
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(protocol + "://" + accServiceEndpoint + "/accounts/addAccount", account, ResponseEntity.class);
    }
}

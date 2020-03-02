package com.azlan.test.controller;

import com.azlan.test.model.Client;
import com.azlan.test.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping()
    public List<Client> getClients() {
        log.debug("Get all client list");
        return clientService.getClients();
    }

    @GetMapping("/getClientByFirstName")
    public List<Client> getClientByFirstName(@RequestBody Client client) {
        log.debug("Get client by first name");
        return clientService.findByFirstName(client.getFirstName());
    }

    @GetMapping(value = "/{id}")
    public Client getClient(@PathVariable("id") final Long id) {
        log.debug("Get client by ID = " + id);
        return clientService.getClient(id);
    }

    @PostMapping()
    public ResponseEntity createClient(@RequestBody  Client client) {
        log.debug("Creating client");
        clientService.createClient(client);
        return new ResponseEntity(HttpStatus.OK);

    }
}

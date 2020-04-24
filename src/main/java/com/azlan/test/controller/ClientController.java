package com.azlan.test.controller;

import com.azlan.test.exception.ResourceNotFoundException;
import com.azlan.test.model.Client;
import com.azlan.test.model.ResponseModel;
import com.azlan.test.service.ClientService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/clients") //API versioning will be handled by AWS GW
@Api(value="Client Management System")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "View a list of clients", response = List.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getClients() {
        log.debug("Get all client list");
        return clientService.getClients();
    }

    @ApiOperation(value = "Get a client by First Name")
    @GetMapping(value = "/getClientByFirstName", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getClientByFirstName(@RequestParam("name") String name) throws ResourceNotFoundException {
        log.debug("Get client by first name");
        List<Client> clientList = clientService.findByFirstName(name);
        if(clientList.isEmpty())
            throw new ResourceNotFoundException("Unable to find client with first name: " + name);
        return ResponseEntity.ok().body(clientList);
    }

    @ApiOperation(value = "Get a client by Id")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(
            @ApiParam(value = "Client id from which client object will retrieve", required = true)
            @PathVariable("id") final Long id) throws ResourceNotFoundException {
        log.debug("Get client by ID = " + id);
        Client client = clientService.getClient(id);
        if(client == null)
            throw new ResourceNotFoundException("Client ID " + id + " is not found");
        return ResponseEntity.ok().body(client);
    }

    @ApiOperation(value = "Create a client")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> createClient(@RequestBody  Client client) {

        try{
            log.debug("Creating client");
            clientService.createClient(client);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseModel(new Date(), "Client " + client.getFirstName() + " " + client.getLastName() + " created", ""));
        }
        catch (Exception ex){
            log.error("Error: " + ex.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseModel(new Date(), "Error occured", ""));
        }
    }

    @GetMapping(value = "/healthCheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> healthCheck(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseModel(new Date(), "I'm alive", ""));
    }

}

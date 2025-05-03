package com.example.backend_quiz.controllers;

import com.example.backend_quiz.models.Client;
import com.example.backend_quiz.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable int id) {
        Client client = clientService.getClientById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("new")
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.createClient(client);
        return ResponseEntity.status(201).body(createdClient);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable int id, @RequestBody Client updatedClient) {
        Client client = clientService.updateClient(id, updatedClient);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
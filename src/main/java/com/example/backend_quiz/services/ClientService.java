package com.example.backend_quiz.services;

import com.example.backend_quiz.models.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    
    private List<Client> clients = new ArrayList<>();

    public ClientService() {
        clients.add(new Client(1, "John", "Doe", "123456789"));
        clients.add(new Client(2, "Jane", "Smith", "987654321"));
    }

    public List<Client> getAllClients() {
        return clients;
    }

    public Client getClientById(int id) {
        return clients.stream()
                .filter(client -> client.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Client createClient(Client client) {
        client.setId(clients.size() + 1);
        clients.add(client);
        return client;
    }

    public Client updateClient(int id, Client updatedClient) {
        Client client = getClientById(id);
        if (client != null) {
            client.setFirstName(updatedClient.getFirstName());
            client.setLastName(updatedClient.getLastName());
            client.setMobile(updatedClient.getMobile());
            return client;
        }
        return null;
    }
}
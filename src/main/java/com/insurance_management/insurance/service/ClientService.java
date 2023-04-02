package com.insurance_management.insurance.service;

import com.insurance_management.insurance.exceptions.ClientNotFoundException;
import com.insurance_management.insurance.model.Client;
import com.insurance_management.insurance.repo.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            return client.get();
        } else {
            throw new ClientNotFoundException("Client with ID " + id + " not found.");
        }
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client client) {
        Optional<Client> existingClient = clientRepository.findById(id);
        if(existingClient.isPresent()) {
            client.setId(id);
            return clientRepository.save(client);
        } else {
            throw new ClientNotFoundException("Client with ID " + id + " not found.");
        }
    }

    public void deleteClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isPresent()) {
            clientRepository.deleteById(id);
        } else {
            throw new ClientNotFoundException("Client with ID " + id + " not found.");
        }
    }
}
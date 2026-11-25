package com.tecnm.merida.market_backendjavaj.domain.service;

import com.tecnm.merida.market_backendjavaj.domain.Client;
import com.tecnm.merida.market_backendjavaj.domain.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(String clientId) {
        return clientRepository.getClient(clientId);
    }

    public Optional<Client> getByEmail(String email) {
        return clientRepository.getByEmail(email);
    }

    public Client save(Client client) {
        if (client.getPassword() != null && !client.getPassword().isBlank() && !client.getPassword().startsWith("$2a$")) {
            client.setPassword(passwordEncoder.encode(client.getPassword()));
        }
        return clientRepository.save(client);
    }

    public Optional<Client> update(String clientId, Client client) {
        return getClient(clientId).map(existing -> {
            client.setClientId(clientId);
            if (client.getPassword() == null || client.getPassword().isBlank()) {
                client.setPassword(existing.getPassword());
            } else if (!client.getPassword().startsWith("$2a$")) {
                client.setPassword(passwordEncoder.encode(client.getPassword()));
            }
            return clientRepository.save(client);
        });
    }

    public boolean delete(String clientId) {
        return getClient(clientId).map(client -> {
            clientRepository.delete(clientId);
            return true;
        }).orElse(false);
    }
}
package br.com.unisales.microservicocliente.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unisales.microservicocliente.repository.ClientRepository;
import br.com.unisales.microservicocliente.table.Client;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public Client findClientById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }
    
}

package br.com.unisales.microservicocliente.service;

import java.util.Optional;

import br.com.unisales.microservicocliente.table.Client;

public interface ClientService {
    Client saveClient(Client client);
    Optional<Client> findClientByEmail(String email);
    Client findClientById(Integer id);
    Optional<Client> findByEmailAndSenha(String email, String senha);

}

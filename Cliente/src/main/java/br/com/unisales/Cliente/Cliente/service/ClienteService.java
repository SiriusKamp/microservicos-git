package br.com.unisales.Cliente.Cliente.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.unisales.Cliente.Cliente.model.Cliente;
import br.com.unisales.Cliente.Cliente.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente update(Long id, Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            return null; // ou lançar uma exceção
        }
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    public boolean delete(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

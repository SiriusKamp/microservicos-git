package br.com.unisales.Cliente.Cliente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unisales.Cliente.Cliente.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}

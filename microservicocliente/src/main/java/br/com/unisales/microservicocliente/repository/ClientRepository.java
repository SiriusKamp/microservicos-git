package br.com.unisales.microservicocliente.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisales.microservicocliente.table.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByEmailAndSenha(String email, String senha);

    Optional<Client> findByEmail(String email); 

    List<Client> findByNomeIgnoreCaseContainingOrderByNomeAsc(String nome);

    List<Client> findByNomeIgnoreCaseContainingAndAtivoOrderByNomeAsc(String nome, Integer ativo);

    List<Client> findByAtivoOrderByNomeAsc(Integer ativo);

    List<Client> findAllByOrderByNomeAsc();
    
}

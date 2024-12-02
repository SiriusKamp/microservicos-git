package br.com.unisales.Login.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.unisales.Login.model.ClienteProduto;

public interface ClienteProdutoRepository extends JpaRepository<ClienteProduto, Long> {
}

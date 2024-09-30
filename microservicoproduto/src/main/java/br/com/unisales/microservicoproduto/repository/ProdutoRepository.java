package br.com.unisales.microservicoproduto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisales.microservicoproduto.table.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByTituloIgnoreCaseContainingOrderByTitulo(String titulo);
}

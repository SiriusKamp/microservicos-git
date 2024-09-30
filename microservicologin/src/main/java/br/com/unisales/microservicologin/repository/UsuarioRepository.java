package br.com.unisales.microservicologin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unisales.microservicologin.table.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmailAndSenha(String email, String senha);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByNomeIgnoreCaseContainingOrderByNomeAsc(String nome);

    List<Usuario> findByNomeIgnoreCaseContainingAndAtivoOrderByNomeAsc(String nome, Integer ativo);

    List<Usuario> findByAtivoOrderByNomeAsc(Integer ativo);

    List<Usuario> findAllByOrderByNomeAsc();
}

package br.com.unisales.microservicologin.table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @apiNote Classe responsável por mapear a tabela usuario do banco de dados
 * @author Vito Rodrigues Franzosi
 * @Data Criação 05.09.2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;
    @Column(name = "nome", nullable = false, length = 150)
    private String nome;
    @Column(name = "sexo", nullable = false, length = 1)
    private String sexo;
    @Column(name = "email", nullable = false, length = 150)
    private String email;
    @Column(name = "senha", nullable = false, length = 10)
    private String senha;
    @Column(name = "grupo", nullable = false, length = 15)
    private String grupo;
    @Column(name = "ativo", nullable = false)
    private Integer ativo;

}

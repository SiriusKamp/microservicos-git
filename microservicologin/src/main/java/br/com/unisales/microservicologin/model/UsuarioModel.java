package br.com.unisales.microservicologin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel {
    private Integer id;
    private String nome;
    private String sexo;
    private String email;
    private String grupo;
    private Integer ativo;
    private String token;
}

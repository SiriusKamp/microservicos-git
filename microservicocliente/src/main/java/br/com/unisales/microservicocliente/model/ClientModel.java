package br.com.unisales.microservicocliente.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel {
    private Integer id;
    private String nome;
    private String sexo;
    private String email;
    private String grupo;
    private Integer ativo;
}

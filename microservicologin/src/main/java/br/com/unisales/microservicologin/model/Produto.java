package br.com.unisales.microservicologin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    private Integer id;
    private String titulo;
    private String descricao;
    private int ativo;
}

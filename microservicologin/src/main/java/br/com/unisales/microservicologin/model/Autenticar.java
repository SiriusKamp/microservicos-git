package br.com.unisales.microservicologin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autenticar {
    private String email;
    private String senha;
}

package br.com.unisales.Login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "http://localhost:8090") // Permite requisições CORS do frontend
public class ProdutoController {

    @GetMapping("/produtos") // Rota para listar produtos
    public String listarProdutos() {
        return "listar-produtos"; // Nome da página que lista os produtos
    }

    @GetMapping("/cadastro-produto") // Rota para o cadastro de produtos
    public String cadastrarProduto() {
        return "cadastro-produto"; // Nome da página de cadastro de produtos
    }
}

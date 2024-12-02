package br.com.unisales.Login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.unisales.Login.model.ClienteProduto;
import br.com.unisales.Login.repository.ClienteProdutoRepository;

@Controller
@CrossOrigin(origins = "http://localhost:8090")
@RequestMapping("/cliente-produto")
public class ClienteProdutoController {

    @Autowired
    private ClienteProdutoRepository clienteProdutoRepository;

    @GetMapping
    public List<ClienteProduto> listarTodos() {
        return clienteProdutoRepository.findAll();
    }
    @GetMapping("/linkar") 
    public String listarProdutos() {
        return "Linkar"; 
    }

    @PostMapping
    public ClienteProduto cadastrar(@RequestBody ClienteProduto clienteProduto) {
        return clienteProdutoRepository.save(clienteProduto);
    }
}

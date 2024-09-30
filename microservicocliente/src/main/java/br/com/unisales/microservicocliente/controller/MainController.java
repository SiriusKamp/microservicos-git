package br.com.unisales.microservicocliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.unisales.microservicocliente.repository.ClientRepository;
import br.com.unisales.microservicocliente.service.ClientService;
import br.com.unisales.microservicocliente.table.Client;
import br.com.unisales.microservicocliente.model.ClientModel;   

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "./html/login.html";
    }

    @GetMapping("/listarUsuario")
    public String usuarioListar() {
        return "./html/cliente/ClientListar.html";
    }

    @GetMapping("/cadastrarUsuario")
    public String usuarioCadastrar() {
        return "./html/cliente/Cadastro.html";
    }
 @Autowired
    private ClientRepository repo;


    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("senha") String senha, ClientModel model) {
        Client client = repo.findByEmailAndSenha(email, senha);
        if (client != null) {
            model.addAttribute("client", client);
            return "./html/cliente/ClientListar.html";  // Redireciona para a página que lista os dados do cliente logado
        } else {
            model.addAttribute("error", "Email ou senha inválidos.");
            return "./html/login.html";
        }
    }

}


package br.com.unisales.Login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "http://localhost:8090")
public class ClienteController {

    @GetMapping("/clientes")
    public String listarClientes() {
        return "listar-clientes";
    }

    @GetMapping("/cadastro")
    public String cadastrarCliente() {
        return "cadastro-cliente";
    }
}

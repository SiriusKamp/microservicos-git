package br.com.unisales.microservicologin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "./login.html";
    }

    @GetMapping("/listarUsuario")
    public String usuarioListar() {
        return "./html/usuario/usuario_listar.html";
    }

    @GetMapping("/cadastrarUsuario")
    public String usuarioCadastrar() {
        return "./html/usuario/usuario_form.html";
    }
}

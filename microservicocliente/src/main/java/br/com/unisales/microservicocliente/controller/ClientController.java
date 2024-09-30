package br.com.unisales.microservicocliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.unisales.microservicocliente.service.ClientService;
import br.com.unisales.microservicocliente.table.Client;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping("/edit")
    public String editProfile(@RequestParam("id") Integer id, Model model) {
        Client client = clientService.findClientById(id);
        if (client != null) {
            model.addAttribute("client", client);
            return "./html/usuario/ClientProfileEdit.html";  // Página de edição de perfil
        }
        return "redirect:/";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute Client updatedClient) {
        Client existingClient = clientService.findClientById(updatedClient.getId());

        if (existingClient != null) {
            // Atualiza apenas os campos permitidos
            existingClient.setNome(updatedClient.getNome());
            existingClient.setSexo(updatedClient.getSexo());
            existingClient.setSenha(updatedClient.getSenha());

            clientService.saveClient(existingClient);
        }
        return "redirect:/client/profile?id=" + updatedClient.getId();
    }

    // Método para registrar um novo cliente com o grupo 'cliente' por padrão
    @PostMapping("/register")
    public String registerClient(@ModelAttribute Client client) {
        client.setGrupo("cliente");
        client.setAtivo(1);  // Exemplo de cliente ativo por padrão
        clientService.saveClient(client);
        return "redirect:/client/profile";
    }

    // Método para exibir os dados do cliente logado (simulação de login com ID)
    @GetMapping("/profile")
    public String showProfile(@RequestParam("id") Integer id, Model model) {
        Client client = clientService.findClientById(id);
        if (client != null) {
            model.addAttribute("client", client);
            return "./html/cliente/ClientProfile.html";
        }
        return "redirect:/";
    }

    // Método para atualizar os dados editáveis do cliente logado
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute Client updatedClient) {
        Client existingClient = clientService.findClientById(updatedClient.getId());

        if (existingClient != null) {
            // Campos que podem ser atualizados pelo cliente
            existingClient.setNome(updatedClient.getNome());
            existingClient.setSexo(updatedClient.getSexo());
            existingClient.setSenha(updatedClient.getSenha());

            clientService.saveClient(existingClient);
        }
        return "redirect:/client/profile?id=" + updatedClient.getId();
    }
}

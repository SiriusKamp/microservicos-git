package br.com.unisales.microservicologin.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unisales.microservicologin.model.Autenticar;
import br.com.unisales.microservicologin.model.UsuarioModel;
import br.com.unisales.microservicologin.service.UsuarioService;
import br.com.unisales.microservicologin.table.Usuario;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @apiNote Classe responsável por receber as requisições do navegador e
 *          respondê-las nas urls do objeto usuário ]
 * @author Vito Rodrigues Franzosi
 * @Data Criação 05.09.2024
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService servico;

    /**
     * @apiNote Método responsável por autenticar o usuário no sistema
     * @param Autenticar         autenticar
     * @param HttpServletRequest request
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(Autenticar autenticar, HttpServletRequest request) {
        UsuarioModel modelo = this.servico.login(autenticar);
        JSONObject json = new JSONObject();
        if (modelo.getId() != null) {
            request.getSession().setAttribute("usuario", modelo);
            json.put("tipo", "sucesso");
            json.put("email", modelo.getEmail());
            json.put("nome", modelo.getNome());
            json.put("grupo", modelo.getGrupo());
            json.put("sexo", modelo.getSexo());
        } else {
            json.put("tipo", "erro");
            json.put("mensagem", "O usuário e/ou senha incorretos!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(json.toString());
    }

    /**
     * @apiNote Método responsável por salvar os dados do usuário
     * @param Usuario            usuario
     * @param HttpServletRequest request
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    @PostMapping
    public ResponseEntity<String> salvar(Usuario usuario, HttpServletRequest request) {
        UsuarioModel modelo = (UsuarioModel) request.getSession().getAttribute("usuario");
        if (modelo != null) {
            if (modelo.getGrupo().equals("Administrador"))
                return ResponseEntity.status(HttpStatus.OK).body(this.servico.salvar(usuario));
            else {
                usuario.setId(modelo.getId());
                return ResponseEntity.status(HttpStatus.OK).body(this.servico.salvar(usuario));
            }
        } else {
            JSONObject json = new JSONObject();
            json.put("tipo", "erro");
            json.put("mensagem", "O usuário não está logado no sistema!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json.toString());
        }
    }

    /**
     * @apiNote Método responsável por listar os usuários cadastrados
     * @param String             nome
     * @param String             email
     * @param Integer            ativo
     * @param HttpServletRequest request
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    @PostMapping("/listar")
    public ResponseEntity<String> listar(@RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "ativo", required = true, defaultValue = "1") Integer ativo,
            HttpServletRequest request) {
        UsuarioModel modelo = (UsuarioModel) request.getSession().getAttribute("usuario");
        if (modelo != null) {
            if (modelo.getGrupo().equals("Administrador"))
                return ResponseEntity.status(HttpStatus.OK).body(this.servico.listar(nome, email, ativo));
            else
                return ResponseEntity.status(HttpStatus.OK).body(this.servico.buscarPorId(modelo.getId()));
        } else {
            JSONObject json = new JSONObject();
            json.put("tipo", "erro");
            json.put("mensagem", "O usuário não está logado no sistema!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json.toString());
        }
    }

    /**
     * @apiNote Método responsável por buscar o usuário pelo seu código
     * @param Integer            id
     * @param HttpServletRequest request
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> buscarPorId(@PathVariable(value = "id", required = true) Integer id,
            HttpServletRequest request) {
        UsuarioModel modelo = (UsuarioModel) request.getSession().getAttribute("usuario");
        if (modelo != null) {
            if (modelo.getGrupo().equals("Administrador"))
                return ResponseEntity.status(HttpStatus.OK).body(this.servico.buscarPorId(id));
            else
                return ResponseEntity.status(HttpStatus.OK).body(this.servico.buscarPorId(modelo.getId()));
        } else {
            JSONObject json = new JSONObject();
            json.put("tipo", "erro");
            json.put("mensagem", "O usuário não está logado no sistema!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json.toString());
        }
    }

    /**
     * @apiNote Método responsável por excluir o usuário do sistema
     * @param Integer            id
     * @param HttpServletRequest request
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable(value = "id", required = true) Integer id,
            HttpServletRequest request) {
        UsuarioModel modelo = (UsuarioModel) request.getSession().getAttribute("usuario");
        JSONObject json = new JSONObject();
        if (modelo != null) {
            if (modelo.getGrupo().equals("Administrador"))
                if (modelo.getId().equals(id)) {
                    json.put("tipo", "id_igual");
                    json.put("mensagem", "Você não pode excluir o seu usuário!");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json.toString());
                } else
                    return ResponseEntity.status(HttpStatus.OK).body(this.servico.excluir(id));
            else {
                json.put("tipo", "permissao");
                json.put("mensagem", "Você não possui permissão para excluir usuário!");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json.toString());
            }
        } else {
            json.put("tipo", "erro");
            json.put("mensagem", "O usuário não está logado no sistema!");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json.toString());
        }
    }
}

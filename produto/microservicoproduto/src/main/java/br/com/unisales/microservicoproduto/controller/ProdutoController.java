package br.com.unisales.microservicoproduto.controller;

import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unisales.microservicoproduto.service.ProdutoService;
import br.com.unisales.microservicoproduto.table.Produto;
import br.com.unisales.microservicoproduto.util.Token;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService servico;

    @PostMapping("/listar")
    public ResponseEntity<String> listar(@RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "token", required = true) String token) {
        if (new Token().compararToken(UUID.fromString(token)))
            return ResponseEntity.status(HttpStatus.OK).body(this.servico.listar(titulo));
        else {
            JSONObject json = new JSONObject();
            json.put("erro", "O token não foi reconhecido por esta aplicação!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json.toString());
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> buscarPorId(@PathVariable("id") Integer id,
            @RequestParam(value = "token", required = true) String token) {
        // new Token().generationToken();
        if (new Token().compararToken(UUID.fromString(token)))
            return ResponseEntity.status(HttpStatus.OK).body(this.servico.buscarPorId(id));
        else {
            JSONObject json = new JSONObject();
            json.put("erro", "O token não foi reconhecido por esta aplicação!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json.toString());
        }
    }

    @PostMapping("/salvar")
    public ResponseEntity<String> salvar(@RequestBody Produto produto, @RequestParam("token") String token) {
        if (new Token().compararToken(UUID.fromString(token)))
            return ResponseEntity.status(HttpStatus.OK).body(this.servico.salvar(produto));
        else {
            JSONObject json = new JSONObject();
            json.put("erro", "O token não foi reconhecido por esta aplicação!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json.toString());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> alterar(@PathVariable("id") Integer id, @RequestBody Produto produto,
            @RequestParam("token") String token) {
        if (new Token().compararToken(UUID.fromString(token)))
            return ResponseEntity.status(HttpStatus.OK).body(this.servico.salvar(produto));
        else {
            JSONObject json = new JSONObject();
            json.put("erro", "O token não foi reconhecido por esta aplicação!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json.toString());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> alterar(@PathVariable("id") Integer id, @RequestParam("token") String token) {
        if (new Token().compararToken(UUID.fromString(token)))
            return ResponseEntity.status(HttpStatus.OK).body(this.servico.excluir(id));
        else {
            JSONObject json = new JSONObject();
            json.put("erro", "O token não foi reconhecido por esta aplicação!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(json.toString());
        }
    }

}

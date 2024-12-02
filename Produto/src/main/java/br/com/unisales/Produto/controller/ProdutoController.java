package br.com.unisales.Produto.controller;

import br.com.unisales.Produto.model.Produto;
import br.com.unisales.Produto.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8090")
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired  
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> findAll() {
        return produtoService.findAll();
    }

    @PostMapping
    public Produto create(@RequestBody Produto produto) {
        return produtoService.save(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Optional<Produto> produto = produtoService.findById(id);
        return produto.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody Produto produto) {
        Produto updatedProduto = produtoService.update(id, produto);
        return updatedProduto != null ? ResponseEntity.ok(updatedProduto)
                                       : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = produtoService.delete(id);
        return deleted ? ResponseEntity.noContent().build()
                       : ResponseEntity.notFound().build();
    }
}

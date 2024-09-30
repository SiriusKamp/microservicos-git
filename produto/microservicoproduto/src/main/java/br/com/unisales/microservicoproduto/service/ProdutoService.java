package br.com.unisales.microservicoproduto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.unisales.microservicoproduto.repository.ProdutoRepository;
import br.com.unisales.microservicoproduto.table.Produto;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repo;

    public String listar(String titulo) {
        try {
            List<Produto> lista = new ArrayList<Produto>();
            if ((titulo != null) && ((titulo.trim()).length() > 0))
                lista = this.repo.findByTituloIgnoreCaseContainingOrderByTitulo(titulo);
            else
                lista = this.repo.findAll();
            return new ObjectMapper().writeValueAsString(lista);
        } catch (Exception e) {
            System.err.println("Erro no método listar() da classe ProdutoService: " + e.getMessage());
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("erro", "Não foi possível listar os produtos!");
            return json.toString();
        }
    }

    public String buscarPorId(Integer id) {
        try {
            Optional<Produto> produto = this.repo.findById(id);
            if (produto.isPresent())
                return new ObjectMapper().writeValueAsString(produto.get());
            JSONObject json = new JSONObject();
            json.put("vazio", "Não foi possível encontrar o produto pelo código passado!");
            return json.toString();
        } catch (Exception e) {
            System.err.println("Erro no método buscarPorId() da classe ProdutoService: " + e.getMessage());
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("erro", "Erro na busca o produto pelo código passado!");
            return json.toString();
        }
    }

    public String salvar(Produto produto) {
        try {
            if (produto.getId() != null) {
                Optional<Produto> produtoOptional = this.repo.findById(produto.getId());
                if (produtoOptional.isPresent())
                    return new ObjectMapper().writeValueAsString(this.repo.save(produto));
                else {
                    JSONObject json = new JSONObject();
                    json.put("vazio", "O produto não foi encontrado para ser alterado!");
                    return json.toString();
                }
            }
            return new ObjectMapper().writeValueAsString(this.repo.save(produto));
        } catch (Exception e) {
            System.err.println("Erro no método salvar() da classe ProdutoService: " + e.getMessage());
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("erro", "Erro foi possível salvar os dados do produto!");
            return json.toString();
        }
    }

    public String excluir(Integer id) {
        try {
            JSONObject json = new JSONObject();
            Optional<Produto> produto = this.repo.findById(id);
            if (produto.isPresent()) {
                this.repo.delete(produto.get());
                json.put("sucesso", "Os dados do produto foram excluídos com sucesso!");
            } else
                json.put("vazio", "Os dados do produto NÃO foram excluídos do sistema!");
            return json.toString();
        } catch (Exception e) {
            System.err.println("Erro no método excluir() da classe ProdutoService: " + e.getMessage());
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("erro", "Erro ao tentar excluir os dados do produto!");
            return json.toString();
        }
    }
}

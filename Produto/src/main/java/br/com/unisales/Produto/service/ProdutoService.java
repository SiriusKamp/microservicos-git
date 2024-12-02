package br.com.unisales.Produto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.unisales.Produto.model.Produto;
import br.com.unisales.Produto.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto update(Long id, Produto produto) {
        if (produtoRepository.existsById(id)) {
            produto.setId(id);
            return produtoRepository.save(produto);
        }
        return null;
    }

    public boolean delete(Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public String buscarNomeProduto(Integer codigoProduto) {
        // URL do microserviço Produto (ajuste conforme necessário)
        String url = "http://microservico-produto/api/produtos/" + codigoProduto;

        RestTemplate restTemplate = new RestTemplate();
        try {
            // Chamada ao microserviço para buscar o nome do produto
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Produto não encontrado";
        }
    }

}

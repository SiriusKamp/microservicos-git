package br.com.unisales.microservicologin.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import br.com.unisales.microservicologin.model.Produto;

/**
 * @apiNote Classe que conecta ao micro serviço produto e realiza o CRUD
 * @author Vito Rodrigues Franzosi
 * @Data Criação 05.09.2024
 */
@Service
public class ProdutoService {
    /**
     * @apiNote Método responsável por buscar no micro serviço produto todos os
     *          produtos cadastrados
     * @param String titulo
     * @param String token
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    public ResponseEntity<String> listar(String titulo, String token) {
        // Criando a link da requisição (URL base)
        String url = "http://localhost:8095/produto/listar";
        // Criando o cabeçalho da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // Criando os parametros para serem passados na requisição
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("titulo", titulo);
        map.add("token", token);
        // Encapsulando os cabeçalhos HTTP e o corpo da requisição
        HttpEntity<MultiValueMap<String, String>> resposta = new HttpEntity<MultiValueMap<String, String>>(map,
                headers);
        // Criando o objeto do Spring Boot que consome os serviços RESTful em aplicações
        // Java
        RestTemplate restTemplate = new RestTemplate();
        // Enviando uma requisição HTTP POST para uma API REST e capturar a resposta
        // completa em uma entidade ResponseEntity.
        return restTemplate.postForEntity(url, resposta, String.class);
    }

    /**
     * @apiNote Método responsável por buscar no micro serviço produto o produto
     *          pelo seu id
     * @param Integer id
     * @param String  token
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    public ResponseEntity<String> buscarPorId(Integer id, String token) {
        // Criando a link da requisição (URL base)
        String url = "http://localhost:8095/produto/" + id;
        // Criando o cabeçalho da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // Criando os parametros para serem passados na requisição
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("token", token);
        // Encapsulando os cabeçalhos HTTP e o corpo da requisição
        HttpEntity<MultiValueMap<String, String>> resposta = new HttpEntity<MultiValueMap<String, String>>(map,
                headers);
        // Criando o objeto do Spring Boot que consome os serviços RESTful em aplicações
        // Java
        RestTemplate restTemplate = new RestTemplate();
        // Enviando uma requisição HTTP POST para uma API REST e capturar a resposta
        // completa em uma entidade ResponseEntity.
        return restTemplate.getForEntity(url, String.class, resposta);
    }

    /**
     * @apiNote Método responsável por enviar os dados para o micro serviço produto
     *          para salvar os dados do produto
     * @param Produto produto
     * @param String  token
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    public ResponseEntity<String> salvar(Produto produto, String token) {
        // Criando a link da requisição (URL base)
        String url = "http://localhost:8095/";
        // Criando o cabeçalho da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // Criando os parametros para serem passados na requisição
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("id", produto.getId().toString());
        map.add("titulo", produto.getTitulo());
        map.add("descricao", produto.getDescricao());
        map.add("ativo", "" + produto.getAtivo());
        map.add("token", token);
        // Encapsulando os cabeçalhos HTTP e o corpo da requisição
        HttpEntity<MultiValueMap<String, String>> resposta = new HttpEntity<MultiValueMap<String, String>>(map,
                headers);
        // Criando o objeto do Spring Boot que consome os serviços RESTful em aplicações
        // Java
        RestTemplate restTemplate = new RestTemplate();
        // Enviando uma requisição HTTP POST para uma API REST e capturar a resposta
        // completa em uma entidade ResponseEntity.
        return restTemplate.postForEntity(url, resposta, String.class);
    }

    /**
     * @apiNote Método responsável por enviar para o micro serviço produto o id do
     *          produto para ser excluído
     * @param Integer id
     * @param String  token
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    public ResponseEntity<String> excluir(Integer id, String token) {
        // Criando a link da requisição (URL base)
        String url = "http://localhost:8095/produto/" + id;
        // Criando o cabeçalho da requisição
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // Criando os parametros para serem passados na requisição
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("token", token);
        // Encapsulando os cabeçalhos HTTP e o corpo da requisição
        HttpEntity<MultiValueMap<String, String>> resposta = new HttpEntity<MultiValueMap<String, String>>(map,
                headers);
        // Criando o objeto do Spring Boot que consome os serviços RESTful em aplicações
        // Java
        RestTemplate restTemplate = new RestTemplate();
        // Enviando uma requisição HTTP POST para uma API REST e capturar a resposta
        // completa em uma entidade ResponseEntity.
        return restTemplate.exchange(url, HttpMethod.DELETE, resposta, String.class);
    }
}

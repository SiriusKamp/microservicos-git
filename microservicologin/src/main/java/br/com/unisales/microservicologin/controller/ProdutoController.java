package br.com.unisales.microservicologin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.unisales.microservicologin.model.Produto;
import br.com.unisales.microservicologin.service.ProdutoService;
import br.com.unisales.microservicologin.util.Token;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @apiNote Classes responsável por receber e responder as requisições
 *          realizadas para o produto
 * @author Vito Rodrigues Franzosi
 * @Data Criação 05.09.2024
 */
@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService servico;

    /**
     * @apiNote Método responsável por receber a requisição do navegador para a
     *          listagem de produtos e retornar os produtos cadastrados para o
     *          navegador
     * @param String             titulo
     * @param HttpServletRequest request
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    @GetMapping
    public ResponseEntity<String> listar(@RequestParam(value = "titulo", required = false) String titulo,
            HttpServletRequest request) {
        return this.servico.listar(titulo, new Token().generationToken().toString());
    }

    /**
     * @apiNote Método responsável por receber a requisição do navegoador para
     *          buscar o produto pelo seu id e retornar os dados deste produto
     * @param Integer            id
     * @param HttpServletRequest request
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    @GetMapping("/{id}")
    public ResponseEntity<String> buscarPorId(@PathVariable("id") Integer id, HttpServletRequest request) {
        return this.servico.buscarPorId(id, new Token().generationToken().toString());
    }

    /**
     * @apiNote Método resposável por receber do navegador os dados do produto
     *          e retornar para o navegador se o novo produto foi inserido no
     *          sistema
     * @param Produto            produto
     * @param String             token
     * @param HttpServletRequest request
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    @PostMapping
    public ResponseEntity<String> salvar(@RequestBody Produto produto, @RequestParam("token") String token,
            HttpServletRequest request) {
        return this.servico.salvar(produto, token);
    }

    /**
     * @apiNote Método responsável por receber do navegador os dados do produto para
     *          serem alterados e retornar para o navegador os dados alterados do
     *          produto
     * @param Integer            id
     * @param Produto            produto
     * @param HttpServletRequest request
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> alterar(@PathVariable("id") Integer id, @RequestBody Produto produto,
            HttpServletRequest request) {
        return this.servico.salvar(produto, new Token().generationToken().toString());
    }

    /**
     * @apiNote Método responsável por receber do navegador o código do produto que
     *          deve ser excluído do sistema e retornar para o navegador se os dados
     *          foram excluídos
     * @param Integer            id
     * @param HttpServletRequest request
     * @return ResponseEntity<String>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable("id") Integer id, HttpServletRequest request) {
        return this.servico.excluir(id, new Token().generationToken().toString());
    }
}

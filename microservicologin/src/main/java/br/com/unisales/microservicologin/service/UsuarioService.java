package br.com.unisales.microservicologin.service;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.unisales.microservicologin.model.Autenticar;
import br.com.unisales.microservicologin.model.UsuarioModel;
import br.com.unisales.microservicologin.repository.UsuarioRepository;
import br.com.unisales.microservicologin.table.Usuario;
import br.com.unisales.microservicologin.util.Token;

/**
 * @apiNote Classe responsável por realizar os serviços de CRUD com os dados do
 *          usuário
 * @author Vito Rodrigues Franzosi
 * @Data Criação 05.09.2024
 */
@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repo;

    /**
     * @apiNote Método responsável por realizar o login do usuário no sistema
     * @param Autenticar autenticar
     * @return UsuarioModel
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    public UsuarioModel login(Autenticar autenticar) {
        UsuarioModel modelo = new UsuarioModel();
        // this.repo.save(new Usuario(null, "Vito Franzosi", "M", autenticar.getEmail(),
        // autenticar.getSenha(), "Administrador", 1));
        Optional<Usuario> usuario = this.repo.findByEmailAndSenha(autenticar.getEmail(), autenticar.getSenha());
        if (usuario.isPresent()) {
            modelo = this.converteUsuarioToModel(usuario.get());
            modelo.setToken((new Token().generationToken()).toString());
        }
        return modelo;
    }

    /**
     * @apiNote Método responsável por listar os usuários cadastrados no banco de
     *          dados
     * @param String  nome
     * @param String  email
     * @param Integer ativo
     * @return String (List<UsuarioModel>)
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    public String listar(String nome, String email, Integer ativo) {
        try {
            List<Usuario> lista = new ArrayList<Usuario>();
            if ((email != null) && ((email.trim()).length() > 10)) {
                Optional<Usuario> usuario = this.repo.findByEmail(email);
                if (usuario.isPresent())
                    lista.add(usuario.get());
            } else if ((nome != null) && ((nome.trim()).length() > 3)) {
                if (ativo == 2)
                    lista = this.repo.findByNomeIgnoreCaseContainingOrderByNomeAsc(nome);
                else
                    lista = this.repo.findByNomeIgnoreCaseContainingAndAtivoOrderByNomeAsc(nome, ativo);
            } else if (ativo != 2) {
                lista = this.repo.findByAtivoOrderByNomeAsc(ativo);
            } else
                lista = this.repo.findAllByOrderByNomeAsc();
            return new ObjectMapper().writeValueAsString(this.converteListaUsuarioToListaModel(lista));
        } catch (Exception e) {
            System.err.println("Erro no método listar() da classe UsuarioService: " + e.getMessage());
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("tipo", "erro");
            json.put("mensagem", "Não foi possível listar os usuários!");
            return json.toString();
        }
    }

    /**
     * @apiNote Método responsável por buscar um usuário pelo seu código de
     *          identificação única (id)
     * @param Integer id
     * @return String (UsuarioModel)
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    public String buscarPorId(Integer id) {
        JSONObject json = new JSONObject();
        try {
            Optional<Usuario> usuario = this.repo.findById(id);
            if (usuario.isPresent())
                return new ObjectMapper().writeValueAsString(this.converteUsuarioToModel(usuario.get()));
            json.put("tipo", "vazio");
            json.put("mensagem", "O usuário não foi encontrado!");
            return json.toString();
        } catch (Exception e) {
            System.err.println("Erro no método listar() da classe UsuarioService: " + e.getMessage());
            e.printStackTrace();
            json.put("tipo", "erro");
            json.put("mensagem", "Não foi possível buscar o usuário pelo seu código!");
            return json.toString();
        }
    }

    /**
     * @apiNote Método responsável por salvar os dados do usuário
     * @param UsuarioModel usuarioModel
     * @return UsuarioModel
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    public String salvar(Usuario usuario) {
        try {
            Optional<Usuario> usuarioOptional = this.repo.findById(usuario.getId());
            Usuario user = new Usuario();
            if (usuarioOptional.isPresent()) {
                user.setId(usuario.getId());
                user.setSenha(usuarioOptional.get().getSenha());
            } else
                user.setSenha(usuario.getSenha());
            user.setAtivo(usuario.getAtivo());
            user.setEmail(usuario.getEmail());
            user.setGrupo(usuario.getGrupo());
            user.setNome(usuario.getNome());
            user.setSexo(usuario.getSexo());
            return new ObjectMapper().writeValueAsString(this.converteUsuarioToModel(this.repo.save(user)));
        } catch (Exception e) {
            System.err.println("Erro no método listar() da classe UsuarioService: " + e.getMessage());
            e.printStackTrace();
            JSONObject json = new JSONObject();
            json.put("tipo", "erro");
            json.put("mensagem", "Não foi possível buscar o usuário pelo seu código!");
            return json.toString();
        }
    }

    /**
     * @apiNote Método responsável por alterar a senha do usuário
     * @param Integer id
     * @param String  novaSenha
     * @return UsuarioModel
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    public UsuarioModel alterarSenha(Integer id, String novaSenha) {
        Optional<Usuario> usuario = this.repo.findById(id);
        if (usuario.isPresent()) {
            usuario.get().setSenha(novaSenha);
            return this.converteUsuarioToModel(this.repo.save(usuario.get()));
        }
        return new UsuarioModel();
    }

    /**
     * @apiNote Método responsável por excluir um usuário
     * @param Integer id
     * @return String
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    public String excluir(Integer id) {
        JSONObject json = new JSONObject();
        Optional<Usuario> usuario = this.repo.findById(id);
        if (usuario.isPresent()) {
            this.repo.delete(usuario.get());
            json.put("sucesso", "Os dados do usuário foram excluídos com sucesso!");
        } else
            json.put("erro", "Os dados do usuário não foram excluídos!");
        return json.toString();
    }

    /**
     * @apiNote Método responsável por converter uma listar de usuários para uma
     *          lista de usuários modelos
     * @param List<Usuario> lista
     * @return List<UsuarioModel>
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    private List<UsuarioModel> converteListaUsuarioToListaModel(List<Usuario> lista) {
        List<UsuarioModel> listaModelo = new ArrayList<UsuarioModel>();
        for (Usuario item : lista)
            listaModelo.add(this.converteUsuarioToModel(item));
        return listaModelo;
    }

    /**
     * @apiNote Método responsável por converter um usuário em seu modelo
     * @param Usuario usuario
     * @return UsuarioModel
     * @author Vito Rodrigues Franzosi
     * @Data Criação 05.09.2024
     */
    private UsuarioModel converteUsuarioToModel(Usuario usuario) {
        UsuarioModel modelo = new UsuarioModel();
        modelo.setAtivo(usuario.getAtivo());
        modelo.setEmail(usuario.getEmail());
        modelo.setGrupo(usuario.getGrupo());
        modelo.setId(usuario.getId());
        modelo.setNome(usuario.getNome());
        modelo.setSexo(usuario.getSexo());
        return modelo;
    }
}

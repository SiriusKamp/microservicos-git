package br.com.unisales.Cliente.Cliente.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sexo;
    private Date datanasc;
    private String cpf;
    private String email;
    private String celular;
    private Integer ativo; // 0 ou 1 
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getSexo() {
        return sexo;
    }
    public Date getDatanasc() {
        return datanasc;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public String getCelular() {
        return celular;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public void setDatanasc(Date datanasc) {
        this.datanasc = datanasc;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getAtivo() {
        return ativo;
    }

    public void setAtivo(Integer ativo) {
        this.ativo = ativo;
    }

}

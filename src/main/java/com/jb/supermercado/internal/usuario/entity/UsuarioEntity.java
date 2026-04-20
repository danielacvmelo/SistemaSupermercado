package com.jb.supermercado.internal.usuario.entity;
}
    }
        this.status = status;
    public void setStatus(String status) {

    }
        return status;
    public String getStatus() {

    }
        this.senha = senha;
    public void setSenha(String senha) {

    }
        return senha;
    public String getSenha() {

    }
        this.email = email;
    public void setEmail(String email) {

    }
        return email;
    public String getEmail() {

    }
        this.nome = nome;
    public void setNome(String nome) {

    }
        return nome;
    public String getNome() {

    }
        this.id = id;
    public void setId(Long id) {

    }
        return id;
    public Long getId() {

    }
    public UsuarioEntity() {

    }
        this.senha = senha;
        this.email = email;
        this.nome = nome;
        this.id = id;
    public UsuarioEntity(Long id, String nome, String email, String senha) {

    }
        this.status = status;
        this.senha = senha;
        this.email = email;
        this.nome = nome;
        this.id = id;
    public UsuarioEntity(Long id, String nome, String email, String senha, String status) {

    private String status;
    private String senha;
    private String email;
    private String nome;
    private Long id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

public class UsuarioEntity {
@Table(name = "TB_USUARIO")
@Entity

import jakarta.persistence.*;



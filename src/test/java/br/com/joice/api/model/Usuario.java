package br.com.joice.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class Usuario {

    private String nome;
    private String email;
    private String password;
    private String administrador;

    @JsonIgnore
    private String id;

    public Usuario() {
    }

    public Usuario(String nome, String email, String password, String administrador) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.administrador = administrador;
    }

    /** Gera um usuário com e-mail único para evitar conflito entre execuções. */
    public static Usuario aleatorio(boolean administrador) {
        String sufixo = UUID.randomUUID().toString().substring(0, 8);
        return new Usuario(
                "QA Automação " + sufixo,
                "qa.restassured." + sufixo + "@teste.com.br",
                "Senha@" + sufixo,
                String.valueOf(administrador));
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getAdministrador() { return administrador; }
    public void setAdministrador(String administrador) { this.administrador = administrador; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}

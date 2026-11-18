package br.com.joice.api.model;

import java.util.UUID;

public class Produto {

    private String nome;
    private int preco;
    private String descricao;
    private int quantidade;

    public Produto() {
    }

    public Produto(String nome, int preco, String descricao, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public static Produto aleatorio() {
        String sufixo = UUID.randomUUID().toString().substring(0, 8);
        return new Produto("Produto QA " + sufixo, 150, "Produto criado pela automação", 10);
    }

    public String getNome() { return nome; }
    public int getPreco() { return preco; }
    public String getDescricao() { return descricao; }
    public int getQuantidade() { return quantidade; }
}

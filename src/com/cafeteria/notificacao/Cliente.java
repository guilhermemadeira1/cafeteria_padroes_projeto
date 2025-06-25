package com.cafeteria.notificacao;

public class Cliente implements Observador{
    private String nome;

    public Cliente(){}

    public Cliente(String nome){
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public void atualizar(String mensagem){
        System.out.println("Cliente \""+nome+"\" notificado: \""+mensagem+"\"");
    }
}
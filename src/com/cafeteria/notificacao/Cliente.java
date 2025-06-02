package com.cafeteria.notificacao;

public class Cliente implements Observador{
    String nome;
    public Cliente(String nome){
        this.nome = nome;
    }
    @Override
    public void atualizar(String mensagem){
        System.out.println("Cliente "+nome+" notificado: "+mensagem);
    }
}
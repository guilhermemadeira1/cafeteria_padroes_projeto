package com.cafeteria.notificacao;

import java.util.ArrayList;
import java.util.List;

public class Pedido{
    List<Observador> observadores = new ArrayList<>();
    private String status;

    public void adicionarObservador(Observador o){
        observadores.add(o);
    }
    public void atualizarStatus(String mensagem){
        status = mensagem;
    }
    public void notificarTotos(){
        for(Observador o : observadores){
            o.atualizar("Satus do pedido: " + status);
        }
    }
}
package com.cafeteria.app;

import com.cafeteria.bebidas.*;
import com.cafeteria.bebidas.complementos.*;
import com.cafeteria.config.ConfiguracaoCafeteria;
import com.cafeteria.notificacao.*;
import com.cafeteria.descontos.*;

public class Main{
    public static void main(String[]args){
        System.out.println("Bem-vindo à cafeteria "
                + ConfiguracaoCafeteria.getInstancia().getNomeCafeteria() +"!");
        Bebida bebida = BebidaFactory.criarBebida("cafe");
        bebida = new Leite(bebida);
        bebida = new Canela(bebida);

        System.out.println("Pedido: " + bebida.getDescricao());
        System.out.println("Preço sem desconto: " + bebida.getPreco());

        DescontoStrategy desconto = new DescontoFidelidade();
        System.out.println("Preço com desconto fidelidade: " + desconto.aplicarDesconto(bebida.getPreco()));

        Pedido pedido = new Pedido();
        pedido.adicionarObservador(new Cliente("Ana"));
        pedido.atualizarStatus("Em preparo");
    }
}
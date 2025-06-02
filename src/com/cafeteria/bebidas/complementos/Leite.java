package com.cafeteria.bebidas.complementos;

import com.cafeteria.bebidas.Bebida;

public class Leite extends BebidaDecorator {
    public Leite(Bebida bebida){
        super(bebida);
    }
    public String getDescricao(){
        return bebida.getDescricao() + " com leite";
    }
    public double getPreco(){
        return bebida.getPreco() + 1.50;
    }
}
package com.cafeteria.bebidas.complementos;

import com.cafeteria.bebidas.Bebida;

public class Canela extends BebidaDecorator {
    public Canela(Bebida bebida){
        super(bebida);
    }
    public String getDescricao(){
        return bebida.getDescricao() + " com canela";
    }
    public double getPreco(){
        return bebida.getPreco() + 0.75;
    }
}
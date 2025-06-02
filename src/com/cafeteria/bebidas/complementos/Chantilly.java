package com.cafeteria.bebidas.complementos;

import com.cafeteria.bebidas.Bebida;

public class Chantilly extends BebidaDecorator{
    public Chantilly(Bebida bebida){
        super(bebida);
    }
    @Override
    public String getDescricao() {
        return bebida.getDescricao() + " com chantilly";
    }
    @Override
    public double getPreco() {
        return bebida.getPreco() + 0.50;
    }
}

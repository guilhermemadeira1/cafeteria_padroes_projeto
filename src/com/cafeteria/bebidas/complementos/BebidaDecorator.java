package com.cafeteria.bebidas.complementos;

import com.cafeteria.bebidas.Bebida;

public abstract class BebidaDecorator extends Bebida {
    protected Bebida bebida;
    public BebidaDecorator(Bebida bebida){
        this.bebida = bebida;
    }
}

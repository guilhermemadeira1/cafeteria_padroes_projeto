package com.cafeteria.bebidas.complementos;

import com.cafeteria.bebidas.Bebida;

public abstract class BebidaDecorator implements Bebida {
    protected Bebida bebida;
    public BebidaDecorator(Bebida bebida){
        this.bebida = bebida;
    }
    //passa a responsabilidade de implementar os métodos de Bebida pras subclasses
}

package com.cafeteria.bebidas.factories;

import com.cafeteria.bebidas.Bebida;
import com.cafeteria.bebidas.Chocolate;

public class ChocolateFactory extends BebidaFactory{
    @Override
    public Bebida criarBebida(){
        return new Chocolate();
    }
}

package com.cafeteria.bebidas.factories;

import com.cafeteria.bebidas.Bebida;
import com.cafeteria.bebidas.Cha;

public class ChaFactory extends BebidaFactory{
    @Override
    public Bebida criarBebida(){
        return new Cha();
    }
}

package com.cafeteria.bebidas.factories;

import com.cafeteria.bebidas.Bebida;
import com.cafeteria.bebidas.Cafe;

public class CafeFactory extends BebidaFactory {
    @Override
    public Bebida criarBebida() {
        return new Cafe();
    }
}

package com.cafeteria.bebidas;

public class Chocolate implements Bebida {
    @Override
    public String getDescricao() {
        return "Chocolate";
    }
    @Override
    public double getPreco() {
        return 6.0;
    }
}

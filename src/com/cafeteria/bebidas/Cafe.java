package com.cafeteria.bebidas;

public class Cafe implements Bebida{
    @Override
    public String getDescricao(){
        return "Café";
    }
    @Override
    public double getPreco(){
        return 5.00;
    }
}
package com.cafeteria.descontos;

public class DescontoAniversario implements DescontoStrategy{
    @Override
    public double aplicarDesconto(double valor) {
        return valor * 0.80; // 20%
    }
}

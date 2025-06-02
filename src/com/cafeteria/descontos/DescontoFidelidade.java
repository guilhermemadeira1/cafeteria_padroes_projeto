package com.cafeteria.descontos;

public class DescontoFidelidade implements DescontoStrategy{
    @Override
    public double aplicarDesconto(double valor){
        return valor * 0.90; // 10%
    }
}
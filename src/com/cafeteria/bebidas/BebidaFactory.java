package com.cafeteria.bebidas;

public class BebidaFactory{
    public static Bebida criarBebida(String tipo){
        return switch(tipo.toLowerCase()) {
            case "cha" -> new Cha();
            case "cafe" -> new Cafe();
            default -> throw new IllegalArgumentException("Tipo não suportado.");
        };
    }
}

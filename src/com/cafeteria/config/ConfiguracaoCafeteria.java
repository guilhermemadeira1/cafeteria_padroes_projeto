package com.cafeteria.config;

// Singleton
public class ConfiguracaoCafeteria{
    private static ConfiguracaoCafeteria instancia;
    private String nomeCafeteria = "Café Encantado";

    private ConfiguracaoCafeteria(){};

    public static ConfiguracaoCafeteria getInstancia(){
        if(instancia == null){
            instancia = new ConfiguracaoCafeteria();
        }
        return instancia;
    }
    public String getNomeCafeteria(){
        return nomeCafeteria;
    }
}
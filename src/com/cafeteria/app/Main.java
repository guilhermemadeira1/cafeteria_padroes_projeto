package com.cafeteria.app;

import com.cafeteria.bebidas.*;
import com.cafeteria.bebidas.factories.*;
import com.cafeteria.bebidas.complementos.*;
import com.cafeteria.config.ConfiguracaoCafeteria;
import com.cafeteria.notificacao.*;
import com.cafeteria.descontos.*;
import java.util.Scanner;

public class Main{
    public static void main(String[]args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Bem-vindo à cafeteria "
                + ConfiguracaoCafeteria.getInstancia().getNomeCafeteria() +"!");

        System.out.print("Insira seu nome: ");
        String nomeCliente = scan.nextLine();

        System.out.println("Opções de bebida\n1 - Café\n2 - Chá\n3 - Chocolate");
        System.out.print("Insira um número correspondente: ");
        int escolhaBebida = scan.nextInt();
        scan.nextLine();// consome quebra de linha residual deixado pelo enter

        BebidaFactory factory = null;
        factory = switch(escolhaBebida){
                case 1 -> new CafeFactory();
                case 2 -> new ChaFactory();
                case 3 -> new ChocolateFactory();
                default -> throw new IllegalArgumentException("Tipo inválido");
        };
        Bebida bebida = factory.criarBebida();

        System.out.print("Deseja adicionar um complemento para a bebida? (S/N): ");
        String temComplemento = scan.next();

        if (temComplemento.equalsIgnoreCase("s")) {
            System.out.println("Opções de complemento\n1 - Canela\n2 - Chantilly\n3 - Leite");
            System.out.print("Insira um número correspondente: ");
            int escolhaComplemento = scan.nextInt();
            scan.nextLine(); //consome quebra de linha residual

            bebida = switch (escolhaComplemento) {
                    case 1 -> new Canela(bebida);
                    case 2 -> new Chantilly(bebida);
                    case 3 -> new Leite(bebida);
                    default -> throw new IllegalArgumentException("Complemento inválido");
            };
        }
        System.out.println("Pedido escolhido: "+bebida.getDescricao());
        System.out.println("Preço sem desconto: R$"+String.format("%.2f",bebida.getPreco()));

        DescontoStrategy desconto = new DescontoFidelidade();
        System.out.println("Preço com desconto fidelidade: R$"+String.format("%.2f",desconto.aplicarDesconto(bebida.getPreco())));

        System.out.print("Realizar pedido? (S/N): ");
        String resposta2 = scan.next();

        if(resposta2.equalsIgnoreCase("s")){
            System.out.println("Pedido enviado!");
            Pedido pedido = new Pedido();
            pedido.adicionarObservador(new Cliente(nomeCliente));
            pedido.atualizarStatus("Em preparo");
            pedido.notificarTodos();
            try {
                Thread.sleep(3000); //pausa a execução por 3 segundos
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            pedido.atualizarStatus("Concluído");
            pedido.notificarTodos();
        }
        else{
            System.out.println("Pedido cancelado.");
        }

    }
}
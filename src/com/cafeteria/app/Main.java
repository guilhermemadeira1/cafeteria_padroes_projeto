package com.cafeteria.app;

import com.cafeteria.bebidas.*;
import com.cafeteria.bebidas.factories.*;
import com.cafeteria.bebidas.complementos.*;
import com.cafeteria.config.ConfiguracaoCafeteria;
import com.cafeteria.notificacao.*;
import com.cafeteria.descontos.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main{
    public static void main(String[]args) throws InterruptedException {

        System.out.println("===== Bem-vindo à cafeteria "+
                ConfiguracaoCafeteria.getInstancia().getNomeCafeteria() + "! =====");

        Scanner scan = new Scanner(System.in);
        System.out.print("Insira seu nome: ");
        String nomeCliente = scan.nextLine().strip();
        Cliente cliente = new Cliente(nomeCliente);

        Map<Integer,String> opcoesBebida = new HashMap<>();
        opcoesBebida.put(1,"Café");
        opcoesBebida.put(2,"Chá");
        opcoesBebida.put(3,"Chocolate");

        System.out.println("Opções de bebida");
        for(int key : opcoesBebida.keySet()){
            System.out.println(key +" - "+ opcoesBebida.get(key));
        }
        Integer escolhaBebida = null;
        while (true){
            System.out.print("Escolha um número: ");
            try {
                escolhaBebida = scan.nextInt();
                if(opcoesBebida.containsKey(escolhaBebida)){
                    break;
                }
                else{
                    System.out.println("Opção não encontrada.");
                }
            }
            catch (InputMismatchException e){
                 System.out.print("Insira um número inteiro: ");
                 scan.nextLine(); // consome quebra de linha residual deixada pelo enter
            }
        }
        BebidaFactory factory = switch(escolhaBebida){
                case 1 -> new CafeFactory();
                case 2 -> new ChaFactory();
                case 3 -> new ChocolateFactory();
                default -> throw new IllegalArgumentException("Tipo inválido");
        };
        Bebida bebida = factory.criarBebida();

        System.out.print("Deseja adicionar um complemento para a bebida? (S/N): ");
        String temComplemento = scan.next();

        if (temComplemento.equalsIgnoreCase("s")) {
            Map<Integer,String> opcoesComplemento = new HashMap<>();
            opcoesComplemento.put(1,"Canela");
            opcoesComplemento.put(2,"Chantilly");
            opcoesComplemento.put(3, "Leite");

            System.out.println("Opções de complemento");
            for(Integer key : opcoesComplemento.keySet()){
                System.out.println(key+" - "+opcoesComplemento.get(key));
            }
            Integer escolhaComplemento = null;
            while (true){
                System.out.print("Escolha um número: ");
                try{
                    escolhaComplemento = scan.nextInt();
                    if(opcoesComplemento.containsKey(escolhaComplemento)){
                        break;
                    }
                }
                catch(InputMismatchException e){
                    System.out.print("Insira um número inteiro: ");
                    scan.nextLine(); //consome quebra de linha do enter
                }
            }
            bebida = switch (escolhaComplemento) {
                    case 1 -> new Canela(bebida);
                    case 2 -> new Chantilly(bebida);
                    case 3 -> new Leite(bebida);
                    default -> throw new IllegalArgumentException("Complemento inválido");
            };
        }
        System.out.println("Pedido escolhido: "+bebida.getDescricao());
        DescontoStrategy desconto = new SemDesconto();

        System.out.print("Deseja receber desconto de aniversário? (S/N): ");
        String querDesconto = scan.next();
        String descricaoDesconto = "Sem desconto.";

        if(querDesconto.equalsIgnoreCase("s")){
            LocalDate dataAtual = LocalDate.now();
            int dia, mes;

            while(true){
                try {
                    System.out.print("Informe seu aniversário\nDia: ");
                    dia = scan.nextInt();
                    System.out.print("Mês: ");
                    mes = scan.nextInt();
                    break;
                }
                catch (InputMismatchException e){
                    scan.nextLine();
                }
            }
            if (dataAtual.getMonthValue() == mes && dataAtual.getDayOfMonth() == dia){
                desconto = new DescontoAniversario();
                System.out.println();
                descricaoDesconto = "20% com desconto aniversário.\n"+
                                    "\tPreço sem desconto: R$"+String.format("%.2f",bebida.getPreco());
            }
            else{
                System.out.println("Hoje não é seu aniversário");
            }
        }
        System.out.println("=========== Detalhes do pedido =================");
        System.out.println("\tCliente: "+cliente.getNome());
        System.out.println("\tBebida: "+bebida.getDescricao());
        System.out.println("\tDesconto: "+ descricaoDesconto);
        System.out.println("\tPreço final: R$"+ String.format("%.2f",desconto.aplicarDesconto(bebida.getPreco())));
        System.out.println("===========================================");

        System.out.print("Finalizar pedido? (S/N): ");
        String finalizarPedido = scan.next();
        scan.close();

        if(finalizarPedido.equalsIgnoreCase("s")){
            System.out.println("Pedido enviado!");
            Pedido pedido = new Pedido();
            pedido.adicionarObservador(cliente);
            pedido.atualizarStatus("Em preparo");
            pedido.notificarTodos();

            Thread.sleep(5000); //pausa por 5 segundos

            pedido.atualizarStatus("Concluído");
            pedido.notificarTodos();
            System.out.println(ConfiguracaoCafeteria.getInstancia().getNomeCafeteria()+" agredece sua preferência!");
        }
        else{
            System.out.println("Pedido cancelado.");
        }
    }
}
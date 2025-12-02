package Servicos;

import java.util.Scanner;
import Entidades.*;
import enums.*;
import excecoes.*;

public class SistemaMobilidade {
    
    public static void clearScreen() { 
        for(int i = 0; i < 30; i++) System.out.println(); 
    }
    
    public static void delay(double seconds) { 
        try { Thread.sleep((long)(seconds * 1000)); } catch (InterruptedException e) {} 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoUsuarios servicoUsuarios = new ServicoUsuarios();
        ServicoCorridas servicoCorridas = new ServicoCorridas();
        
        Passageiro passageiroAtual = null;
        Motorista motoristaAtual = null;
        
        boolean sistemaRodando = true;
        boolean usuarioLogado = false;
        boolean isPassageiro = false;

        String[] nomesAleatorios = {"João Silva", "Maria Oliveira", "Carlos Santos", "Ana Souza", "Pedro Lima"};

        while (sistemaRodando) {
            if (!usuarioLogado) {
                System.out.println("\n=== Sistema de Mobilidade ===");
                System.out.println("1 - Cadastro Passageiro");
                System.out.println("2 - Cadastro Motorista");
                System.out.println("3 - Login");
                System.out.println("4 - Sair");
                System.out.print("Escolha: ");
                
                int opcao = scanner.nextInt();
                
                switch (opcao) {
                    case 1:
                        Passageiro novoP = new Passageiro();
                        servicoUsuarios.cadastrarPassageiro(novoP);
                        break;
                    case 2:
                        Motorista novoM = new Motorista();
                        servicoUsuarios.cadastrarMotorista(novoM);
                        break;
                    case 3:
                        System.out.print("Email: ");
                        String email = scanner.next();
                        System.out.print("Senha: ");
                        String senha = scanner.next();
                        
                        if (servicoUsuarios.autenticar(email, senha)) {
                            System.out.println("Login realizado!");
                            usuarioLogado = true;
                            
                            if (servicoUsuarios.getPassageiroLogado() != null && servicoUsuarios.getPassageiroLogado().getEmail().equals(email)) {
                                passageiroAtual = servicoUsuarios.getPassageiroLogado();
                                isPassageiro = true;
                            } else if (servicoUsuarios.getMotoristaLogado() != null) {
                                motoristaAtual = servicoUsuarios.getMotoristaLogado();
                                isPassageiro = false;
                            } else {
                                System.out.println("Entrando em modo de teste (Passageiro)...");
                                passageiroAtual = new Passageiro("1", "Teste", "000", "teste", "00", "123");
                                passageiroAtual.adicionarMetodoPagamento(MetodoPagamento.DINHEIRO);
                                isPassageiro = true;
                            }
                        } else {
                            System.out.println("Dados incorretos.");
                        }
                        break;
                    case 4:
                        sistemaRodando = false;
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } else {
                
                if (isPassageiro) {
                    System.out.println("\n--- Painel Passageiro: " + passageiroAtual.getNome() + " ---");
                    System.out.println("Sua Nota Média: " + String.format("%.1f", passageiroAtual.getMediaAvaliacao()) + " estrelas");
                    System.out.println("1 - Solicitar Corrida");
                    System.out.println("2 - Ver Saldo");
                    System.out.println("3 - Logout");
                    System.out.print("Opção: ");
                    
                    int opPass = scanner.nextInt();
                    if (opPass == 1) {
                        try {
                            System.out.print("Origem: ");
                            String origem = scanner.next();
                            System.out.print("Destino: ");
                            String destino = scanner.next();
                            
                            System.out.println("Categoria: 1-Básico, 2-Premium");
                            int catOp = scanner.nextInt();
                            CategoriaServico cat = (catOp == 2) ? CategoriaServico.PREMIUM : CategoriaServico.BASICO;
                            
                            System.out.println("Buscando motoristas...");
                            delay(1);
                            
                            Corrida corrida = servicoCorridas.solicitarCorrida(passageiroAtual, origem, destino, cat);
                            
                            System.out.println("Motorista encontrado: " + corrida.getMotorista().getNome());
                            System.out.println("Nota do Motorista: " + String.format("%.1f", corrida.getMotorista().getMediaAvaliacao()));
                            delay(1);
                            
                            corrida.iniciarViagem();
                            System.out.println("Viagem em andamento...");
                            delay(2);
                            
                            System.out.println("Chegou ao destino! Valor: R$ " + String.format("%.2f", corrida.calcularPreco()));
                            
                            if (!passageiroAtual.getMetodosPagamento().isEmpty()) {
                                System.out.println("Escolha pagamento:");
                                for(int i=0; i < passageiroAtual.getMetodosPagamento().size(); i++) {
                                    System.out.println((i+1) + " - " + passageiroAtual.getMetodosPagamento().get(i));
                                }
                                int pg = scanner.nextInt();
                                if (pg > 0 && pg <= passageiroAtual.getMetodosPagamento().size()) {
                                    MetodoPagamento mp = passageiroAtual.getMetodosPagamento().get(pg-1);
                                    corrida.setMetodoPagamentoUtilizado(mp);
                                }
                            }
                            
                            corrida.finalizarViagem();
                            passageiroAtual.setSaldo(passageiroAtual.getSaldo() - corrida.calcularPreco());

                            System.out.println("\n--------------------------------");
                            System.out.print("Avalie o motorista (1 a 5): ");
                            double nota = scanner.nextDouble();
                            corrida.getMotorista().adicionarAvaliacao(new Avaliacao(passageiroAtual.getNome(), nota, "Boa corrida"));
                            System.out.println("Obrigado pela avaliação!");
                            
                            System.out.println("O motorista está avaliando você...");
                            delay(1);
                            double notaRecebida = 4.0 + Math.random();
                            if (notaRecebida > 5.0) notaRecebida = 5.0;
                            
                            passageiroAtual.adicionarAvaliacao(new Avaliacao(corrida.getMotorista().getNome(), notaRecebida, "Passageiro tranquilo"));
                            System.out.println("O motorista te avaliou com " + String.format("%.1f", notaRecebida) + " estrelas.");
                            System.out.println("Sua nova média: " + String.format("%.1f", passageiroAtual.getMediaAvaliacao()));

                            
                        } catch (Exception e) {
                            System.out.println("Erro na corrida: " + e.getMessage());
                        }
                    } else if (opPass == 2) {
                        System.out.println("Saldo: R$ " + String.format("%.2f", passageiroAtual.getSaldo()));
                    } else {
                        usuarioLogado = false;
                        passageiroAtual = null;
                    }
                    
                } else {
                    System.out.println("\n--- Painel Motorista: " + motoristaAtual.getNome() + " ---");
                    System.out.println("Sua Nota Média: " + String.format("%.1f", motoristaAtual.getMediaAvaliacao()) + " estrelas");
                    System.out.println("Status: " + motoristaAtual.getStatus());
                    System.out.println("1 - Ficar ONLINE");
                    System.out.println("2 - Ver Saldo");
                    System.out.println("3 - Logout");
                    System.out.print("Opção: ");
                    
                    int opMot = scanner.nextInt();
                    
                    if (opMot == 1) {
                        motoristaAtual.setStatus(MotoristaStatus.ONLINE);
                        System.out.println("Você está ONLINE. Aguardando...");
                        delay(2);
                        
                        System.out.println("\n!!! NOVA SOLICITAÇÃO !!!");
                        
                        String nomeSorteado = nomesAleatorios[(int)(Math.random() * nomesAleatorios.length)];
                        Passageiro pFake = new Passageiro("99", nomeSorteado, "000", "email", "00", "00");
                        
                        double notaInicialFake = 3.5 + Math.random() * 1.5; 
                        if (notaInicialFake > 5.0) notaInicialFake = 5.0;
                        pFake.adicionarAvaliacao(new Avaliacao("Sistema", notaInicialFake, "Inicial"));

                        double distFake = 5.0 + Math.random() * 15.0; 
                        CategoriaServico catFake = CategoriaServico.BASICO;
                        
                        Corrida corridaOfertada = new Corrida(
                            System.currentTimeMillis(), "Centro", "Bairro Sul", 
                            StatusCorrida.SOLICITADA, pFake, catFake, distFake
                        );
                        
                        double valorEstimado = catFake.calcularPreco(distFake);
                        
                        System.out.println("Passageiro: " + pFake.getNome() + " (Nota: " + String.format("%.1f", pFake.getMediaAvaliacao()) + ")");
                        System.out.println("Valor: R$ " + String.format("%.2f", valorEstimado));
                        System.out.println("1 - ACEITAR | 2 - RECUSAR");
                        
                        int decisao = scanner.nextInt();
                        
                        if (decisao == 1) {
                            corridaOfertada.aceitar(motoristaAtual);
                            motoristaAtual.setStatus(MotoristaStatus.EM_CORRIDA);
                            System.out.println("Corrida aceita. Em andamento...");
                            delay(2);
                            
                            try {
                                corridaOfertada.setMetodoPagamentoUtilizado(MetodoPagamento.DINHEIRO);
                                corridaOfertada.finalizarViagem();
                                
                                motoristaAtual.setSaldo(motoristaAtual.getSaldo() + valorEstimado);
                                motoristaAtual.setStatus(MotoristaStatus.ONLINE);
                                
                                System.out.println("Corrida Finalizada. Saldo atualizado.");
                                
                                System.out.println("\n--------------------------------");
                                System.out.print("Avalie o passageiro " + pFake.getNome() + " (1 a 5): ");
                                double notaDada = scanner.nextDouble();
                                
                                pFake.adicionarAvaliacao(new Avaliacao(motoristaAtual.getNome(), notaDada, "Avaliação do motorista"));
                                
                                System.out.println("Avaliação enviada!");
                                System.out.println("Nova média do passageiro: " + String.format("%.1f", pFake.getMediaAvaliacao()));
                                
                                System.out.println("--------------------------------");
                                System.out.println("O passageiro também te avaliou...");
                                delay(1);
                                double notaRecebidaMot = 4.0 + Math.random();
                                if (notaRecebidaMot > 5.0) notaRecebidaMot = 5.0;
                                
                                motoristaAtual.adicionarAvaliacao(new Avaliacao(pFake.getNome(), notaRecebidaMot, "Motorista rapido"));
                                System.out.println("Você recebeu " + String.format("%.1f", notaRecebidaMot) + " estrelas.");
                                System.out.println("Sua nova média: " + String.format("%.1f", motoristaAtual.getMediaAvaliacao()));
                                
                            } catch (Exception e) {
                                System.out.println("Erro: " + e.getMessage());
                            }
                            
                        } else {
                            System.out.println("Recusada.");
                        }
                        
                    } else if (opMot == 2) {
                        System.out.println("Saldo: R$ " + String.format("%.2f", motoristaAtual.getSaldo()));
                    } else {
                        motoristaAtual.setStatus(MotoristaStatus.OFFLINE);
                        usuarioLogado = false;
                        motoristaAtual = null;
                    }
                }
            }
        }
        System.out.println("Sistema encerrado.");
        scanner.close();
    }
}
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
                System.out.println("Pagamento pendente. Por favor, regularize sua situação financeira.");
                setSaldoPassageiro(saldoPassageiro() - corrida.calcularPreco());
            }
                System.out.println("Saldo atual do passageiro: R$ " + getSaldoPassageiro());
                
                for(i = 0; i<1; i++){
                System.out.println("*Avalie sua experiência com o motorista*");
                System.out.println("Digite uma nota de 1 a 5 estrelas:");
                double estrelas = scanner.nextDouble();
                if(estrelas < 1 || estrelas > 5){
                    System.out.println("Nota inválida. Por favor, insira uma nota entre 1 e 5.");
                    i--;
                } else {
                    System.out.println("Obrigado por sua avaliação!");
                    avaliar(estrelas,corrida.getMotorista());
                    i++;
                    
                }
           } 
        
    }
            catch (NenhumMotoristaDisponivelException e) {
            System.out.println(e.getMessage("Nenhum motorista disponível no momento."));
        }
       } else if(Login == true && motoristaLogado == true){
        System.out.println("Sistema de Mobilidade - Motorista Logado");
        delay(3);
        clearScreen();
        System.out.println("*Sistema de Mobilidade*");
            setStatus(StatusMotorista.OFFLINE);
            if(motorista.getVeiculoAtual() == null) {
                System.out.println("Você não possui um veículo cadastrado. Por favor, cadastre um veículo antes de aceitar corridas.");
                return;
            }else if(!motorista.isCnhValida()) {
                System.out.println("Sua CNH está vencida ou inválida. Atualize suas informações para continuar.");
                return;
            }else
            System.out.println("Deseja ficar online?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            int opcaoOnline = scanner.nextInt();
            if (opcaoOnline == 1) {
                System.out.println("Você está agora online e disponível para aceitar corridas.");
                setStatus(StatusMotorista.ONLINE);
            } else {
                System.out.println("Você está offline.");
            }
            for(i = 0; i<1; i++){
            System.out.println("Aguardando solicitação de corrida...");
            delay(3);
            clearScreen();
            System.out.println("Você recebeu uma solicitação de corrida!");
            aceitarCorrida();
            System.out.println("Deseja aceitar a corrida?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            int aceitar = scanner.nextInt();
            if(aceitar == 1){
                return i++;
            } else {
                System.out.println("Corrida recusada. Voltando ao modo de espera.");
                i--;
            }
            iniciarViagem();
            System.out.println("Corrida em andamento! ID da corrida: " + corrida.getId());
            delay(Math.random()*20);
            System.out.println("Corrida finalizada! Total recebido: R$ " + corrida.calcularPreco());
            finalizarViagem();
            System.out.println("Saldo atual do motorista: R$ " + getSaldoMotorista());
            
            
    
        
}
}
    
   
        } 
    while (sistema == true);
}
}





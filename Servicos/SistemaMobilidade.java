package Servicos;

import java.util.Scanner;

import entidades.*;
import entidades.pagamento.*;
import enums.*;
import excecoes.*;
import servicos.*;

public class SistemaMobilidade {
    public static void main(String[] args) {
        do{
       do {
        boolean sistema = true;
        ServicoUsuarios servicoUsuarios = new ServicoUsuarios();
        ServicoCorridas servicoCorridas = new ServicoCorridas();
        login = false;
        System.out.println("Bem-vindo ao Sistema de Mobilidade!");
        System.out.println("Selecione uma opção:");
        System.out.println("1 - Cadastro.");
        System.out.println("2 - Login.");
        System.out.println("3 - Sair.");
        Scanner scanner = new Scanner(System.in);
        int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Cadastro selecionado, escolha uma opção:");
                    System.out.println("1 - Passageiro");
                    System.out.println("2 - Motorista");
                    int tipoCadastro = scanner.nextInt();
                    if (tipoCadastro == 1) {
                        Passageiro passageiro = new Passageiro();
                        servicoUsuarios.cadastrarPassageiro(passageiro);
                        boolean passageiroLogado = true;
                    } else if (tipoCadastro == 2) {
                        Motorista motorista = new Motorista();
                        servicoUsuarios.cadastrarMotorista(motorista);
                        boolean motoristaLogado = true;
                    } else {
                        System.out.println("Opção inválida.");
                    }
                    break;
                case 2:
                    System.out.println("Login selecionado.");
                    System.out.println("Digite seu email:");
                    String email = scanner.next();
                    System.out.println("Digite sua senha:");
                    String senha = scanner.next();
                    boolean autenticado = servicoUsuarios.autenticar(email, senha);
                    if (autenticado) {
                        System.out.println("Login realizado com sucesso!");
                        boolean Login = true;
                    } else {
                        System.out.println("Email ou senha incorretos.");
                    }
                    break;
                case 3:
                    System.out.println("Saindo do sistema. Até logo!");
                    sistema = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        } while (login = false || sistema == true);
       if(Login == true && passageiroLogado == true){
        System.out.println("Sistema de Mobilidade - Passageiro Logado");
        clearScreen();
        System.out.println("*Sistema de Mobilidade*");
        try {
           System.out.println("Iniciando solicitação da corrida.");
           System.out.println("Digite o local de origem:");
              String origem = scanner.next();
              System.out.println("Digite o local de destino:");
                String destino = scanner.next();
            System.out.println("Escolha uma categoria de serviço:");
            System.out.println("1 - Básico");
            System.out.println("2 - Premium");
            int categoriaEscolhida = scanner.nextInt();
             if(CategoriaServico.BASICO){
              setCategoria(CategoriaServico.BASICO);
            } else {
                setCategoria(CategoriaServico.PREMIUM);
            }
            System.out.println("Solicitando uma corrida...");
            delay(3);
            clearScreen();
            Corrida corrida = servicoCorridas.solicitarCorrida(
                origem,
                destino,
                getnome(),
                categoriaEscolhida == 1 ? CategoriaServico.BASICO : CategoriaServico.PREMIUM
            );

            System.out.println("Corrida em andamento! ID da corrida: " + corrida.getId());
            delay(Math.random()*20);
            System.out.println("Corrida finalizada! Total a pagar: R$ " + corrida.calcularPreco());
            if(passageiro.getMetodosPagamento().isEmpty()) {
                System.out.println("Nenhum método de pagamento cadastrado. Por favor, cadastre um método de pagamento antes de finalizar a corrida.");
                corrida.setStatusFinanceiro(StatusFinanceiro.PENDENTE);
                return;
            } else {
                System.out.println("Selecione um método de pagamento:");
                for (int i = 0; i < passageiro.getMetodosPagamento().size(); i++) {
                    System.out.println((i + 1) + " - " + passageiro.getMetodosPagamento().get(i).getTipo());
                }
                int metodoEscolhido = scanner.nextInt();
                MetodoPagamento metodoPagamento = passageiro.getMetodosPagamento().get(metodoEscolhido - 1);
                corrida.setMetodoPagamentoUtilizado(metodoPagamento);
                corrida.setStatusFinanceiro(StatusFinanceiro.REGULAR);
            } if(corrida.getStatusFinanceiro() != StatusFinanceiro.PENDENTE) {
                corrida.finalizarViagem();
                System.out.println("Pagamento realizado com sucesso! Obrigado por usar nosso serviço.");
                setSaldoMotorista(saldoMotorista() + corrida.calcularPreco());
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





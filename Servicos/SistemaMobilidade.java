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
        ServicoUsuarios servicoUsuarios = new ServicoUsuarios();
        ServicoCorridas servicoCorridas = new ServicoCorridas();
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
                return i++;
            default:
                System.out.println("Opção inválida, tente novamente.");
        }
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
            Corrida corrida = servicoCorridas.solicitarCorrida(
                passageiro,
                origem,
                destino,
                categoriaEscolhida == 1 ? CategoriaServico.BASICO : CategoriaServico.PREMIUM
            );
            if(CategoriaServico.BASICO){
              setCategoria(CategoriaServico.BASICO);
            } else {
                setCategoria(CategoriaServico.PREMIUM);
            }
            System.out.println("Solicitando uma corrida...");
            delay(3);
            clearScreen();
            
            System.out.println("Corrida solicitada com sucesso! ID da corrida: " + corrida.getId());
        } catch (NenhumMotoristaDisponivelException e) {
            System.out.println(e.getMessage());
        }
       } else if(Login == true && motoristaLogado == true){
        System.out.println("Sistema de Mobilidade - Motorista Logado");
        clearScreen();
        System.out.println("*Sistema de Mobilidade*");
        try {
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
                setStatus(StatusMotorista.ONLINE);
                System.out.println("Você está agora online e disponível para aceitar corridas.");
                setStatus(StatusMotorista.ONLINE);
            } else {
                System.out.println("Você está offline.");
            }
            System.out.println("Aguardando solicitação de corrida...");
            Corrida corrida = servicoCorridas.aceitarCorrida(motorista);
            if (corrida != null) {
                System.out.println("Corrida aceita com sucesso! ID da corrida: " + corrida.getId());
                motorista.setStatus(StatusMotorista.EM_CORRIDA);
            } else {
                System.out.println("Nenhuma corrida disponível no momento.");
            }
        } catch (NenhumMotoristaDisponivelException e) {
            System.out.println(e.getMessage());
        }
        


    }
}
    while (i<1);
 }
}

package Servicos;

import java.io.ObjectInputFilter.Status;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import entidades.Passageiro;
import enums.StatusFinanceiro;
import excecoes.NenhumMotoristaDisponivelException;
import excecoes.PassageiroPendenteException;
import excecoes.UsuarioPendenteException;
import pagamento.MetodoPagamento;

public class ServicoUsuarios {

    public void cadastrarPassageiro(Passageiro passageiro) {
       
        System.out.println("Iniciando cadastro de passageiro...");
        delay(3);
        clearScreen();
        System.out.println("Digite seus dados para completar o cadastro:");
        System.out.println("nome: ");
        getnome();
        System.out.println("cpf: ");
        getcpf();
        System.out.println("email: ");
        getemail();
        System.out.println("telefone: ");
        gettelefone();
        System.out.println("senha: ");
        getsenha();
        System.out.println("Cadastro do Passageiro realizado com sucesso!");
        System.out.println("Digite um método de pagamento:");
        System.out.println("1 - Cartão de Crédito");
        System.out.println("2 - Dinheiro");
        System.out.println("3 - Pix");
        Scanner scanner = new Scanner(System.in);
        int metodo = scanner.nextInt();
        switch (metodo) {
            case 1:
                System.out.println("Digite o número do cartão de crédito:");
                String numero = scanner.next();
                System.out.println("Digite a validade do cartão (MM/AA):");
                String validade = scanner.next();
                passageiro.PagamentoCartaoCredito(numero, validade);
                return MetodoPagamento.CARTAO_CREDITO;
                break;
            case 2:
                passageiro.adicionarMetodoPagamento("Dinheiro");
                return MetodoPagamento.DINHEIRO;
            case 3:
                System.out.println("Digite a chave Pix:");
                String chavePix = scanner.next();
                passageiro.PagamentoPIX(chavePix);
                return MetodoPagamento.PIX;
            default:
                System.out.println("Método de pagamento inválido, pendente de registro.");
                return StatusFinanceiro.PENDENTE;
        }
        toString(ArrayList<Passageiro> passageiro);
    }

        public void cadastrarMotorista(Motorista motorista) {
       
        System.out.println("Iniciando cadastro do Motorista...");
        delay(3);
        clearScreen();
        System.out.println("Digite seus dados para completar o cadastro:");
        System.out.println("nome: ");
        getnome();
        System.out.println("cpf: ");
        getcpf();
        System.out.println("email: ");
        getemail();
        System.out.println("telefone: ");
        gettelefone();
        System.out.println("cnh numero: ");
        getcnhnumero();
        System.out.println("cnh validade: ");
        getcnhvalidade();
        System.out.println("senha: ");
        getsenha();
        toString(ArrayList<Motorista> motorista);
        System.out.println("Digite o modelo do veículo:");
        getModelo();
        System.out.println("Digite a placa do veículo:");
        getPlaca();
        System.out.println("Digite a cor do veículo:");
        getCor();
        System.out.println("Digite o ano do veículo:");
        getAno();
        toString(ArrayList<Veiculo> veiculo);
        System.out.println("Cadastro do motorista realizado com sucesso!");
        motorista.adicionarMotorista(motorista);
    } 

   
    public void verificarPendencias(Passageiro passageiro) throws UsuarioPendenteException {
        if (passageiro.getStatusFinanceiro() == StatusFinanceiro.PENDENTE) {
            throw new UsuarioPendenteException("Usuário com pendência financeira! Resolva antes de solicitar uma corrida.");
        }
    }
    
    public boolean autenticar(String email, String senha) {
        if(email == getEmail() && senha == getSenha()){
            return true;
        } return true; 
    }
    public void verificarValidade(Motorista motorista)throws NenhumMotoristaDisponivelException{
        if(motorista.getStatus() != StatusMotorista.APROVADO){
           throw new NenhumMotoristaDisponivelException("Motorista não aprovado para dirigir no sistema.");
        }
    }
}


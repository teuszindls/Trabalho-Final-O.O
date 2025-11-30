import java.util.Arrays; // Apenas para criar listas rápidas no teste

import Entidades.Motorista;
import Entidades.Passageiro;
import Entidades.MotoristaStatus;
import Entidades.Veiculo;
import Servicos.ServicoCorridas;
import Servicos.ServicoUsuarios;

public class Main {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO SISTEMA DE MOBILIDADE ---\n");

        // 1. Instanciar os Serviços
        ServicoUsuarios servicoUsuarios = new ServicoUsuarios();
        ServicoCorridas servicoCorridas = new ServicoCorridas();

        // 2. Criar Dados "Fake" (Mock)
        // Nota: Ajuste os construtores conforme o código real da Pessoa 1
        Veiculo veiculo1 = new Veiculo("ABC-1234", "Fiat Uno", "Prata", 2010);
        
        Motorista motorista1 = new Motorista("Carlos", "carlos@uber.com", "123", "119999999", "CNH123", veiculo1);
        Passageiro passageiro1 = new Passageiro("Ana", "ana@gmail.com", "123", "118888888");

        // 3. Cadastrar no Sistema
        servicoUsuarios.cadastrarMotorista(motorista1);
        servicoUsuarios.cadastrarPassageiro(passageiro1);

        System.out.println("\n--- TESTE 1: Tentando pedir corrida SEM motorista online ---");
        // O motorista nasce OFFLINE (por padrão). Vamos tentar pedir corrida.
        servicoCorridas.solicitarCorrida(
            passageiro1, 
            "Asa Norte", 
            "Asa Sul", 
            servicoUsuarios.getMotoristasCadastrados()
        );

        System.out.println("\n--- TESTE 2: Motorista fica ONLINE ---");
        // Vamos mudar o status manualmente (simulando o motorista clicando no app)
        motorista1.setStatus(StatusMotorista.ONLINE);
        System.out.println("Motorista " + motorista1.getNome() + " agora está: " + motorista1.getStatus());

        System.out.println("\n--- TESTE 3: Tentando pedir corrida COM motorista online ---");
        servicoCorridas.solicitarCorrida(
            passageiro1, 
            "Lago Sul", 
            "Aeroporto", 
            servicoUsuarios.getMotoristasCadastrados()
        );
    }
}
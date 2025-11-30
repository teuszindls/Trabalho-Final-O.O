package Servicos;
import Entidades.Corrida;
import Entidades.Motorista;
import Entidades.Passageiro;
import Entidades.CategoriaServico;
import Entidades.Veiculo;
import enums.StatusCorrida;
import excecoes.NenhumMotoristaDisponivelException;
import enums.StatusCorrida;
import enums.MotoristaStatus;
import excecoes.EstadoInvalidoDaCorridaException;
import excecoes.NenhumMotoristaDisponivelException;
import java.util.List;

 public class SistemaMobilidade {

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   UBER/99 SIMULATION - SYSTEM STARTUP   ");
        System.out.println("=========================================\n");

        // 1. INICIALIZAÇÃO DOS SERVIÇOS (O "Backend" rodando)
        ServicoUsuarios servicoUsuarios = new ServicoUsuarios();
        ServicoCorridas servicoCorridas = new ServicoCorridas();

        // 2. CRIAÇÃO DE DADOS MOCK (Simulando o Banco de Dados)
        
        // --- Criando Veículos ---
        Veiculo carro1 = new Veiculo("JKL-0000", "Ford Ka", "Branco");
        Veiculo carro2 = new Veiculo("XPTO-1234", "Honda Civic", "Preto");

        // --- Criando Motoristas ---
        // Motorista 1: Começa ONLINE
        Motorista motoristaJoao = new Motorista("Joao da Silva", "joao@driver.com", "123", "9999-1111", carro1);
        motoristaJoao.setStatus(StatusMotorista.ONLINE);

        // Motorista 2: Começa OFFLINE
        Motorista motoristaMaria = new Motorista("Maria Souza", "maria@driver.com", "456", "9999-2222", carro2);
        motoristaMaria.setStatus(StatusMotorista.OFFLINE);

        // --- Criando Passageiros ---
        Passageiro passageiroAna = new Passageiro("Ana Cliente", "ana@mail.com", "abc", "8888-7777");
        Passageiro passageiroCarlos = new Passageiro("Carlos Caloteiro", "carlos@mail.com", "xyz", "8888-6666");
        
        // Forçando Carlos a ter pendência financeira para teste
        passageiroCarlos.setStatusFinanceiro(StatusFinanceiro.PENDENTE);

        // --- Criando Categoria ---
        CategoriaServico categoriaComum = new CategoriaServico("UberX", 5.0, 1.20); // Taxa fixa 5.0 + 1.20 por Km

        // 3. CADASTRO (Colocando nas listas do sistema)
        servicoUsuarios.cadastrarMotorista(motoristaJoao);
        servicoUsuarios.cadastrarMotorista(motoristaMaria);
        servicoUsuarios.cadastrarPassageiro(passageiroAna);
        servicoUsuarios.cadastrarPassageiro(passageiroCarlos);

        System.out.println("-> Dados carregados: 2 Motoristas, 2 Passageiros.\n");

        // =================================================================
        // CENÁRIO 1: CORRIDA BEM SUCEDIDA
        // =================================================================
        System.out.println("--- TESTE 1: Solicitação Normal (Caminho Feliz) ---");
        try {
            System.out.println("Passageiro(a) " + passageiroAna.getNome() + " solicitando corrida...");
            
            Corrida corrida1 = servicoCorridas.solicitarCorrida(
                passageiroAna, 
                "Aeroporto", 
                "Hotel Central", 
                categoriaComum, 
                servicoUsuarios.getMotoristasCadastrados() // Passa a lista onde procurar
            );

            System.out.println("✅ SUCESSO! Motorista encontrado: " + corrida1.getMotorista().getNome());
            System.out.println("   Status da Corrida: " + corrida1.getStatus());
            System.out.println("   Carro: " + corrida1.getMotorista().getVeiculoAtual().getModelo());

        } catch (Exception e) {
            System.err.println("❌ ERRO INESPERADO: " + e.getMessage());
        }

        System.out.println("\n-------------------------------------------------");

        // =================================================================
        // CENÁRIO 2: ERRO FINANCEIRO (PassageiroPendenteException)
        // =================================================================
        System.out.println("\n--- TESTE 2: Solicitação com Pendência Financeira ---");
        try {
            System.out.println("Passageiro(a) " + passageiroCarlos.getNome() + " (Status: "+ passageiroCarlos.getStatusFinanceiro() +") solicitando corrida...");
            
            servicoCorridas.solicitarCorrida(
                passageiroCarlos, 
                "Casa", 
                "Shopping", 
                categoriaComum, 
                servicoUsuarios.getMotoristasCadastrados()
            );

            System.err.println("❌ FALHA NO TESTE: O sistema permitiu a corrida, mas deveria ter bloqueado!");

        } catch (PassageiroPendenteException e) {
            System.out.println("✅ SUCESSO! O sistema bloqueou corretamente: Passageiro com pendência.");
        } catch (Exception e) {
            System.err.println("❌ ERRO ERRADO: " + e.getClass().getSimpleName());
        }

        System.out.println("\n-------------------------------------------------");

        // =================================================================
        // CENÁRIO 3: ERRO DE DISPONIBILIDADE (NenhumMotoristaDisponivelException)
        // =================================================================
        System.out.println("\n--- TESTE 3: Nenhum Motorista Disponível ---");
        
        // Simulando que o único motorista online ficou OFFLINE
        System.out.println("(Simulação: Motorista Joao ficou OFFLINE)");
        motoristaJoao.setStatus(StatusMotorista.OFFLINE);
        
        try {
            System.out.println("Passageiro(a) " + passageiroAna.getNome() + " tentando pedir corrida novamente...");
            
            servicoCorridas.solicitarCorrida(
                passageiroAna, 
                "Shopping", 
                "Casa", 
                categoriaComum, 
                servicoUsuarios.getMotoristasCadastrados()
            );

            System.err.println("❌ FALHA NO TESTE: O sistema achou motorista onde não existia!");

        } catch (NenhumMotoristaDisponivelException e) {
            System.out.println("✅ SUCESSO! O sistema avisou corretamente: Nenhum motorista disponível.");
        } catch (Exception e) {
            System.err.println("❌ ERRO ERRADO: " + e.getClass().getSimpleName());
        }

        System.out.println("\n=========================================");
        System.out.println("   FIM DA SIMULAÇÃO   ");
        System.out.println("=========================================");
    }
}

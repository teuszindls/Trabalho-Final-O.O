package Servicos;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import entidades.Corrida;
import entidades.Motorista;
import entidades.Passageiro;
import entidades.CategoriaServico;
import enums.StatusCorrida;
import enums.StatusMotorista;
import excecoes.NenhumMotoristaDisponivelException;

public class ServicoCorridas {

    private List<Motorista> motoristasCadastrados = new ArrayList<>();

    public void adicionarMotorista(Motorista m) {
        motoristasCadastrados.add(m);
    }

    public Corrida solicitarCorrida(Passageiro passageiro, String origem, String destino, CategoriaServico categoria) 
            throws NenhumMotoristaDisponivelException {
        
       
        Motorista motoristaEncontrado = buscarMotoristaDisponivel();

        if (motoristaEncontrado == null) {
            throw new NenhumMotoristaDisponivelException("Não há motoristas disponíveis no momento.");
        }

   
        double distanciaSimulada = new Random().nextDouble() * 20 + 1;
  
        long idCorrida = System.currentTimeMillis(); 
        
        Corrida novaCorrida = new Corrida(
            idCorrida,
            origem,
            destino,
            0.0, 
            0.0, 
            StatusCorrida.SOLICITADA,
            passageiro,
            categoria,
            distanciaSimulada
        );


        novaCorrida.aceitar(motoristaEncontrado);
        motoristaEncontrado.setStatus(StatusMotorista.EM_CORRIDA);

        System.out.println("Motorista " + motoristaEncontrado.getNome() + " aceitou a corrida!");
        
        return novaCorrida;
    }

    private Motorista buscarMotoristaDisponivel() {
        for (Motorista m : motoristasCadastrados) {
            if (m.getStatus() == StatusMotorista.ONLINE) {
                return m; 
            }
        }
        return null; 
    }
}

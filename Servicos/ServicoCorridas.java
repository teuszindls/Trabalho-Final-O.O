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
import servicos.SistemaUsuarios;

public class ServicoCorridas {

    public Corrida solicitarCorrida(Passageiro passageiro, String origem, String destino, CategoriaServico categoria) 
            throws NenhumMotoristaDisponivelException {
        
       
        Motorista motoristaEncontrado = SistemaUsuarios.motoristaSorteado;

        if (motoristaEncontrado == null) {
            throw new NenhumMotoristaDisponivelException("Não há motoristas disponíveis no momento.");
        }

   
        double distanciaSimulada = new Random().nextDouble() * 20 + 1;
        getDistanciaSimulada(distanciaSimulada);
  
        long idCorrida =  Math.random()*9999;
        
        Corrida novaCorrida = new Corrida(
            idCorrida,
            origem,
            destino,
            StatusCorrida.SOLICITADA,
            passageiro,
            categoria,
            distanciaSimulada);

        novaCorrida.aceitar(motoristaEncontrado);
        motoristaEncontrado.setStatus(StatusMotorista.EM_CORRIDA);

        System.out.println("Motorista " + motoristaEncontrado.getNome() + " aceitou a corrida!");
        
        return novaCorrida;
    }
    public Corrida aceitarCorrida(Motorista motorista) {
        Passageiro passageiroEncontrado = SistemaUsuarios.passageiroSorteado;

        Corrida novaViagem = new ServicoCorridas().solicitarCorrida(
            SistemaUsuarios.passageiroSorteado, 
            "Origem Exemplo", 
            "Destino Exemplo", 
            CategoriaServico.BASICO);
 
            novaViagem.aceitar(passageiroEncontrado);
            System.out.println("Passageiro " + passageiroEncontrado.getNome() + " pediu a corrida!");
            System.out.println("Dados da corrida:" + 
                "\nOrigem: " + novaViagem.getOrigem() +
                "\nDestino: " + novaViagem.getDestino() +
                "\nDistância: " + novaViagem.getDistanciaSimulada() + " km" +
                "\nCategoria: " + novaViagem.getCategoria().toString() +
                "\nValor: " + novaViagem.calcularPreco()
            );

            return novaViagem;
    }

}

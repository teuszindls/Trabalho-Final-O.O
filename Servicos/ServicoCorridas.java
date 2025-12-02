package Servicos;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import Entidades.Corrida;
import Entidades.Motorista;
import Entidades.Passageiro;
import Entidades.CategoriaServico;
import enums.StatusCorrida;
import enums.StatusMotorista;
import excecoes.NenhumMotoristaDisponivelException;
import servicos.SistemaUsuarios;

public class ServicoCorridas {

    public Corrida solicitarCorrida(Passageiro passageiro, String origem, String destino, CategoriaServico categoria) 
            throws NenhumMotoristaDisponivelException {
        int decisao = Math.random();
        if(decisao <1){
            throw new NenhumMotoristaDisponivelException("Nenhum motorista disponível no momento.");
        } else{
       Motorista motoristaEncontrado = motorista("Airton Senna","798.456.123-99","airton@gmail.com","(11) 99876-5432","01938576420",LocalDate.of(2030, 1, 1),"senha123","Fiat Uno","UBR-8562","prata","2022");
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
        Passageiro passageiroEncontrado = passageiro("Davi Teles","853.846.789-90","teles@gmail.com","(61) 91234-5859","senhapass231");

        Corrida novaViagem = new ServicoCorridas().solicitarCorrida(
            passageiroEncontrado, 
            "Plano piloto", 
            "Gama", 
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

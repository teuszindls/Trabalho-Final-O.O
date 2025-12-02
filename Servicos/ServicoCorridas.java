package Servicos;

import java.time.LocalDate;
import java.util.Random;

import Entidades.Corrida;
import Entidades.Motorista;
import Entidades.Passageiro;
import Entidades.Avaliacao;
import Entidades.CategoriaServico;
import enums.StatusCorrida;
import enums.MotoristaStatus;
import excecoes.NenhumMotoristaDisponivelException;

public class ServicoCorridas {

    private Motorista motoristaPadrao;

    public ServicoCorridas() {
        this.motoristaPadrao = new Motorista(
            "mot1", "Airton Senna", "798.456.123-99", "airton@gmail.com", 
            "(11) 99876-5432", "senha123", "01938576420", LocalDate.of(2030, 1, 1)
        );
        this.motoristaPadrao.adicionarAvaliacao(new Avaliacao("Sistema", 5.0, "Inicial"));
    }

    public Corrida solicitarCorrida(Passageiro passageiro, String origem, String destino, CategoriaServico categoria) 
            throws NenhumMotoristaDisponivelException {
        
        double decisao = Math.random();
        if(decisao < 0.1){
            throw new NenhumMotoristaDisponivelException("Nenhum motorista disponível no momento.");
        } 
        
        double distanciaSimulada = new Random().nextDouble() * 20 + 1;
        long idCorrida = (long) (Math.random() * 9999);
        
        Corrida novaCorrida = new Corrida(
            idCorrida,
            origem,
            destino,
            StatusCorrida.SOLICITADA,
            passageiro,
            categoria,
            distanciaSimulada);

        novaCorrida.aceitar(this.motoristaPadrao);
        this.motoristaPadrao.setStatus(MotoristaStatus.EM_CORRIDA);

        System.out.println("Motorista " + this.motoristaPadrao.getNome() + " aceitou a corrida!");
        
        return novaCorrida;
    }
}
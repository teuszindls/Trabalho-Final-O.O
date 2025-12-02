package Entidades;

import enums.StatusCorrida;
import enums.MetodoPagamento;
import enums.StatusFinanceiro;
import excecoes.EstadoInvalidoDaCorridaException;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;
import pagamento.MetodoPagamento1;
import pagamento.PagamentoCartaoCredito;
import pagamento.PagamentoDinheiro;
import pagamento.PagamentoPIX;

public class Corrida {
    
    private long id;
    private String localPartida;
    private String localDestino;
    private double precoFinal;
    private StatusCorrida status;
    private Passageiro passageiro;
    private Motorista motorista;
    private CategoriaServico categoria;
    private MetodoPagamento metodoPagamentoUtilizado;
    private StatusFinanceiro statusFinanceiro = StatusFinanceiro.PENDENTE;
    private double distancia;
    
    public Corrida(long id, String localPartida, String localDestino, StatusCorrida status, 
                   Passageiro passageiro, CategoriaServico categoria, double distancia) {
        this.id = id;
        this.localPartida = localPartida;
        this.localDestino = localDestino;
        this.status = status;
        this.passageiro = passageiro;
        this.categoria = categoria;
        this.distancia = distancia;
    }

    public void aceitar(Motorista m) {
        setStatus(StatusCorrida.ACEITA);
        this.motorista = m;
    }

    public long getId() { return id; }
    public String getOrigem() { return localPartida; }
    public String getDestino() { return localDestino; }
    public double getDistanciaSimulada() { return distancia; }
    public CategoriaServico getCategoria() { return categoria; }
    public Motorista getMotorista() { return motorista; }
    
    public void setStatus(StatusCorrida status) { this.status = status; }
    public StatusCorrida getStatus() { return status; }
    
    public void setMetodoPagamentoUtilizado(MetodoPagamento metodo) {
        this.metodoPagamentoUtilizado = metodo;
    }
    
    public void setStatusFinanceiro(StatusFinanceiro status) {
        this.statusFinanceiro = status;
    }
    
    public StatusFinanceiro getStatusFinanceiro() {
        return this.statusFinanceiro;
    }

    public double calcularPreco() {
        return categoria.calcularPreco(distancia);
    }

    public void iniciarViagem() {
        setStatus(StatusCorrida.EM_ANDAMENTO);
    }

    public void finalizarViagem() throws PagamentoRecusadoException, SaldoInsuficienteException {
        setStatus(StatusCorrida.PENDENTE_PAGAMENTO);
        
        double valor = calcularPreco();
        this.precoFinal = valor;

        if (this.metodoPagamentoUtilizado == null) {
            this.metodoPagamentoUtilizado = MetodoPagamento.DINHEIRO;
        }

        MetodoPagamento1 processador = null;
        switch(this.metodoPagamentoUtilizado) {
            case CARTAO_CREDITO: processador = new PagamentoCartaoCredito("1234123412341234", "12/30"); break;
            case PIX: processador = new PagamentoPIX("chave-pix-aleatoria"); break;
            default: processador = new PagamentoDinheiro(); break;
        }

        processador.processarPagamento(this.passageiro, valor);
            
        setStatus(StatusCorrida.FINALIZADA);
        setStatusFinanceiro(StatusFinanceiro.REGULAR);
    }
}
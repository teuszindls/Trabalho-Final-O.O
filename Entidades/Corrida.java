package Entidades;

import enums.StatusCorrida;
import excecoes.EstadoInvalidoDaCorridaException;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;
import pagamento.MetodoPagamento;

public class Corrida {
    
    private long id;
    private String localPartida;
    private String localDestino;
    private double precoEstimado;
    private double precoFinal;
    private StatusCorrida status;
    private Passageiro passageiro;
    private Motorista motorista;
    private CategoriaServico categoria;
    private MetodoPagamento metodoPagamentoUtilizado;
    private double distancia;
    
    public Corrida(long id, String localPartida, String localDestino, double precoEstimado, double precoFinal,
            StatusCorrida status, Passageiro passageiro, CategoriaServico categoria,
            double distancia) {
        super();
        this.id = id;
        this.localPartida = localPartida;
        this.localDestino = localDestino;
        this.precoEstimado = precoEstimado;
        this.precoFinal = precoFinal;
        this.status = status;
        this.passageiro = passageiro;
        this.categoria = categoria;
        this.distancia = distancia;
    }

    public void aceitar(Motorista m) {
        setStatus(StatusCorrida.ACEITA);
        this.motorista = m;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocalPartida() {
        return localPartida;
    }

    public void setLocalPartida(String localPartida) {
        this.localPartida = localPartida;
    }

    public String getLocalDestino() {
        return localDestino;
    }

    public void setLocalDestino(String localDestino) {
        this.localDestino = localDestino;
    }

    public double getPrecoEstimado() {
        return precoEstimado;
    }

    public void setPrecoEstimado(double precoEstimado) {
        this.precoEstimado = precoEstimado;
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public StatusCorrida getStatus() {
        return status;
    }

    public void setStatus(StatusCorrida status) {
        this.status = status;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public CategoriaServico getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaServico categoria) {
        this.categoria = categoria;
    }

    public void setMetodoPagamentoUtilizado(MetodoPagamento metodo) {
        this.metodoPagamentoUtilizado = metodo;
    }

    public MetodoPagamento getMetodoPagamentoUtilizado() {
        return this.metodoPagamentoUtilizado;
    }

    public void iniciarViagem() {
        setStatus(StatusCorrida.EM_ANDAMENTO);
    }

    public void finalizarViagem() throws PagamentoRecusadoException, SaldoInsuficienteException {
        setStatus(StatusCorrida.PENDENTE_PAGAMENTO);
        
        double valor = categoria.calcularPreco(distancia);
        this.precoFinal = valor;

        if (this.metodoPagamentoUtilizado == null) {
            throw new EstadoInvalidoDaCorridaException("Não há método de pagamento selecionado para esta corrida.");
        }

        this.metodoPagamentoUtilizado.processarPagamento(this.passageiro, valor);
            
        setStatus(StatusCorrida.FINALIZADA);
    }
    
    public void cancelar() throws EstadoInvalidoDaCorridaException {
        if (status == StatusCorrida.EM_ANDAMENTO) {
            throw new EstadoInvalidoDaCorridaException("Não é possível cancelar uma corrida que já está em andamento.");
        } else {
            setStatus(StatusCorrida.CANCELADA);
        }
    }
}
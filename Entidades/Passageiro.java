package Entidades;

import java.util.ArrayList;
import java.util.List;
import enums.MetodoPagamento;
import enums.StatusFinanceiro;

public class Passageiro extends Usuario {
    private final List<MetodoPagamento> metodosPagamento = new ArrayList<>();
    private StatusFinanceiro statusFinanceiro = StatusFinanceiro.REGULAR;
    private double saldo = 1000.0;

    public Passageiro(String id, String nome, String cpf, String email, String telefone, String senhaHash) {
        super(id, nome, cpf, email, telefone, senhaHash);
    }
    
    public Passageiro() { super(); }

    public void adicionarMetodoPagamento(MetodoPagamento metodo) {
        metodosPagamento.add(metodo);
    }

    public List<MetodoPagamento> getMetodosPagamento() {
        return metodosPagamento;
    }

    public StatusFinanceiro getStatusFinanceiro() { return statusFinanceiro; }
    public void setStatusFinanceiro(StatusFinanceiro status) { this.statusFinanceiro = status; }

    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    @Override
    public String toString() {
        return "Passageiro: " + nome;
    }
}
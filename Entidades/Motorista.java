package Entidades;

import java.time.LocalDate;
import enums.MotoristaStatus;

public class Motorista extends Usuario {
    private String cnhNumero;
    private LocalDate cnhValidade;
    private MotoristaStatus status = MotoristaStatus.OFFLINE;
    private Veiculo veiculoAtual;
    private double saldo = 0.0;

    public Motorista(String id, String nome, String cpf, String email, String telefone, String senhaHash,
                     String cnhNumero, LocalDate cnhValidade) {
        super(id, nome, cpf, email, telefone, senhaHash);
        this.cnhNumero = cnhNumero;
        this.cnhValidade = cnhValidade;
    }
    
    public Motorista() { super(); }

    public String getCnhNumero() { return cnhNumero; }
    public void setCnhNumero(String cnhNumero) { this.cnhNumero = cnhNumero; }
    public LocalDate getCnhValidade() { return cnhValidade; }
    public void setCnhValidade(LocalDate cnhValidade) { this.cnhValidade = cnhValidade; }

    public MotoristaStatus getStatus() { return status; }
    public void setStatus(MotoristaStatus status) { this.status = status; }

    public Veiculo getVeiculoAtual() { return veiculoAtual; }
    public void setVeiculoAtual(Veiculo veiculo) { this.veiculoAtual = veiculo; }

    public boolean isCnhValida() {
        if (cnhValidade == null) return true; 
        return !LocalDate.now().isAfter(cnhValidade);
    }
    
    public double getSaldo() { return saldo; }
    public void setSaldo(double saldo) { this.saldo = saldo; }

    @Override
    public String toString() {
        return "Motorista: " + nome;
    }
}
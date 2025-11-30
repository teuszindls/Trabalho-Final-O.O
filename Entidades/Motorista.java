package Entidades;

import java.time.LocalDate;
import java.util.Objects;
import enums.MotoristaStatus;

public class Motorista extends Usuario {
    private String cnhNumero;
    private LocalDate cnhValidade;
    private MotoristaStatus status = MotoristaStatus.OFFLINE;
    private Veiculo veiculoAtual;

    public Motorista(String id, String nome, String cpf, String email, String telefone, String senhaHash,
                     String cnhNumero, LocalDate cnhValidade) {
        super(id, nome, cpf, email, telefone, senhaHash);
        this.cnhNumero = cnhNumero;
        this.cnhValidade = cnhValidade;
    }

    public String getCnhNumero() { return cnhNumero; }
    public void setCnhNumero(String cnhNumero) { this.cnhNumero = cnhNumero; }
    public LocalDate getCnhValidade() { return cnhValidade; }
    public void setCnhValidade(LocalDate cnhValidade) { this.cnhValidade = cnhValidade; }

    public MotoristaStatus getStatus() { return status; }

    public void setStatus(MotoristaStatus status) {
        this.status = Objects.requireNonNull(status);
    }

    public Veiculo getVeiculoAtual() { return veiculoAtual; }

    public void setVeiculoAtual(Veiculo veiculo) {
        this.veiculoAtual = veiculo;
    }

    public boolean isCnhValida() {
        if (cnhValidade == null) return false;
        return !LocalDate.now().isAfter(cnhValidade);
    }

    @Override
    public String toString() {
        return "Motorista{" + super.toString() + ", cnh=" + cnhNumero + ", validade=" + cnhValidade +
                ", status=" + status + ", veiculo=" + veiculoAtual + '}';
    }
     public cadastrar() {
        System.out.println("Cadastro do motorista realizado com sucesso!");
}

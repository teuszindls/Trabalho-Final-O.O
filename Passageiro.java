package entidades;

import java.util.ArrayList;
import java.util.List;

public class Passageiro extends Usuario {
    private final List<String> metodosPagamento = new ArrayList<>();
    private boolean pendenciaFinanceira = false;

    public Passageiro(String id, String nome, String cpf, String email, String telefone, String senhaHash) {
        super(id, nome, cpf, email, telefone, senhaHash);
    }

    public synchronized void adicionarMetodoPagamento(String metodo) {
        if (metodo == null || metodo.isBlank()) throw new IllegalArgumentException("Metodo de pagamento inválido");
        metodosPagamento.add(metodo);
    }

    public synchronized List<String> getMetodosPagamento() {
        return List.copyOf(metodosPagamento);
    }

    public synchronized boolean possuiMetodoPagamento() {
        return !metodosPagamento.isEmpty();
    }

    public boolean hasPendenciaFinanceira() { return pendenciaFinanceira; }
    public void setPendenciaFinanceira(boolean pendenciaFinanceira) { this.pendenciaFinanceira = pendenciaFinanceira; }

    @Override
    public String toString() {
        return "Passageiro{" + super.toString() + ", metodosPagamento=" + metodosPagamento + '}';
    }
}

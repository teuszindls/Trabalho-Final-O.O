package Entidades;

import java.util.ArrayList;
import java.util.List;
import excecoes.UsuarioPendenteException;
import enums.StatusFinanceiro;
public class Passageiro extends Usuario {
    private final List<String> metodosPagamento = new ArrayList<>(3);

    public Passageiro(String id, String nome, String cpf, String email, String telefone, String senhaHash) {
        super(id, nome, cpf, email, telefone, senhaHash);
    }

    public synchronized void adicionarMetodoPagamento(String metodo) throws UsuarioPendenteException {
        if (metodo == null || metodo.isBlank()) 
            throw new UsuarioPendenteException("Você deve fornecer um método de pagamento válido.");
        metodosPagamento.add(StatusFinanceiro.PENDENTE.name());
    }

    public synchronized List<String> getMetodosPagamento() {
        return List.copyOf(metodosPagamento);
    }

    @Override
    public String toString() {
        return "Passageiro{" + super.toString() + ", metodosPagamento=" + metodosPagamento + '}';
    }
}

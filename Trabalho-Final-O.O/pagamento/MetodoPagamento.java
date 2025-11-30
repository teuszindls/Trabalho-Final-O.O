package pagamento;

import Entidades.Passageiro;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public interface MetodoPagamento {
    void processarPagamento(Passageiro passageiro, double valor) throws PagamentoRecusadoException, SaldoInsuficienteException;
}
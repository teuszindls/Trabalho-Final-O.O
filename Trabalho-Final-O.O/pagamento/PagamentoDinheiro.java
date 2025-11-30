package pagamento;

import Entidades.Passageiro;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public class PagamentoDinheiro implements MetodoPagamento {

    @Override
    public void processarPagamento(Passageiro passageiro, double valor) throws PagamentoRecusadoException, SaldoInsuficienteException {
    }
}
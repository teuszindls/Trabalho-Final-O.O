package pagamento;

import Entidades.Passageiro;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public class PagamentoDinheiro implements MetodoPagamento1 {
    @Override
    public void processarPagamento(Passageiro passageiro, double valor) throws PagamentoRecusadoException, SaldoInsuficienteException {
         System.out.println("Pagamento de R$ " + valor + " processado em Dinheiro.");
    }
}
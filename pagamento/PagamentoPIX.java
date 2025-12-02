package pagamento;

import Entidades.Passageiro;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public class PagamentoPIX implements MetodoPagamento1 {
    private String chavePix;

    public PagamentoPIX(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void processarPagamento(Passageiro passageiro, double valor) throws PagamentoRecusadoException, SaldoInsuficienteException {
        System.out.println("Pagamento de R$ " + valor + " processado via PIX.");
    }
}
package entidades.pagamento;

import entidades.Passageiro;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public class PagamentoPIX implements MetodoPagamento {
    private String chavePix;

    public PagamentoPIX(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void processarPagamento(Passageiro passageiro, double valor) throws PagamentoRecusadoException, SaldoInsuficienteException {
        if (chavePix == null || chavePix.isEmpty()) {
            throw new PagamentoRecusadoException("Chave PIX inválida.");
        }
    }

    public String getChavePix() {
        return chavePix;
    }
}
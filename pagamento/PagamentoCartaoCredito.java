package pagamento;

import entidades.Passageiro;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public class PagamentoCartaoCredito implements MetodoPagamento {
    private String numero;
    private String validade;

    public PagamentoCartaoCredito(String numero, String validade) {
        this.numero = numero;
        this.validade = validade;
    }

    @Override
    public void processarPagamento(Passageiro passageiro, double valor) throws PagamentoRecusadoException, SaldoInsuficienteException {
        if (numero == null || numero.length() < 16) {
            throw new PagamentoRecusadoException("Número do cartão inválido.");
        } else if (validade == null || validade.isEmpty()) {
            throw new PagamentoRecusadoException("Validade do cartão inválida.");
        }
    }

    public String getNumero() {
        return numero;
    }
    public String getValidade() {
        return validade;
    }
}
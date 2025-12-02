package pagamento;

import Entidades.Passageiro;
import excecoes.PagamentoRecusadoException;
import excecoes.SaldoInsuficienteException;

public class PagamentoCartaoCredito implements MetodoPagamento1 {
    private String numero;
    private String validade;

    public PagamentoCartaoCredito(String numero, String validade) {
        this.numero = numero;
        this.validade = validade;
    }

    @Override
    public void processarPagamento(Passageiro passageiro, double valor) throws PagamentoRecusadoException, SaldoInsuficienteException {
        if (numero == null || numero.length() < 16) {
        }
        System.out.println("Pagamento de R$ " + valor + " processado no Cartão de Crédito.");
    }
    
    public String getTipo() { return "Cartão de Crédito"; }
}
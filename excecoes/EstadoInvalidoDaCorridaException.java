package excecoes;

public class EstadoInvalidoDaCorridaException extends RuntimeException {
    public EstadoInvalidoDaCorridaException(String mensagem) {
        super(mensagem);
    }
}
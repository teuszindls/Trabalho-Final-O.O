package Entidades;

import java.time.LocalDateTime;
import java.util.Objects;

public class Avaliacao {
    private final String autorId;
    private final int estrelas;
    private final String comentario;
    private final LocalDateTime dataHora;

    public Avaliacao(String autorId, int estrelas, String comentario) {
        this.autorId = Objects.requireNonNull(autorId);
        if (estrelas < 1 || estrelas > 5) throw new IllegalArgumentException("Estrelas devem ficar entre 1 e 5");
        this.estrelas = estrelas;
        this.comentario = comentario;
        this.dataHora = LocalDateTime.now();
    }

    public String getAutorId() { return autorId; }
    public int getEstrelas() { return estrelas; }
    public String getComentario() { return comentario; }
    public LocalDateTime getDataHora() { return dataHora; }

    @Override
    public String toString() {
        return String.format("Avaliacao[autor=%s, estrelas=%d, comentario=%s, data=%s]",
                autorId, estrelas, comentario, dataHora.toString());
    }
}

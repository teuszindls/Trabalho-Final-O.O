package Entidades;

public class Avaliacao {
    private String autorId;
    private double estrelas;
    private String comentario;

    public Avaliacao(String autorId, double estrelas, String comentario) {
        this.autorId = autorId;
        this.estrelas = estrelas;
        this.comentario = comentario;
    }

    public double getEstrelas() {
        return estrelas;
    }

    @Override
    public String toString() {
        return String.format("Avaliacao[autor=%s, estrelas=%.1f, comentario=%s]",
                autorId, estrelas, comentario);
    }
}
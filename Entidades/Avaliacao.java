package Entidades;

public class Avaliacao {
    
    public Avaliacao avaliar();{
      double estrelas,soma_avaliacoes;
      int n_corridas;
      
      
      
    }

    @Override
    public String toString() {
        return String.format("Avaliacao[autor=%s, estrelas=%.1f, comentario=%s]",
                autorId, estrelas, comentario);
    }
}
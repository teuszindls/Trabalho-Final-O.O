package Entidades;


import java.util.Objects;
import SistemaUsuarios;

public class Avaliacao {
    
    public Avaliacao avaliar();{
      double estrelas,soma_avaliacoes;
      int n_corridas,np_corridas;
      
      double mediaAvaliacao_m = soma_avaliacoes / n_corridas;
      double mediaAvaliacao_p = soma_avaliacoes / np_corridas;
      return mediaAvaliacao_m,mediaAvaliacao_p;
    }

    @Override
    public String toString() {
        return String.format("Avaliacao[autor=%s, estrelas=%d, comentario=%s]",
                autorId, estrelas, comentario);
    }
}

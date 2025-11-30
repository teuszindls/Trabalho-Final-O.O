package Entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Usuario {
    
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private String senhaHash;

    private final List<Avaliacao> historicoAvaliacoes = new ArrayList<>();
    private int somaAvaliacoes = 0;
    private int totalAvaliacoes = 0;

    public Usuario(String nome, String cpf, String email, String telefone, String senhaHash, String senhaHash2) {
        
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senhaHash = senhaHash;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getSenhaHash() { return senhaHash; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }

    public synchronized void adicionarAvaliacao(Avaliacao avaliacao) {
        if (avaliacao == null) throw new IllegalArgumentException("Avaliacao não pode ser nula");
        historicoAvaliacoes.add(avaliacao);
        somaAvaliacoes += avaliacao.getEstrelas();
        totalAvaliacoes += 1;
    }

    public synchronized double getMediaAvaliacao() {
        return totalAvaliacoes == 0 ? 0.0 : (double) somaAvaliacoes / totalAvaliacoes;
    }

    public synchronized List<Avaliacao> getHistoricoAvaliacoes() {
        return Collections.unmodifiableList(new ArrayList<>(historicoAvaliacoes));
    }

    @Override
    public String toString() {
        return String.format("nome=%s, cpf=%s, email=%s, telefone=%s, media=%.2f]",
                nome, cpf, email, telefone, getMediaAvaliacao());
    }
    public synchronized boolean autenticar(String senhaHashString) {
        return Objects.equals(this.senhaHash, senhaHashString);
    }
}

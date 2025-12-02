package Entidades;

import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
    protected String id;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String telefone;
    protected String senhaHash;

    private final List<Avaliacao> historicoAvaliacoes = new ArrayList<>();
    private double somaAvaliacoes = 0;
    private int totalAvaliacoes = 0;

    public Usuario(String id, String nome, String cpf, String email, String telefone, String senhaHash) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.senhaHash = senhaHash;
    }
    
    public Usuario() {}

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
    public String getId() { return id; }

    public synchronized void adicionarAvaliacao(Avaliacao avaliacao) {
        if (avaliacao == null) throw new IllegalArgumentException("Avaliacao não pode ser nula");
        historicoAvaliacoes.add(avaliacao);
        somaAvaliacoes += avaliacao.getEstrelas();
        totalAvaliacoes += 1;
    }

    public synchronized double getMediaAvaliacao() {
        return totalAvaliacoes == 0 ? 0.0 : somaAvaliacoes / totalAvaliacoes;
    }

    public boolean autenticar(String senha) {
        return this.senhaHash != null && this.senhaHash.equals(senha);
    }
}
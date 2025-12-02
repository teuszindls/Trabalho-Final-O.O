package Entidades;

public class Veiculo {
    private final String placa;
    private String modelo;
    private String cor;
    private int ano;
    private boolean aprovado;

    public Veiculo(String placa, String modelo, String cor, int ano, boolean aprovado) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.aprovado = aprovado;
    }

    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public boolean isAprovado() { return aprovado; }
    public void setAprovado(boolean aprovado) { this.aprovado = aprovado; }

    @Override
    public String toString() {
        return "Veiculo: " + modelo + " (" + cor + ")";
    }
}
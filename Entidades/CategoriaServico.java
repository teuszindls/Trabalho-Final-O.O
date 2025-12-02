package Entidades;

public enum CategoriaServico {
    BASICO(1.0, 5.0),
    PREMIUM(2.2, 9.0);

    private final double multiplicadorKm;
    private final double tarifaBase;

    CategoriaServico(double multiplicadorKm, double tarifaBase) {
        this.multiplicadorKm = multiplicadorKm;
        this.tarifaBase = tarifaBase;
    }

    public double calcularPreco(double distanciaKm) {
        return tarifaBase + (multiplicadorKm * distanciaKm);
    }
}
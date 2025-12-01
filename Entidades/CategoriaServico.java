package Entidades;

public class CategoriaServico {
	private String nome;
	private double tarifaBase;
	private double multiplicadorKm;
	private double PREMIUM = 2.2;	
	private double BASICO = 1.0;
	private double PREMIUMt = 9.0;
	private double BASICOt = 5.0;
	
	public double calcularPreco(double distanciaKm) {
		distanciaKm = getDistanciaSimulada();
    if getCategoria().equals("PREMIUM") {
		tarifaBase == PREMIUMt;
		multiplicadorKm == PREMIUM;
	} else {
		tarifaBase == BASICOt;
		multiplicadorKm == BASICO;
	}
		return tarifaBase + multiplicadorKm * distanciaKm;
		}
	

}

package Entidades;

public class CategoriaServico {

	private double tarifaBase;
	private double multiplicadorKm;
	
	public double calcularPreco(double distanciaKm) {
		
		return tarifaBase + multiplicadorKm * distanciaKm;
		
	}
	

}

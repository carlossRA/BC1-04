package Dominio;

/*-- Clase casilla --
 * 
 * En esta clase definimos el objeto casilla y sus atributos.
 * 
 *  */
public class Casilla {
	int cantidad = 0;
	int fila = 0;
	int columna = 0;

	//
	public Casilla(int cantidad, int fila, int columna) {
		this.cantidad = cantidad;
		this.fila = fila;
		this.columna = columna;
	}

	public Casilla() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * -- Metodo getCantidad() --
	 * 
	 * Este metodo nos devuelve la cantidad que hay en una casilla
	 * 
	 */
	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}

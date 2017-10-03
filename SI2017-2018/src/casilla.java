/*-- Clase casilla --
 * 
 * En esta clase definimos el objeto casilla y sus atributos.
 * 
 *  */
public class casilla {
	int cantidad=0;
	int fila=0;
	int columna=0;
	//
	public casilla(int cantidad,int fila,int columna)
	{
		this.cantidad=cantidad;
		this.fila=fila;
		this.columna=columna;
	}
	
	/* -- Metodo getCantidad() --
	 * 
	 * Este metodo nos devuelve la cantidad que hay en una casilla
	 * 
	 * */
	public int getCantidad()
	{
		return this.cantidad;
	}
}

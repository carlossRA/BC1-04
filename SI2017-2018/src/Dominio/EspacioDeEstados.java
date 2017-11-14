package Dominio;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Stack;

public class EspacioDeEstados {
	Estado estado;
	Terreno terreno;

	public EspacioDeEstados(Estado estado, Terreno terreno){
		
		this.estado = estado;
		this.terreno = terreno;
	}
	
	
	public Estado getEstado() {
		return estado;
	}
	
	public Terreno getTerreno() {
		return terreno;
	}
	
	// El estado sera valido si el terreno no se ha salido del tablero de juego.
	/*public boolean EsValido(){
		
		Casilla[][]  = estado.getPuzzle().getPuzzleMatriz();
		int filas=estado.getPuzzle().getFilas();
		int columnas=estado.getPuzzle().getColumnas();
		// Si se halla coincidencia con la imagen negra se devuelve true, si no se devolvera false al final del metodo
		for(int i=0;i<filas;i++)
			for(int j=0;j<columnas;j++)
				if(piezasReconstruir[i][j].getID() < 0) return false;
		return true;
	}
	*/
	
	
	

	
	
	
}

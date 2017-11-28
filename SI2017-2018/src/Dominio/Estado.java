package Dominio;

import java.io.IOException;
import java.util.Stack;

public class Estado {
	Terreno terreno;
	Terreno ter;
	Estado estado;
	public Estado (Estado estado, Terreno ter){
		
		this.estado = estado;
		this.ter = ter;
	}
	
	
	public Estado getEstado() {
		return estado;
	}
	
	/*public Terreno getTerreno() {
		return ter;
	}*/
	public Estado(Terreno terreno) {
		this.terreno = terreno;
	}

	public Terreno getTerreno() {
		return terreno;
	}

	// 
	/*
	 *  }
	 */
	public Stack<Sucesor> calculaSucesores(Nodo nab) throws IOException {
		Terreno padre = nab.getEstado().getTerreno();
	
		
		Terreno sucesor = new Terreno();

		sucesor = copiarTerrenos(padre, sucesor);// los copio a mano por el problema de las referencias

		Estado suc;
		Stack<Sucesor> sucesores = new Stack();

		padre.MovimientosValidos();
		Stack<String> acciones = padre.DistribuirCantidades(); // obtengo los movimientos validos del padre
		
		// para evitar la misma referencia en memoria creo un nuevo terrenp hijo.
		// si no lo creara de nuevo cambiaria el terreno del padre.

		String aux;
		sucesor.MovimientosValidos();
		
		while (!acciones.isEmpty()) {

			aux = acciones.pop();
			
			sucesor.generarAccion(aux);
		     
			suc = new Estado(sucesor);
			
			sucesores.push(new Sucesor(aux, suc, 1));
			
			sucesor = copiarTerrenos(padre, sucesor);
			sucesor.MovimientosValidos();
		}

		return sucesores;
	}

	private Terreno copiarTerrenos(Terreno padre, Terreno sucesor) {

		Casilla[][] terHijo = new Casilla[padre.getFilas()][padre.getColumnas()];
		int aux = 0;
		for (int i = 0; i < terHijo.length; i++) {
			for (int j = 0; j < terHijo[i].length; j++) 
			{
				aux = padre.getTerreno()[i][j].getCantidad();
				terHijo[i][j] = new Casilla(aux, i, j);
			}
		}

		sucesor = new Terreno(terHijo, padre.getxt(), padre.getyt(), padre.getK(), padre.max(), padre.getFilas(),
				padre.getColumnas());
		return sucesor;
	}
   public int getCosto()
   { 
	   
	return terreno.getCantADist()+1;
  }
}

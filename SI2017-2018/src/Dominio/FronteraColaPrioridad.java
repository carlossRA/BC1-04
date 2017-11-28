package Dominio;

import java.util.PriorityQueue;
import java.util.Stack;



public class FronteraColaPrioridad {

	PriorityQueue<Nodo> cola = new PriorityQueue<Nodo>();

	public FronteraColaPrioridad(PriorityQueue<Nodo> cola)

	{
		this.cola = cola;

	}

	public void insertar(Nodo nodo) {

		cola.offer(nodo);
	}
	public void insertaLista(Stack<Nodo> LN){
		while(!LN.isEmpty()){
			Nodo n = LN.pop();
			insertar(n);
		}
	}//metodo añadido en la pract3

	public void crearFronteraColaPrioridad() {
		cola = new PriorityQueue<Nodo>();

	}

	public Nodo Elimina() {
		return cola.remove();
	}

	public boolean esVacia() {
		if (cola.size() == 0)
			return true;
		return false;

	}

}

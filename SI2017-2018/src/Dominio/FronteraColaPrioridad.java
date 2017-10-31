package Dominio;

import java.util.PriorityQueue;

public class FronteraColaPrioridad {

	PriorityQueue<Nodo> cola = new PriorityQueue<Nodo>();

	public FronteraColaPrioridad(PriorityQueue<Nodo> cola)

	{
		this.cola = cola;

	}

	public void insertar(Nodo nodo) {

		cola.offer(nodo);
	}

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

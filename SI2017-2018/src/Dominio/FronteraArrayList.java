package Dominio;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class FronteraArrayList {

	ArrayList<Nodo> lista = new ArrayList<Nodo>();

	public FronteraArrayList(ArrayList<Nodo> lista)

	{
		this.lista = lista;
	}

	public void insertar(Nodo nodo, int indice) {
		// Añadimos el nodo donde corresponda para mantener la ordenación de la lista
		lista.add(indice, nodo);
	}

	public void crearFronteraColaPrioridad() {
		lista = new ArrayList<Nodo>();
	}

	public Nodo Elimina() {
		// Sacamos siempre el primer elemento de la lista
		return lista.remove(0);
	}

	public int elementosArray() {
		return lista.size();
	}

	public Nodo getElemento(int indice) {
		return lista.get(indice);
	}

	public void esVacia() {
		if (lista.size() == 0)
			System.out.println("Vacia");
		if (lista.size() != 0)
			System.out.println("Con elementos");
	}

}

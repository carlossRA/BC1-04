package Dominio;

import java.io.IOException;
import java.util.Random;
import java.util.Stack;


public class Nodo implements Comparable<Nodo> {

	private Nodo nodoPadre;
	private Estado estado;
	private int costo;
	private int profundidad;
	private float valor;
	private String accion;
	
	// Constructor para el caso del nodo raiz
	public Nodo(Estado estado, String accion) {
		this.nodoPadre = null;
		this.estado = estado;
		this.costo = 0;
		this.profundidad = 1;
		this.valor = 0;
		this.accion = accion;
	}

	// Constructor para el caso del resto de nodos
	public Nodo(Nodo nodoPadre, String accion, int costo, Estado estado) throws IOException {

		this.nodoPadre = nodoPadre;
		this.costo = costo;
		this.profundidad = nodoPadre.getProfundidad() + 1;

		// Por el momento valor es un aleatorio entre 0 y 1000

		Random rn = new Random();
		this.valor = rn.nextInt(1000);
		this.accion = accion;

		

		this.estado = estado;

		/*
		 **/

	}
	public Nodo(Nodo nodoPadre, String accion, int costo, Estado estado, float valor) throws IOException 
	{
		this.nodoPadre = nodoPadre;
		this.costo = costo;
		this.profundidad = nodoPadre.getProfundidad()+1;
		this.valor = valor;
		this.accion = accion;
		this.estado=estado;
	}

	public Nodo getNodoPadre() {
		return nodoPadre;
	}

	public Estado getEstado() {
		return estado;
	}

	public int getCostoAccion() {
		return estado.getCosto();
	}
	public int GetCosto()
	{
		return costo;
	}
	public String getAccion() {
		return accion;
	}

	public int getProfundidad() {
		return profundidad;
	}

	public double getValor() {
		return valor;
	}

	public int compareTo(Nodo otro) {
		int res;
		if (this.valor < otro.getValor()) {
			res = -1;
		} else {
			res = 1;
		}
		return res;

	}
	public String serializado() {
		String serialized="@";
		Casilla[][] s = estado.getTerreno().getTerreno();
		for(int i=0; i<s.length; i++)
			for(int j=0; j<s[i].length; j++)
				serialized+=s[i][j].getCantidad();
		serialized+="@"+estado.getTerreno().getxt()+estado.getTerreno().getyt();
		return serialized;
	}

}

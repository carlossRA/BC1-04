package Dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.JOptionPane;

/*-- Clase terreno --
 * 
 * En esta clase definimos el objeto terreno y sus atributos.
 * 
 *  */

public class Terreno {
	int filas, columnas;
	Casilla cas[][];
	int xt = 0;
	int yt = 0;
	int k = 0;
	int V = 0;
	int max = 0;
	int c = 0;
	int f = 0;
	int[] MovimientosPosibles = new int[4];
	Casilla [][] casResuelto;
	Stack<String> pila = new Stack();
	/*
	 * -- Variables --
	 * 
	 * c = representa la columna f = representa la fila max= representa la cantidad
	 * maxima que puede haber en una casilla movimientosPosibles = es un array de
	 * movimientos posibles que podemos hacer en una determinada posicion del
	 * tablero.
	 * 
	 */

	// github.com/carlossRA/BC1-04
	
	public Terreno() {

	}

	public Terreno(Casilla cas[][]) {
		this.cas = cas;

	}

	public Terreno(Casilla cas[][], int x, int y, int k, int max, int f, int c) {
		this.cas = cas;
		this.xt = x;
		this.yt = y;
		this.k = k;
		this.max = max;
		this.f = f;
		this.c = c;

	}

	/*-- Metodo crearTerreno --
	 * 
	 * nos permite crear un terreno con unas dimensiones especificas.
	 *  
	 *  */

	public void crearTerreno(int p) {
		// TODO Auto-generated method stub
		V = c * f * k;
		Random rn = new Random();
		int sel = 0;
		for (int i = 0; i < cas.length; i++)
			for (int j = 0; j < cas[i].length; j++) {
				if (V > 0) {
					sel = rn.nextInt(p);
					cas[i][j] = new Casilla(sel, i, j);
					V = V - sel;
				} else
					cas[i][j] = new Casilla(0, i, j);
			}
		
		do {
			completarTerreno(p, V);
		} while (V > 0);
		
	}

	public void completarTerreno(int p, int v) {
		V = v;
		Random rn = new Random();
		int sel = 0;
		for (int i = 0; i < cas.length; i++)
			for (int j = 0; j < cas[i].length; j++) {
				if (V > 0) {
					do {
						sel = rn.nextInt(p);
					} while ((cas[i][j].getCantidad() + sel) >= p || V - sel < 0);
					cas[i][j] = new Casilla(cas[i][j].getCantidad() + sel, i, j);
					V = V - sel;
				}
			}
	}

	/*-- Metodo leerTerreno --
	 * 
	 * nos permite leer un fichero con los datos especificos de un terreno para después crearlo.
	 *  
	 *  */

	public void leerTerreno() throws FileNotFoundException {
		File file = new File("terreno.txt");
		Scanner datos = new Scanner(file);
		xt = datos.nextInt();
		yt = datos.nextInt();
		k = datos.nextInt();
		max = datos.nextInt();
		f = datos.nextInt();
		c= datos.nextInt();

		for (int i = 0; i < cas.length; i++)
			for (int j = 0; j < cas[i].length; j++) {
				cas[i][j] = new Casilla(datos.nextInt(), i - 1, j);
			}
		datos.close();
	}

	public void obtenerNuevoTerreno() {

	}

	/*-- Metodo imprimirTerreno --
	 * 
	 * Imprimimos por consola la cantidad que hay en cada casilla de un terreno ya creado.
	 *  
	 *  */

	public void imprimirTerreno() {
		System.out.println("----------------");
		System.out.println(xt + " " + yt + " " + k + " " + max + " " + f + " " + c);
		for (int i = 0; i < cas.length; i++) {
			for (int j = 0; j < cas[i].length; j++) {
				System.out.print(cas[i][j].getCantidad() + " ");
			}
			System.out.println();
		}
	}

	/*-- Metodo MovimientosValidos --
	 * 
	 * calculamos los movimientos que son validos y los que no en cada situación.
	 *  
	 *  */

	public void MovimientosValidos() {

		// Inicializamos array de flags de movimiento
		for (int i = 0; i < 4; i++) {
			MovimientosPosibles[i] = 0;
		}

		if ((xt == 0 || xt == (cas.length - 1)) || (yt == 0 || yt == (cas[1].length - 1))) {
			if ((xt == 0)) {
				if (yt == 0) {

					MovimientosPosibles[1] = 1;
					MovimientosPosibles[3] = 1;
				} else if (yt == (cas[1].length - 1)) {
					MovimientosPosibles[0] = 1;
					MovimientosPosibles[3] = 1;

				} else {
					MovimientosPosibles[0] = 1;
					MovimientosPosibles[1] = 1;
					MovimientosPosibles[3] = 1;

				}
			} else if (xt == (cas.length - 1)) {
				if (yt == 0) {
					MovimientosPosibles[1] = 1;
					MovimientosPosibles[2] = 1;

				} else if (yt == (cas[1].length - 1)) {
					MovimientosPosibles[0] = 1;
					MovimientosPosibles[2] = 1;

				} else {
					MovimientosPosibles[0] = 1;
					MovimientosPosibles[1] = 1;
					MovimientosPosibles[2] = 1;

				}
			} else {
				if (yt == 0) {
					MovimientosPosibles[1] = 1;
					MovimientosPosibles[2] = 1;
					MovimientosPosibles[3] = 1;

				} else if (yt == (cas[1].length - 1)) {
					MovimientosPosibles[0] = 1;
					MovimientosPosibles[2] = 1;
					MovimientosPosibles[3] = 1;

				}
			}
		} else {
			MovimientosPosibles[0] = 1;
			MovimientosPosibles[1] = 1;
			MovimientosPosibles[2] = 1;
			MovimientosPosibles[3] = 1;

		}

	}

	/*-- Metodo generarAcción --
	 * 
	 * generamos un movimiento de forma aleatoria y lo imprimimos por pantalla.
	 *  
	 *  */

	public void generarAccion(String elegido) {

		int cont = 0;
		int fila = 0;
		int columna = 0;
		int incr = 0;
		int cantAñadir = 0;

		for (int i = 0; i < 4; i++)
			if (MovimientosPosibles[i] == 1)
				cont++;
		for (int i = 0; i < cont; i++) {
			cantAñadir = Integer.parseInt(elegido.substring(5 + incr, 6 + incr));
			fila = Integer.parseInt(elegido.substring(7 + incr, 8 + incr));
			columna = Integer.parseInt(elegido.substring(9 + incr, 10 + incr));
			cas[fila][columna] = new Casilla(cantAñadir + cas[fila][columna].getCantidad(), fila, columna);
			incr = incr + 8;
			cas[xt][yt].setCantidad(cas[xt][yt].getCantidad() - cantAñadir);
		}
		xt = Integer.parseInt(elegido.substring(0, 1));
		yt = Integer.parseInt(elegido.substring(2, 3));

	}

	/*-- Metodo decidirAleatorio --
	 * 
	 * Generamos un numero aleatorio en función de los movimientos posibles que tengamos.
	 *  
	 *  */

	private int decidirAleatorio(int[] movimientosPosibles) {
		Random rn = new Random();
		int sel = rn.nextInt(4);

		while (movimientosPosibles[sel] == 0)// Si el movimiento no es posible debe buscarse otro.
			sel = rn.nextInt(4);
		return sel;
	}

	public void EscribirFichero() {

		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter("terreno.txt");
			pw = new PrintWriter(fichero);
			pw.println(xt + " " + yt + " " + k + " " + max + " " + c + " " + f);
			for (int i = 0; i < cas.length; i++) {
				for (int j = 0; j < cas[i].length; j++) {
					pw.print(cas[i][j].getCantidad() + " ");
				}
				pw.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public int getxt() {
		return xt;
	}

	public int getyt() {
		return yt;
	}

	public int k() {
		return k;
	}

	public int max() {
		return max;
	}

	public int c() {
		return c;
	}

	public int f() {
		return f;
	}

	public Casilla[][] getTerreno() {
		return cas;
	}

	public int[] tamañoTerrenoFichero() throws FileNotFoundException {
		int[] tamFichTer = new int[2];
		File file;
		Scanner datos;
		int contCol = 0;
		int contFil = 0;
		int contEl = 0;

		file = new File("terreno.txt");
		datos = new Scanner(file);

		datos.nextLine();
		while (datos.hasNextLine()) {
			contFil++;
			datos.nextLine();
		}
		datos.close();

		file = new File("terreno.txt");
		datos = new Scanner(file);

		datos.nextLine();
		while (datos.hasNextInt()) {
			contEl++;
			datos.nextInt();

		}

		tamFichTer[0] = contFil;
		contCol = (contEl / contFil);
		tamFichTer[1] = contCol;

		datos.close();
		return tamFichTer;
	}

	public Stack<String> DistribuirCantidades() {

		String cad = "";
		Stack<String> pila = new Stack();
		Stack<String> pila2 = new Stack();
		int izq = 0, der = 0, arr = 0, abj = 0, cantADist = 0;
		cantADist = cas[xt][yt].getCantidad() - k;

		int cantidadIzq = 0, cantidadDer = 0, cantidadArr = 0, cantidadAbj;
		
		if(cantADist<0)cantADist=0;
			if ((xt == 0 || xt == (cas.length - 1)) || (yt == 0 || yt == (cas[1].length - 1))) {
				if ((xt == 0)) {
					if (yt == 0) {// movimientos posibles para derecha ,abajo
						for (der = 0; der <= cantADist; der++)
							for (abj = 0; abj <= cas.length; abj++) {
								if ((der + abj) == cantADist)
									if (((der + cas[xt][yt + 1].getCantidad()) <= max)
											&& ((abj + cas[xt + 1][yt].getCantidad()) <= max)) {
										pila.push("(" + der + " " + xt + " " + (yt + 1) + ")," + "(" + abj + " "
												+ (xt + 1) + " " + (yt) + "),");
									}
							}

					} else if (yt == (cas[1].length - 1)) {// movimientos posibles para izquierda ,abajo
						for (izq = 0; izq <= cantADist; izq++)
							for (abj = 0; abj <= cantADist; abj++) {
								if ((izq + abj) == cantADist)
									if (((izq + cas[xt][yt - 1].getCantidad()) <= max)
											&& ((abj + cas[xt + 1][yt].getCantidad()) <= max)) {
										pila.push("(" + izq + " " + xt + " " + (yt - 1) + ")," + "(" + abj + " "
												+ (xt + 1) + " " + (yt) + "),");
									}
							}

					} else {// movimientos posibles para izquierda ,derecha ,abajo
						for (izq = 0; izq <= cantADist; izq++)
							for (der = 0; der <= cantADist; der++)
								for (abj = 0; abj <= cantADist; abj++) {
									if ((der + izq + abj) == cantADist)
										if (((der + cas[xt][yt + 1].getCantidad()) <= max)
												&& ((abj + cas[xt + 1][yt].getCantidad()) <= max)
												&& ((izq + cas[xt][yt - 1].getCantidad()) <= max)) {
											pila.push("(" + izq + " " + xt + " " + (yt - 1) + ")," + "(" + abj + " "
													+ (xt + 1) + " " + (yt) + ")," + "(" + der + " " + xt + " "
													+ (yt + 1) + "),");
										}
								}

					}
				} else if (xt == (cas.length - 1)) {
					if (yt == 0) {// movimientos posibles derecha, arriba
						for (der = 0; der <= cantADist; der++)
							for (arr = 0; arr <= cantADist; arr++) {
								if ((der + arr) == cantADist)
									if (((der + cas[xt][yt + 1].getCantidad()) <= max)
											&& ((arr + cas[xt - 1][yt].getCantidad()) <= max)) {
										pila.push("(" + der + " " + xt + " " + (yt + 1) + ")," + "(" + arr + " "
												+ (xt - 1) + " " + (yt) + "),");
									}
							}

					} else if (yt == (cas[1].length - 1)) {// movimientos posibles para izquierda ,arriba
						for (izq = 0; izq <= cantADist; izq++)
							for (arr = 0; arr <= cantADist; arr++) {
								if ((izq + arr) == cantADist)
									if (((izq + cas[xt][yt - 1].getCantidad()) <= max)
											&& ((arr + cas[xt - 1][yt].getCantidad()) <= max)) {
										pila.push("(" + izq + " " + xt + " " + (yt - 1) + ")," + "(" + arr + " "
												+ (xt - 1) + " " + (yt) + "),");
									}
							}

					} else {// movimientos posibles para izquierda,derecha,arriba
						for (izq = 0; izq <= cantADist; izq++)
							for (der = 0; der <= cantADist; der++)
								for (arr = 0; arr <= cantADist; arr++) {
									if ((izq + der + arr) == cantADist)
										if (((der + cas[xt][yt + 1].getCantidad()) <= max)
												&& ((arr + cas[xt - 1][yt].getCantidad()) <= max)
												&& ((izq + cas[xt][yt - 1].getCantidad()) <= max)) {
											pila.push("(" + izq + " " + xt + " " + (yt - 1) + ")," + "(" + der + " "
													+ (xt) + " " + (yt + 1) + ")," + "(" + arr + " " + (xt - 1) + " "
													+ (yt) + "),");
										}
								}

					}
				} else {
					if (yt == 0) {// movimientos posibles para derecha ,arriba,abajo
						for (der = 0; der <= cantADist; der++)
							for (arr = 0; arr <= cantADist; arr++)
								for (abj = 0; abj <= cantADist; abj++) {
									if ((der + arr + abj) == cantADist)
										if (((der + cas[xt][yt + 1].getCantidad()) <= max)
												&& ((abj + cas[xt + 1][yt].getCantidad()) <= max)
												&& ((arr + cas[xt - 1][yt].getCantidad()) <= max)) {
											pila.push("(" + abj + " " + (xt + 1) + " " + (yt) + ")," + "(" + der + " "
													+ (xt) + " " + (yt + 1) + ")," + "(" + arr + " " + (xt - 1) + " "
													+ (yt) + "),");
										}
								}

					} else if (yt == (cas[1].length - 1)) {// movimientos posibles para izquierda,arriba,abajo
						for (izq = 0; izq <= cantADist; izq++)
							for (arr = 0; arr <= cantADist; arr++)
								for (abj = 0; abj <= cantADist; abj++) {
									if ((izq + arr + abj) == cantADist)
										if (((arr + cas[xt - 1][yt].getCantidad()) <= max)
												&& ((abj + cas[xt + 1][yt].getCantidad()) <= max)
												&& ((izq + cas[xt][yt - 1].getCantidad()) <= max)) {
											pila.push("(" + abj + " " + (xt + 1) + " " + (yt) + ")," + "(" + izq + " "
													+ (xt) + " " + (yt - 1) + ")," + "(" + arr + " " + (xt - 1) + " "
													+ (yt) + "),");
										}
								}

					}
				}
			} else {// movimientos posibles para izquierda,derecha,arriba y abajo
				for (izq = 0; izq <= cantADist; izq++)
					for (der = 0; der <= cantADist; der++)
						for (arr = 0; arr <= cantADist; arr++)
							for (abj = 0; abj <= cantADist; abj++) {
								if ((izq + der + arr + abj) == cantADist)
									if (((der + cas[xt][yt + 1].getCantidad()) <= max)
											&& ((abj + cas[xt + 1][yt].getCantidad()) <= max)
											&& ((izq + cas[xt][yt - 1].getCantidad()) <= max)
											&& ((arr + cas[xt - 1][yt].getCantidad()) <= max)) {
										pila.push("(" + abj + " " + (xt + 1) + " " + (yt) + ")," + "(" + izq + " "
												+ (xt) + " " + (yt - 1) + ")," + "(" + arr + " " + (xt - 1) + " " + (yt)
												+ ")," + "(" + der + " " + (xt) + " " + (yt + 1) + "),");
									}
							}

			

		}

		if (MovimientosPosibles[0] == 1)

			for (int i = 0; i < pila.size(); i++)
				pila2.push(xt + " " + (yt - 1) + " " + pila.elementAt(i));

		if (MovimientosPosibles[1] == 1)
			for (int i = 0; i < pila.size(); i++)
				pila2.push(xt + " " + (yt + 1) + " " + pila.elementAt(i));

		if (MovimientosPosibles[2] == 1)
			for (int i = 0; i < pila.size(); i++)
				pila2.push((xt - 1) + " " + yt + " " + pila.elementAt(i));

		if (MovimientosPosibles[3] == 1)
			for (int i = 0; i < pila.size(); i++)
				pila2.push((xt + 1) + " " + yt + " " + pila.elementAt(i));

		return pila2;
	}

	public int getK() {
		return k;
	}

	public int getColumnas() {
		return c;
	}

	public int getFilas() {
		return f;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;

	}

	public void setXT(int xt) {
		this.xt = xt;

	}

	public void setYT(int yt) {
		this.yt = yt;

	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getMax() {
		return this.max;
	}

	public Casilla[][] getTerrenoResuelto() {
		casResuelto=new Casilla[f][c];
		
		for(int i=0;i<f;i++)
		{
			for(int j=0;j<c;j++)
			{
				casResuelto[i][j]=new Casilla(k,i,j);
			}
		}
		return casResuelto;
	}
	
	public int numeroTerrenoSinK(Terreno resuelto ) {
    	Casilla[][] t =resuelto.getTerrenoResuelto();
    	int terrenoSinK = 0;
    	
    	for (int i = 0; i < t.length; i++) {
    		for(int j = 0;j<t[i].length;j++){
    			if(t[i][j].cantidad!=k) terrenoSinK++;
    		}
    	}
    	
    	return terrenoSinK;
    }
	public String getTer()
	{	String ter=xt + " " + yt + " " + k + " " + max + " " + c + " " + f+"\n";
	for (int i = 0; i < cas.length; i++) {
		for (int j = 0; j < cas[i].length; j++) {
	        ter=ter+cas[i][j].getCantidad()+" ";
		}
		ter=ter+"\n";
	}
		return ter;
	}
public int getCantADist()
{   
	int cantADist=cas[xt][yt].getCantidad() -k;
	if(cantADist<0)cantADist=0;


	return cantADist;
}
}
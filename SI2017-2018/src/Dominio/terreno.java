package Dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

/*-- Clase terreno --
 * 
 * En esta clase definimos el objeto terreno y sus atributos.
 * 
 *  */

public class terreno {
	int filas, columnas;
	casilla cas[][];
	int xt = 0;
	int yt = 0;
	int k = 0;

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
	int max = 0;
	int c = 0;
	int f = 0;
	int[] MovimientosPosibles = new int[4];

	public terreno() {

	}

	public terreno(casilla cas[][]) {
		this.cas = cas;

	}

	public terreno(casilla cas[][], int x, int y, int k, int max, int f, int c) {
		this.cas = cas;
		this.xt = x;
		this.yt = y;
		this.k = k;
		this.max = max;
		this.c = c;
		this.f = f;

	}

	/*-- Metodo crearTerreno --
	 * 
	 * nos permite crear un terreno con unas dimensiones especificas.
	 *  
	 *  */

	public void crearTerreno(int p) {
		// TODO Auto-generated method stub

		Random rn = new Random();
		int sel = 0;
		for (int i = 0; i < cas.length; i++)
			for (int j = 0; j < cas[i].length; j++) {
				sel = rn.nextInt(p);
				cas[i][j] = new casilla(sel, i, j);
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
		c = datos.nextInt();
		f = datos.nextInt();

		for (int i = 0; i < cas.length; i++)
			for (int j = 0; j < cas[i].length; j++) {
				cas[i][j] = new casilla(datos.nextInt(), i - 1, j);
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
		System.out.println(xt + " " + yt + " " + k + " " + max + " " + c + " " + f);
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

	/*-- Metodo mostrarMovimientosPosibles --
	 * 
	 * mostramos los movimientos que son posibles en ese estado.
	 *  
	 *  */

	public void mostrarMovimientosPosibles() {

		System.out.print("\nLos movimientos válidos son: \n\n");

		if (MovimientosPosibles[0] == 1)
			System.out.println("Izquierda");
		if (MovimientosPosibles[1] == 1)
			System.out.println("Derecha");
		if (MovimientosPosibles[2] == 1)
			System.out.println("Arriba");
		if (MovimientosPosibles[3] == 1)
			System.out.println("Abajo");
	}

	/*-- Metodo generarAcción --
	 * 
	 * generamos un movimiento de forma aleatoria y lo imprimimos por pantalla.
	 *  
	 *  */

	public void generarAccion() {
		casilla aux;
		MovimientosValidos();
		int el = decidirAleatorio(MovimientosPosibles);
		if (el == 0) {
			yt = yt - 1;
			System.out.println("Izquierda");
		} else if (el == 1) {
			yt = yt + 1;
			System.out.println("Derecha");
		} else if (el == 2) {
			xt = xt - 1;
			System.out.println("Arriba");
		} else {
			xt = xt + 1;
			System.out.println("Abajo");
		}
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

	public casilla[][] getTerreno() {
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

}

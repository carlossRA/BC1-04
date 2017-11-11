package Dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;



public class Principal {

	/*
	 * -- Metodo principal --
	 * 
	 * nos pide por teclado una serie de datos b�sicos para la creaci�n de nuestro
	 * tablero.
	 * 
	 * -- variables --
	 * 
	 * opcion = almacena el valor de la acci�n del men� principal que elijamos.
	 * filas y columnas = se recogen por teclado y en ellas se guardar� el valor de
	 * las filas y columnas que tendr� nuestro tablero. cas = es una matriz del tipo
	 * casilla que nos permite crear tantas casillas como dimension vaya a tener
	 * nuestro tablero. ter = es una variable tipo terreno a la que le daremos el
	 * valor de nuestro terreno cuando est� creado.
	 */

	public static void main(String[] args) throws IOException {
		Stack pila = null;
		int opcion = 0;
		int filas = 0;
		int columnas = 0;
		int xt = 0;
		int yt = 0;
		int k = 0;
		int max = 0;
		Casilla[][] cas = null;
		Terreno ter = new Terreno();
		int[] tamFich;

		tamFich = ter.tama�oTerrenoFichero();
		cas = new Casilla[tamFich[0]][tamFich[1]];//
		ter = new Terreno(cas);
		ter.leerTerreno();// cargamos un fichero de prueba para que no de error.
		System.out.println("terreno actual:");
		ter.imprimirTerreno();

		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(
					"--- MENU PRINCIPAL ---\n1. Crear un terreno.\n2. Lectura de un terreno.\n3. Escritura de un terreno.\n4. Generar acciones posibles.\n5. Realizar una accion. \n6 Frontera cola. \n7 Frontera array. \n8 Algoritmo b�sico de b�squeda \n9 Salir"));
			switch (opcion) {
			case 1:
				xt = Integer.parseInt(JOptionPane.showInputDialog("Introduzca Xt"));
				yt = Integer.parseInt(JOptionPane.showInputDialog("Introduzca Yt"));
				k = Integer.parseInt(JOptionPane.showInputDialog("Introduzca K"));
				max = Integer.parseInt(JOptionPane.showInputDialog("Introduzca MAX"));
				while (max < k) {
					max = Integer.parseInt(JOptionPane.showInputDialog("Introduzca un valor mayor o igual que " + k));
				}
				filas = Integer.parseInt(JOptionPane.showInputDialog("Introduzca n�mero de filas:"));
				columnas = Integer.parseInt(JOptionPane.showInputDialog("Introduzca n�mero de columnas:"));

				// max=Integer.parseInt(JOptionPane.showInputDialog("Introduzca n�mero de
				// columnas:"));

				cas = new Casilla[filas][columnas];
				ter = new Terreno(cas, xt, yt, k, max, filas, columnas);
				ter.crearTerreno(max);
				System.out.println("\nNuevo terreno");
				ter.imprimirTerreno();
				break;
			case 2:
				tamFich = ter.tama�oTerrenoFichero();
				cas = new Casilla[tamFich[0]][tamFich[1]];
				ter = new Terreno(cas);
				ter.leerTerreno();
				System.out.println("terreno leido");

				ter.imprimirTerreno();
				break;
			case 3:
				ter.EscribirFichero();
				System.out.println("Terreno escrito");
				ter.imprimirTerreno();
				break;
			case 4:
				ter.MovimientosValidos();// aqui saca direcciones
				System.out.println("---------------");
				pila = ter.DistribuirCantidades();
				System.out.println("\ncantidad a distribuir: " + (ter.max() - ter.k()) + "\n");
				while (!pila.isEmpty()) {
					System.out.println(pila.pop());
				}
				/* ter.mostrarMovimientosPosibles();//aqui distribuye la cantidad */
				break;
			case 5:
				ter.MovimientosValidos();
				pila = ter.DistribuirCantidades();
				System.out.println("movimientos posibles");
				int cont = 0;
				while (cont < pila.size()) {
					System.out.println(pila.elementAt(cont));
					cont++;
				}

				Random rn = new Random();
				String elegido = null;
				if (pila.size() > 0) {

					elegido = (String) pila.elementAt(rn.nextInt(pila.size() - 1));
					System.out.println("\nMovimiento elegido:" + elegido + "cantidad a distribuir: "
							+ (cas[ter.getxt()][ter.getyt()].getCantidad() - ter.getK()));

					ter.generarAccion(elegido);
					System.out.println("\nTerreno actual");
					ter.imprimirTerreno();
				} else {
					System.out.println("No hay movimientos posibles");
				}
				break;

			case 6:
				// Iniciamos la frontera
				PriorityQueue<Nodo> cola = new PriorityQueue();
				FronteraColaPrioridad f = new FronteraColaPrioridad(cola);
				// Le pasamos el nodo ra�z
				Estado e = new Estado(ter);
				Nodo n = new Nodo(e, "nodoRaiz");
				f.insertar(n);
				// Tomaremos tiempos en las siguientes cantidades de extracciones en nuestra
				// estructura
				int[] tomaTiempos = { 3000, 10000, 25000, 50000, 100000, 500000, 1000000, 3000000 };
				Stack<Sucesor> sucesores;
				int extraccionesCola = 0;
				long tiempoInicialCola = System.currentTimeMillis();

				Nodo nabCola;
				int contador = 0;
				for (;;) {

					nabCola = f.Elimina();

					// nabCola.getEstado().getTerreno().imprimirTerreno();
					extraccionesCola++;

					for (int j = 0; j < tomaTiempos.length; j++)
						if (extraccionesCola == tomaTiempos[j]) {
							long tiempoFinalCola = System.currentTimeMillis();
							System.out.println("\nFrontera (Cola con prioridad) con " + tomaTiempos[j]
									+ " extracciones realizada en " + (tiempoFinalCola - tiempoInicialCola)
									+ " milisegundos.");
						}

					sucesores = nabCola.getEstado().calculaSucesores(nabCola);

					while (!sucesores.isEmpty()) {

						Sucesor suc = sucesores.pop();
						Nodo nodo = new Nodo(nabCola, suc.getAccion(), suc.getCosto(), suc.getEstado());
						// nodo.getEstado().getPuzzle().EscribirArray();
						// System.out.println(" "+nodo.getAccion());
						f.insertar(nodo);

					}
					contador++;
				}
			case 7:
				// Iniciamos la frontera
				double valorNodoInsertar;
				ArrayList<Nodo> lista = new ArrayList<Nodo>();
				FronteraArrayList a = new FronteraArrayList(lista);

				// Le pasamos el nodo ra�z
				Estado e2 = new Estado(ter);
				Nodo n2 = new Nodo(e2, "nodoRaiz");

				a.insertar(n2, 0);

				// Tomaremos tiempos en las siguientes cantidades de extracciones en nuestra
				// estructura
				int[] tomaTiempos2 = { 3000, 10000, 25000, 50000, 100000, 500000, 1000000, 3000000 };
				Stack<Sucesor> sucesores2 = new Stack<Sucesor>();
				int extraccionesArrayList = 0;
				long tiempoInicialArrayList = System.currentTimeMillis();

				Nodo nabArray;

				for (;;) {

					nabArray = a.Elimina();
					extraccionesArrayList++;

					for (int j = 0; j < tomaTiempos2.length; j++)
						if (extraccionesArrayList == tomaTiempos2[j]) {
							long tiempoFinalArrayList = System.currentTimeMillis();
							System.out.println(
									"\nFrontera (ArrayList) con " + tomaTiempos2[j] + " extracciones realizada en "
											+ (tiempoFinalArrayList - tiempoInicialArrayList) + " milisegundos.");
						}

					// System.out.println("Sale: "+nabArray.getValor());

					sucesores2 = nabArray.getEstado().calculaSucesores(nabArray);

					while (!sucesores2.isEmpty()) {
						int m = 0;
						int insertado = 0;
						// Calculamos la posici�n de inserci�n

						Sucesor sucesorInsertar = sucesores2.pop();

						Nodo nodo = new Nodo(nabArray, sucesorInsertar.getAccion(), sucesorInsertar.getCosto(),
								sucesorInsertar.getEstado());
						
						valorNodoInsertar = nodo.getValor();

						// Insertamos nodos
						for (m = 0; m < a.elementosArray(); m++)
							if (a.getElemento(m).getValor() >= valorNodoInsertar && insertado == 0) {
								a.insertar(nodo, m);
								insertado = 1;

							}

						// Si todos los "Valor" son menores o no hay nodos lo insertamos al final
						if (insertado == 0)
							a.insertar(nodo, a.elementosArray());
					}
				}
				case 8:
					  int estrategiaElegida;
			 		  int profMax = 9999;
			 		  int incProf = 1;
			 		  boolean solucionBusquedaAcotada = false;
			 		  List<Nodo> ListaSolucion = new ArrayList<Nodo>();
			 		  
			 		  // Pedimos estrategia al usuario
			 		    do {
			 		    	estrategiaElegida = Integer.parseInt(JOptionPane.showInputDialog("-- MENU DE ESTRATEGIAS --\n1. Anchura\n2. Profundidad Simple\n3. Profundidad acotada\n4. Profundidad iterativa.\n5. Costo uniforme."));
			 		    } while (estrategiaElegida < 1 || estrategiaElegida > 5);
			 		    
			 		  		  
			 		  // Espacio de Estados inicial y Estado inicial, necesarios para definir el Problema
			 		   Estado estadoArbol =new Estado(ter);
			 		
			 		   EspacioDeEstados espEstArbol = new EspacioDeEstados(estadoArbol, ter);
			 		  
			 		  // Inicializamos el problema
			 		 // Problema prob = new Problema(espEstArbol, estadoArbol);
			 		  
			 		  //Inicializamos la busqueda
			 		  //Busqueda b = new Busqueda(espEstArbol, estadoArbol);
			 		 
			 		  Terreno resuelto=ter;
			 		  if(estrategiaElegida == 4)  incProf = Integer.parseInt(JOptionPane.showInputDialog("Introduzca incremento de profundidad: "));
			 		 
					  // Si la estrategia es busqueda acotada pedimos profundidad maxima y lanzamos la funcion de busqueda acotada
			 		  if (estrategiaElegida == 3 || estrategiaElegida == 4)
			 		  {
			 			  profMax = Integer.parseInt(JOptionPane.showInputDialog("Introduzca profundidad maxima: "));
			 			//  ListaSolucion = b.Busqueda_Acotada(prob,estrategiaElegida,profMax,resuelto); 
			 		  } else 
			 		  { 
			 			  //ListaSolucion = b.Busqueda(prob, estrategiaElegida, profMax, incProf,resuelto);
			 		  }
			 
			 		  CreaFichero cf = new CreaFichero(ListaSolucion);
			 		  //cf.CreacionFichero(estrategiaElegida,puzzle,b.tiempoBusqueda);
			 
					
				break;
			}
			
		}

		while (opcion != 9);
	}
}
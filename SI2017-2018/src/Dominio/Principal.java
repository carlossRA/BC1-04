package Dominio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

public class Principal {

	/*
	 * -- Metodo principal --
	 * 
	 * nos pide por teclado una serie de datos básicos para la creación de nuestro
	 * tablero.
	 * 
	 * -- variables --
	 * 
	 * opcion = almacena el valor de la acción del menú principal que elijamos.
	 * filas y columnas = se recogen por teclado y en ellas se guardará el valor de
	 * las filas y columnas que tendrá nuestro tablero. cas = es una matriz del tipo
	 * casilla que nos permite crear tantas casillas como dimension vaya a tener
	 * nuestro tablero. ter = es una variable tipo terreno a la que le daremos el
	 * valor de nuestro terreno cuando esté creado.
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

		tamFich = ter.tamañoTerrenoFichero();
		cas = new Casilla[tamFich[0]][tamFich[1]];//
		ter = new Terreno(cas);
		ter.leerTerreno();// cargamos un fichero de prueba para que no de error.
		System.out.println("terreno actual:");
		ter.imprimirTerreno();

		do {
			opcion = Integer.parseInt(JOptionPane.showInputDialog(
					"--- MENU PRINCIPAL ---\n1. Crear un terreno.\n2. Lectura de un terreno.\n3. Escritura de un terreno.\n4. Generar acciones posibles.\n5. Realizar una accion. \n 6 frontera cola \n7. Salir"));
			switch (opcion) {
			case 1:
				xt = Integer.parseInt(JOptionPane.showInputDialog("Introduzca Xt"));
				yt = Integer.parseInt(JOptionPane.showInputDialog("Introduzca Yt"));
				k = Integer.parseInt(JOptionPane.showInputDialog("Introduzca K"));
				max = Integer.parseInt(JOptionPane.showInputDialog("Introduzca MAX"));
				while (max < k) {
					max = Integer.parseInt(JOptionPane.showInputDialog("Introduzca un valor mayor o igual que " + k));
				}
				filas = Integer.parseInt(JOptionPane.showInputDialog("Introduzca número de filas:"));
				columnas = Integer.parseInt(JOptionPane.showInputDialog("Introduzca número de columnas:"));

				// max=Integer.parseInt(JOptionPane.showInputDialog("Introduzca número de
				// columnas:"));

				cas = new Casilla[filas][columnas];
				ter = new Terreno(cas, xt, yt, k, max, filas, columnas);
				ter.crearTerreno(max);
				ter.imprimirTerreno();
				break;
			case 2:
				tamFich = ter.tamañoTerrenoFichero();
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
				pila=ter.DistribuirCantidades();
				System.out.println("cantidad a distribuir: "+(ter.max()-ter.k())+"\n");
				while(!pila.isEmpty())
				{
					System.out.println(pila.pop());
				}
				/*ter.mostrarMovimientosPosibles();//aqui distribuye la cantidad*/
				break;
			case 5:
				ter.MovimientosValidos();
				pila=ter.DistribuirCantidades();
				System.out.println("movimientos posibles");
				int cont=0;
				while(cont <pila.size())
				{	
					System.out.println(pila.elementAt(cont));
					cont++; 
				}
				
				Random rn =new Random();
				String elegido=null;
				if(pila.size()>0) 
				{
					
				elegido=(String) pila.elementAt(rn.nextInt(pila.size()-1));
				System.out.println("\nMovimiento elegido:"+elegido+"cantidad a distribuir: "+(cas[ter.getxt()][ter.getyt()].getCantidad()-ter.getK()));
				
				ter.generarAccion(elegido);
				System.out.println("\nTerreno actual");
				ter.imprimirTerreno();}
			else {
				System.out.println("No hay movimientos posibles");
			}
				break;
				
			case 6:
				// Iniciamos la frontera
		 		 PriorityQueue<Nodo> cola = new PriorityQueue();
		 		 FronteraColaPrioridad f = new FronteraColaPrioridad(cola);  
		 		   //Le pasamos el nodo raíz
		 		 Estado e = new Estado(ter);
		 		 Nodo n = new Nodo(e,"nodoRaiz");
		 		 f.insertar(n);
		 		 // Tomaremos tiempos en las siguientes cantidades de extracciones en nuestra estructura
		 		   int[] tomaTiempos = {3000,10000,25000,50000,100000,500000,1000000,3000000};
		 		   Stack <Sucesor> sucesores; 
		 		  int extraccionesCola = 0;
		 		   long tiempoInicialCola = System.currentTimeMillis();
		 			
		 		   Nodo nabCola;
		 			  int contador=0;
		 		   for(;;) {
		 			   
		 			
		 		     nabCola=f.Elimina();
		 		     
		 		   //  nabCola.getEstado().getTerreno().imprimirTerreno();
		 		     extraccionesCola++;
		 		     
		 		     for(int j=0; j<tomaTiempos.length; j++)
		 		    	 if(extraccionesCola == tomaTiempos[j]) {
		 		    		long tiempoFinalCola = System.currentTimeMillis();
		 		 			System.out.println("\nFrontera (Cola con prioridad) con " + tomaTiempos[j] +" extracciones realizada en " + (tiempoFinalCola-tiempoInicialCola) + " milisegundos."); 
		 		    	 }

		 			 
		 		   
		 			 sucesores = nabCola.getEstado().calculaSucesores(nabCola);
		 			  			
		 			 while(!sucesores.isEmpty())
		 			 {
		 			
		 			  Sucesor suc = sucesores.pop();
		 			  Nodo nodo = new Nodo(nabCola,suc.getAccion(),suc.getCosto(),suc.getEstado());
		 			  //nodo.getEstado().getPuzzle().EscribirArray();
		 			  //System.out.println(" "+nodo.getAccion());
		 			  f.insertar(nodo);
		 			  
		 			 }
		 		contador++;  }  
		 		   
			}
		
		}

		while (opcion != 7);
	}
}
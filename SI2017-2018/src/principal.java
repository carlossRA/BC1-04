import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class principal {
	
	/* -- Metodo principal --
	 * 
	 * nos pide por teclado una serie de datos básicos para la creación de nuestro tablero.
	 * 
	 *  -- variables --
	 *  
	 * opcion = almacena el valor de la acción del menú principal que elijamos.
	 * filas y columnas = se recogen por teclado y en ellas se guardará el valor de las filas y columnas
	 * que tendrá nuestro tablero.
	 * cas = es una matriz del tipo casilla que nos permite crear tantas casillas como dimension vaya a tener nuestro tablero.
	 * ter = es una variable tipo terreno a la que le daremos el valor de nuestro terreno cuando esté creado.
	 * */
	
	public static void main(String[]args) throws FileNotFoundException {
		
		int opcion=0;
		int filas=0;
		int columnas=0;
		//
		casilla[][] cas=null;
		terreno ter=null;
		
		cas=new casilla[3][3];//
		ter=new terreno(cas);//
		ter.leerTerreno();//cargamos un fichero de prueba para que no de error.
		System.out.println("terreno actual:");
		ter.imprimirTerreno();	
		do 
		{
			opcion=Integer.parseInt(JOptionPane.showInputDialog("--- MENU PRINCIPAL ---\n1. Crear un terreno.\n2. Lectura de un terreno.\n3. Escritura de un terreno.\n4. Generar acciones posibles.\n5. Realizar una accion. \n6. Salir")); 
			switch(opcion)
			{
				case 1:
					filas=Integer.parseInt(JOptionPane.showInputDialog("Introduzca número de filas:")); 
					columnas=opcion=Integer.parseInt(JOptionPane.showInputDialog("Introduzca número de columnas:"));
					cas=new casilla [filas][columnas];
					ter=new terreno(cas);
					ter.crearTerreno();
					ter.imprimirTerreno();
					break;
				case 2:
					cas=new casilla[3][3];
					ter=new terreno(cas);
					ter.leerTerreno();
					System.out.println("terreno leido");
					
					ter.imprimirTerreno();
				break;
				case 3:
					
				break;
				case 4:
					ter.MovimientosValidos();
					ter.mostrarMovimientosPosibles();
				break;
				case 5:
					ter.generarAccion();
					ter.imprimirTerreno();	
				break;
			}
		}
		
		while(opcion!=6);
	}
}
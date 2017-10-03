import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class principal {
	public static void main(String[]args) throws FileNotFoundException {
		int opcion=0;
		int filas=0;
		int columnas=0;
		casilla[][] cas=null;
		terreno ter=null;
		
		cas=new casilla[3][3];//
		ter=new terreno(cas);//
		ter.leerTerreno();//cargamos un fichero de prueba para que no de error.
		System.out.println("terreno actual:");
		ter.imprimirTerreno();	
		do 
		{
			opcion=Integer.parseInt(JOptionPane.showInputDialog("--- MENU PRINCIPAL ---\n1. Crear un terreno.\n2. Lectura de un terreno.\n3. Escritura de un terreno.\n4. Generar acciones posibles.\n5. Obtener estado actual\n6. Salir")); 
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
				
				break;
				case 5:
				
				break;
			}
		}
		while(opcion!=6);
	}
}